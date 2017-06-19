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
    <title>${PROJECT_NAME} | 服务器管理</title>
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
                <li class="active">服务器管理</li>
            </ol>
        </section>

        <section class="content">
            <div class="row">
                <div class="col-xs-12">
                    <div class="box box-primary">
                        <div class="box-header">
                            <%--<h3 class="box-title">Data Table With Full Features</h3>--%>
                        </div>
                        <!-- /.box-header -->
                        <div class="box-body">
                            <div class="row">
                                <div class="col-md-4 col-xs-4">
                                    <button type="submit" class="btn btn-primary btn_add">添加服务器</button>&nbsp;
                                    <button type="submit" class="btn btn-primary btn_reboot">重启</button>&nbsp;
                                    <button type="submit" class="btn btn-primary btn_shutdown">关机</button>&nbsp;
                                    <button type="submit" class="btn btn-danger btn_del">删除</button>
                                </div>
                            </div>
                            <div class="row" style="margin-top: 15px;">
                                <div class="col-xs-12 col-md-12">
                                    <table id="example1"
                                           class="table table-striped table-bordered table-condensed table-responsive">
                                        <thead>
                                        <tr>
                                            <th></th>
                                            <th>序号</th>
                                            <th>服务器ip</th>
                                            <th>添加时间</th>
                                            <th>当前状态</th>
                                            <th>操作</th>
                                        </tr>
                                        </thead>
                                    </table>
                                </div>
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
        <section class="content-header">
        <div class="panel-heading">
			<h4 class="panel-title" id="section-1">
				<a data-toggle="collapse" data-parent="#accordion" 
				   href="#collapseOne">
                		服务器配置信息
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
                            <table class="table">
								<thead>
									<tr class="active">
										<th>CPU型号</th>
										<th>内存</th>
										<th>网卡1</th>
										<th>网卡2</th>
									</tr>
								</thead>
								<tbody>
									<tr class="success">
										<td id="ncCpu"></td>
										<td id="ncMemory"></td>
										<td id="ncEth0"></td>
										<td id="ncEth1"></td>
									</tr>
								</tbody>
						</table>
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
    	var datatable=$('#example1').DataTable({
            /* data: tmp, */
            "bProcessing": true,
            "bServerSide": true,//当datatable获取数据时候是否显示正在处理提示信息。
            "sAjaxSource": 'listNcJson.action',
            //"sAjaxSource": 'User_list.action',
            'bFilter': false,  //是否使用内置的过滤功能
            "bPaginate": true, //翻页功能
            "bLengthChange": false, //改变每页显示数据数量
            "bSort": false, //排序功能
            "bInfo": true,//页脚信息ss
            //"bAutoWidth": true,//自动宽度
            "sServerMethod": "POST",
            "fnServerParams": function (aoData) {
            },
            "fnServerData": function (sSource, aoData, fnCallback) {
                $.ajax({
                    "dataType": "json",
                    "type": "POST",
                    "url": sSource,
                    "data": aoData,
                    "success": function (resp) {
                        console.log(resp);
//                        alert(resp.json);
                        var jo = eval('(' + resp.jsonStr + ')');
                        fnCallback(jo);
                    }
                });

            },
            columns:[
                {data: "ip",
                	"render": function (obj) {
                        var str = '<input type="checkbox" class="ncId" name="ncId" value="' + obj + '"/>';
                        return str;
                    }},
                     {data: null},
                {data: "ip"},
                {data: "createTime",
                	"render": function (obj) {
                        if (obj != null) {
                            var ltime = new Date(obj)
                            res = ltime.Format("yyyy-MM-dd hh:mm:ss");
//                            res = ltime.getFullYear() + "-" + (ltime.getMonth() + 1) + "-" + ltime.getDate() + " " + ltime.getHours() + ":" + ltime.getMinutes() + ":" + ltime.getSeconds();
                        } else {
                            res = "";
                        }
                        return res;
                    }
                	},
                {data: "state"},
            ],
            //序号
            "fnDrawCallback": function(){
            	　　var api = this.api();
            	　　var startIndex= api.context[0]._iDisplayStart;//获取到本页开始的条数
            	　　api.column(1).nodes().each(function(cell, i) {
            	　　　　cell.innerHTML = startIndex + i + 1;
            	　　}); 
            	},
            columnDefs:[{
                targets: 5,
                render: function (data, type, row, meta) {
                    return '<a href="#section-1" onclick=findNc("' + row.ip + '")  class="btn btn-primary " id="findId" value="' + row.id + '" >查看</a>&nbsp;&nbsp;<button type="submit"  onclick=rebootAndShudownNc("' + row.ip + '","1")  class="btn btn-primary" id="rebootId" value="' + row.ip + '" >重启</button>&nbsp;&nbsp;<button type="submit" class="btn btn-primary" onclick=rebootAndShudownNc("' + row.ip + '","2") id="shutdownId" value="' + row.id + '" >关机</button>&nbsp;&nbsp;<button type="submit" class="btn btn-danger" onclick=delNc("' + row.ip + '") id="delId" value="' + row.id + '" >删除</button>';
                }
            },
                { "orderable": false, "targets": 4 },
            ],
            "sDom": "<'row'<'col-md-6'l><'col-md-6'f>r>t<'row'<'col-md-12'i><'col-md-12 center-block'p>>",
//          "sPaginationType": "bootstrap",
          "sPaginationType": "full_numbers",
          "oLanguage": {
              "sProcessing": "处理中...",
              "sLengthMenu": "显示 _MENU_ 项结果",
              "sZeroRecords": "没有匹配结果",
              "sInfo": "显示第 _START_ 至 _END_ 项结果，共 _TOTAL_ 项",
              "sInfoEmpty": "显示第 0 至 0 项结果，共 0 项",
              "sInfoFiltered": "(由 _MAX_ 项结果过滤)",
              "sInfoPostFix": "",
              //"sSearch": "搜索:",
              //"sSearch": "推荐人名称:",
              "sUrl": "",
              "sEmptyTable": "表中数据为空",
              "sLoadingRecords": "载入中...",
              "sInfoThousands": ",",
              "oPaginate": {
                  "sFirst": "首页",
                  "sPrevious": "上页",
                  "sNext": "下页",
                  "sLast": "末页"
              },
              "oAria": {
                  "sSortAscending": ": 以升序排列此列",
                  "sSortDescending": ": 以降序排列此列"
              }
          }
        });

        /**
         * 查询
         */
        $(".btn_shutdown").click(function () {
        	var ncIp="";
            $('input[name="ncId"]:checked').each(function(){
            	ncIp+=$(this).val()+" ";
            })
           if (ncIp == null || ncIp == "") {
                layer.alert("请选择需要关闭的服务器");
            } else {
                layer.confirm("确认关闭该服务器", {
                    icon: 3,
                    btn: ['确认', '取消'] //按钮
                }, function () {
                    $.post('rebootAndShudownNc.action', {'ncIp': ncIp,'state':'2'}, function (data) {
                        var jsonStr = eval('(' + data.jsonStr + ')');
                        var flag = jsonStr.flag;
                        if (flag>0) {
                            layer.msg("关闭成功个数"+flag, {icon: 1, time: 1000}, function () {
                                datatable.draw();
                            });
                        }else{
                        	layer.msg("重启失败", {icon: 1, time: 1000}, function () {
                                datatable.draw();
                            });
                        }
                    });
                }, function () {
                    layer.msg('已取消', {icon: 1,time:1000});
                });
            }
        });

        $(".btn_add").click(function () {
            layer.open({
                type: 2,
                area: ['650px', '500px'],
                fixed: false, //不固定
                maxmin: true,
                content: 'addNc.action'
            });
        });

        $(".btn_reboot").click(function () {
        	var ncIp="";
            $('input[name="ncId"]:checked').each(function(){
            	ncIp+=$(this).val()+" ";
            })
           if (ncIp == null || ncIp == "") {
                layer.alert("请选择需要重启的服务器");
            } else {
                layer.confirm("确认重启该服务器", {
                    icon: 3,
                    btn: ['确认', '取消'] //按钮
                }, function () {
                    $.post('rebootAndShudownNc.action', {'ncIp': ncIp,'state':'1'}, function (data) {
                        var jsonStr = eval('(' + data.jsonStr + ')');
                        var flag = jsonStr.flag;
                        if (flag>0) {
                            layer.msg("重启成功个数"+flag, {icon: 1, time: 1000}, function () {
                                datatable.draw();
                            });
                        }else{
                        	layer.msg("重启失败", {icon: 1, time: 1000}, function () {
                                datatable.draw();
                            });
                        }
                    });
                }, function () {
                    layer.msg('已取消', {icon: 1,time:1000});
                });
            }
        });
        
        $("#delId").click(function(){
        	var ncip=$(this).val();
        	alert(ncip);
        });

        $(".btn_del").click(function () {
            var ncIp="";
            $('input[name="ncId"]:checked').each(function(){
            	ncIp+=$(this).val()+" ";
            })
           if (ncIp == null || ncIp == "") {
                layer.alert("请选择需要删除的服务器");
            } else {
                layer.confirm("确认删除该服务器", {
                    icon: 3,
                    btn: ['确认', '取消'] //按钮
                }, function () {
                    $.post('delNcJson.action', {'ncIp': ncIp}, function (data) {
                        var jsonStr = eval('(' + data.jsonStr + ')');
                        var flag = jsonStr.flag;
                        if (flag>0) {
                            layer.msg("删除成功个数"+flag, {icon: 1, time: 1000}, function () {
                                datatable.draw();
                            });
                        }else{
                        	layer.msg("删除失败", {icon: 1, time: 1000}, function () {
                                datatable.draw();
                            });
                        }
                    });
                }, function () {
                    layer.msg('已取消', {icon: 1,time:1000});
                });
            } 
        });
    });
    
    
    
    
    
    function rebootAndShudownNc(ip,state){
    	layer.confirm("确认重启该服务器", {
            icon: 3,
            btn: ['确认', '取消'] //按钮
        }, function () {
            $.post('rebootAndShudownNc.action', {'ncIp': ip,'state':state}, function (data) {
                var jsonStr = eval('(' + data.jsonStr + ')');
                var flag = jsonStr.flag;
                if (flag>0) {
                    layer.msg("重启成功个数:"+flag, {icon: 1, time: 1000}, function () {
                    	parent.location.href='listNc.action';
                    });
                }else{
                	layer.msg("重启失败", {icon: 1, time: 1000}, function () {
                		parent.location.href='listNc.action';
                    });
                }
            });
        }, function () {
            layer.msg('已取消', {icon: 1,time:1000});
        });
    }
    
    function delNc(ip){
    	layer.confirm("确认删除该服务器", {
            icon: 3,
            btn: ['确认', '取消'] //按钮
        }, function () {
            $.post('delNcJson.action', {'ncIp': ip}, function (data) {
                var jsonStr = eval('(' + data.jsonStr + ')');
                var flag = jsonStr.flag;
                if (flag>0) {
                    layer.msg("删除成功", {icon: 1, time: 1000}, function () {
                    	parent.location.href='listNc.action';
                    });
                }else{
                	layer.msg("删除失败", {icon: 1, time: 1000}, function () {
                		parent.location.href='listNc.action';
                    });
                }
            });
        }, function () {
            layer.msg('已取消', {icon: 1,time:1000});
        });
    }
    
    function findNc(ip){
    	//服务器监控信息
    	$.ajax({
			type : "POST",
			url : "findNcMonitor.action",
			data:{"ncIp":ip},
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
					$("#ncCpu").text(jsonNc.ncCpu)
					$("#ncMemory").text(parseFloat(jsonNc.totalMemory/1024).toFixed(1)+'GB')
					$("#ncEth0").text('速率'+jsonNc.ncEth0)
					$("#ncEth1").text('速率'+jsonNc.ncEth1)
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
    	//alert(ip);
    }
</script>
</body>
</html>

