<%--
  Created by IntelliJ IDEA.
  User: qiao
  Date: 17-5-25
  Time: 下午11:59
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="utf-8">
    <title>${PROJECT_NAME} | 控制台</title>
    <jsp:include page="common/include_head.jsp"/>
    <style type="text/css">
    .knoa {
    border:none
    }
    .knob {
    border:none
    }
    
    </style>
</head>
<body class="hold-transition skin-purple-light sidebar-mini">
<div class="wrapper">

    <jsp:include page="common/header.jsp"/>
    <!-- Left side column. contains the logo and sidebar -->
    <jsp:include page="common/sidebar.jsp"/>

    <!-- Content Wrapper. Contains page content -->
    <div class="content-wrapper">
        <!-- Content Header (Page header) -->
        <section class="content-header">
            <h1>
                <small>资源情况总览</small>
            </h1>
            <ol class="breadcrumb">
                <li><a href="#"><i class="fa fa-dashboard"></i> 云课堂</a></li>
                <li class="active">资源情况总览</li>
            </ol>
        </section>
        <section class="content">
            <!-- row -->
            <div class="row">
                <div class="col-xs-12">
                    <!-- jQuery Knob -->
                    <div class="box box-primary">
                        <div class="box-header">
                            <i class="fa fa-pie-chart-o"></i>
                        </div>
                        <!-- /.box-header -->
                        <div class="box-body">
                            <div class="row">
                                <div class="col-xs-8 col-md-4 text-center" id="serverDiv">
                                    <input data-readOnly=true style="border:none" id="serverId" type="text" class="knoa"  data-skin="tron" data-thickness="0.2" data-width="120" data-height="120" data-fgColor="#3c8dbc">
                                    <div class="knob-label">服务器总数</div>
                                    <small >
					                                        运行中：<small id="runId"></small><br/>
					                </small>
					                <small>
					                                        未运行：<small id="shudownId"></small>
                                    </small>
                                </div>
                                <!-- ./col -->
                                <div class="col-xs-8 col-md-4 text-center">
                                    <input id="vmId" style="border:none" data-readOnly=true type="text" class="knoa"  data-skin="tron" data-thickness="0.2" data-width="120" data-height="120" data-fgColor="#00a65a">
                                    <div class="knob-label">桌面总数</div>
                                    <small>
					                                        运行中：<small id="vmRunId"></small><br/>
					                                        未运行：<small id="vmShudownId"></small>
                                    </small>
                                </div>
                                <!-- ./col -->
                                <div class="col-xs-8 col-md-4 text-center">
                                    <input id="diskId" data-readOnly=true type="text" class="knoa"  data-skin="tron" data-thickness="0.2" data-width="120" data-height="120" data-fgColor="#C67171">
                                    <div class="knob-label">存储总数</div>
                                    <small>
                                        		本地：<small id="diskNumber"></small>
                                    </small>
                                </div>
                                <!-- ./col -->
                            </div>
                            <!-- /.row -->
                            <hr/>
                            <div class="row">
                                <div class="col-xs-8 col-md-4 text-center">
                                    <input id="cpuId" data-readOnly=true type="text" class="knob knobserver"  data-thickness="0.2" data-width="120" data-height="120" data-fgColor="#39CCCC">
                                    <div class="knob-label">CPU占用率</div>
                                    <small>
					                                        已使用：<small id="usedCpuId"></small><br/>
					                                        总资源：<small id="totalCpuId"></small><!-- GHz -->
                                    </small>
                                </div>
                                <!-- ./col -->
                                <div class="col-xs-8 col-md-4 text-center">
                                    <input id="memoryId" data-readOnly=true type="text" class="knob knobserver" data-thickness="0.2" data-width="120" data-height="120" data-fgColor="#932ab6">
                                    <div class="knob-label">内存占用率</div>
                                    <small>
					                                        已使用：<small id="usedId"></small>GB<br/>
					                                        总资源：<small id="totalId"></small>GB
                                    </small>
                                </div>
                                <!-- ./col -->
                                <div class="col-xs-8 col-md-4 text-center">
                                    <input id="diskMemoryId" data-readOnly=true type="text" class="knob knobserver" data-thickness="0.2" data-width="120" data-height="120" data-fgColor="#00c0ef">
                                    <div class="knob-label">存储占用率</div>
                                    <small>
						                                        已使用：<small id="usedDiskMemory"></small>TB<br/>
						                                        总资源：<small id="totalDiskMemory"></small>TB
                                    </small>
                                </div>
                                <!-- ./col -->
                            <!-- /.row -->
                        </div>
                        </div>
                        <!-- /.box-body -->
                    </div>
                    <!-- /.box -->
                </div>
                <!-- /.col -->
            </div>
            <!-- /.row -->
        </section>
        <!-- /.content -->
    </div>
    <!-- /.content-wrapper -->

    <jsp:include page="common/footer.jsp"/>
</div>
<!-- ./wrapper -->

<jsp:include page="common/include_scripts.jsp"/>
<!-- page script -->
<script>
    $(function () {
    	layer.ready(function(){
    		 // layer.msg('很高兴一开场就见到你');
    	var index = layer.load(2, {
			  shade: [0.1,'#00c0ef'] //0.1透明度的白色背景
			});
    		});      
    	//服务器监控信息
    	$.ajax({
			type : "POST",
			url : "resourcesMonitor.action",
			dataType : "json",
			success : function(data) {
					layer.closeAll();
				if(data){
					$("#serverId").attr("value",parseInt(data.nodes))
					$("#runId").text(parseInt(data.nodeState))
					$("#shudownId").text(parseInt(data.nodes)-parseInt(data.nodeState))
					$("#vmId").attr("value",data.vmNumber)
					$("#vmRunId").text(data.vmRunState)
					$("#vmShudownId").text(data.vmshutoffState)
					$("#diskId").attr("value",data.diskCount)
					$("#diskNumber").text(data.diskCount)
					$("#memoryId").attr("value",parseInt(data.usedMemory/data.totalMemory*100))
					$("#usedId").text(data.usedMemory)
					$("#totalId").text(data.totalMemory)
					$("#diskMemoryId").attr("value",parseInt(data.usedDiskMemory/data.totalDiskMemory*100))
					$("#usedDiskMemory").text(parseFloat(data.usedDiskMemory).toFixed(1))
					$("#totalDiskMemory").text(parseFloat(data.totalDiskMemory).toFixed(1))
					$("#cpuId").attr("value",parseInt(data.usedVmCpu/data.totalCpu*100))
					$("#usedCpuId").text(data.usedVmCpu)
					$("#totalCpuId").text(data.totalCpu)
					//alert(data.nodes);	
				 $(".knoa").knob({
    	      draw: function () {
    	        // "tron" case
    	        if (this.$.data('skin') == 'tron') {
    	          var a = this.angle(this.cv)  // Angle
    	              , sa = this.startAngle          // Previous start angle
    	              , sat = this.startAngle         // Start angle
    	              , ea                            // Previous end angle
    	              , eat = sat + a                 // End angle
    	              , r = true;

    	          this.g.lineWidth = this.lineWidth;

    	          this.o.cursor
    	          && (sat = eat - 0.3)
    	          && (eat = eat + 0.3);

    	          if (this.o.displayPrevious) {
    	            ea = this.startAngle + this.angle(this.value);
    	            this.o.cursor
    	            && (sa = ea - 0.3)
    	            && (ea = ea + 0.3);
    	            this.g.beginPath();
    	            this.g.strokeStyle = this.previousColor;
    	            this.g.arc(this.xy, this.xy, this.radius - this.lineWidth, sa, ea, false);
    	            this.g.stroke();
    	          }
    	          this.g.beginPath();
    	          this.g.strokeStyle = r ? this.o.fgColor : this.fgColor;
    	          this.g.arc(this.xy, this.xy, this.radius - this.lineWidth, sat, eat, false);
    	          this.g.stroke();

    	          this.g.lineWidth = 2;
    	          this.g.beginPath();
    	          this.g.strokeStyle = this.o.fgColor;
    	          this.g.arc(this.xy, this.xy, this.radius - this.lineWidth + 1 + this.lineWidth * 2 / 3, 0, 2 * Math.PI, false);
    	          this.g.stroke();
    	          return false;
    	        }
    	      }
    	    });	
				 
					 $(".knob").knob({
				            /*change : function (value) {
				             //console.log("change : " + value);
				             },
				             release : function (value) {
				             console.log("release : " + value);
				             },
				             cancel : function () {
				             console.log("cancel : " + this.value);
				             },*/
				           format:function (value) {
				                return value+"%";
				            },
				            draw: function () {
				                // "tron" case
				                if (this.$.data('skin') == 'tron') {
				                    var a = this.angle(this.cv)  // Angle
				                        , sa = this.startAngle          // Previous start angle
				                        , sat = this.startAngle         // Start angle
				                        , ea                            // Previous end angle
				                        , eat = sat + a                 // End angle
				                        , r = true;

				                    this.g.lineWidth = this.lineWidth;
				                    this.o.cursor
				                    && (sat = eat - 0.3)
				                    && (eat = eat + 0.3);
				                    if (this.o.displayPrevious) {
				                        ea = this.startAngle + this.angle(this.value);
				                        this.o.cursor
				                        && (sa = ea - 0.3)
				                        && (ea = ea + 0.3);
				                        this.g.beginPath();
				                        this.g.strokeStyle = this.previousColor;
				                        this.g.arc(this.xy, this.xy, this.radius - this.lineWidth, sa, ea, false);
				                        this.g.stroke();
				                    }

				                    this.g.beginPath();
				                    this.g.strokeStyle = r ? this.o.fgColor : this.fgColor;
				                    this.g.arc(this.xy, this.xy, this.radius - this.lineWidth, sat, eat, false);
				                    this.g.stroke();

				                    this.g.lineWidth = 2;
				                    this.g.beginPath();
				                    this.g.strokeStyle = this.o.fgColor;
				                    this.g.arc(this.xy, this.xy, this.radius - this.lineWidth + 1 + this.lineWidth * 2 / 3, 0, 2 * Math.PI, false);
				                    this.g.stroke();

				                    return false;
				                }
				            }
				        });	 
				}
			},
			error:function() {
				//layer.alert('请求出错!');   
			}
		});
//        $(".knob").val(data.loyalty.progress_percentage).trigger("change");
        /* END JQUERY KNOB */
    });
</script>
</body>
</html>

