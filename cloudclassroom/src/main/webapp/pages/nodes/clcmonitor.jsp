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
    <title>${PROJECT_NAME} | 服务器详情</title>
    <jsp:include page="../common/include_head.jsp"/>
    <style type="text/css">
    th,td {
	text-align: center;
	vertical-align: middle;
}
    .knob {
    border:none
    }
    </style>
</head>
<body class="hold-transition skin-purple-light sidebar-mini">
<div class="wrapper">

    <jsp:include page="../common/header.jsp"/>
    <!-- Left side column. contains the logo and sidebar -->
    <jsp:include page="../common/sidebar.jsp"/>

    <!-- Content Wrapper. Contains page content -->
    <div class="content-wrapper">
        <!-- Content Header (Page header) -->
        <section class="content-header">
            <h1>
                <small>服务器管理</small>
            </h1>
            <ol class="breadcrumb">
                <li><a href="#"><i class="fa fa-user"></i> 云课堂</a></li>
                <li class="active">服务器详情</li>
            </ol>
        </section>
        <section class="content-header">
        <div class="panel-heading">
			<h4 class="panel-title" id="section-1">
				<a data-toggle="collapse" data-parent="#accordion" 
				   href="#collapseOne">
                		服务器资源信息
				</a>
			</h4>
		</div>
        </section>
        <!-- /.content -->
        <section class="content">
        
            <!-- row -->
            <div class="row">
                <div class="col-xs-12">
                    <!-- jQuery Knob -->
                    <div class="box box-primary" id="collapseOne">
                        <div class="box-header">
                            <i class="fa fa-pie-chart-o"></i>
                        </div>
                        <!-- /.box-header -->
                        <div class="box-body">
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
                                    <input id="memoryId"  data-readOnly=true type="text" class="knob knobserver" data-thickness="0.2" data-width="120" data-height="120" data-fgColor="#932ab6">
                                    <div class="knob-label">内存占用率</div>
                                    <small>
					                                        已使用：<small id="usedId"></small>MB<br/>
					                                        总资源：<small id="totalId"></small>MB
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

<!-- <div class="content-wrapper">
        Content Header (Page header)
        
    </div> -->


    <jsp:include page="../common/footer.jsp"/>
</div>
<!-- ./wrapper -->

<jsp:include page="../common/include_scripts.jsp"/>
<!-- page script -->
<script>
    $(function () {
    	//服务器监控信息
    	$.ajax({
			type : "POST",
			url : "findClcMonitor.action",
			dataType : "json",
			beforeSend:function(){
				layer.ready(function(){
		    		 // layer.msg('很高兴一开场就见到你');
		    	var index = layer.load(2, {
					  shade: [0.1,'#00c0ef'] //0.1透明度的白色背景
					});
		    		});
			},
			success : function(data) {
				layer.closeAll();
				var jsonNc = eval('(' + data.jsonStr + ')');
				if(jsonNc!=null){
					var cpu=parseInt(jsonNc.usedVmCpu/jsonNc.totalCpu*100)
					 $('#cpuId').val(cpu).trigger('change').trigger('change')
					$("#usedCpuId").text(jsonNc.usedVmCpu)
					$("#totalCpuId").text(jsonNc.totalCpu) 
					var memory=parseInt(jsonNc.usedMemory/jsonNc.totalMemory*100)
					$("#memoryId").val(memory).trigger('change')
					$("#usedId").text(jsonNc.usedMemory)
					$("#totalId").text(jsonNc.totalMemory)
					var diskMemory=parseInt(jsonNc.usedDiskMemory/jsonNc.totalDiskMemory*100)
					$("#diskMemoryId").val(diskMemory).trigger('change')
					$("#usedDiskMemory").text(parseFloat(jsonNc.usedDiskMemory/1024).toFixed(1))
					$("#totalDiskMemory").text(parseFloat(jsonNc.totalDiskMemory/1024).toFixed(1))
					$(".knob").knob({
				change : function (value) {
					alert(v);
				             },
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
			}
		});
        });
</script>
</body>
</html>

