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
    <title>${PROJECT_NAME} | 权限管理</title>
    <jsp:include page="../common/include_head.jsp"/>
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
                <small>权限管理</small>
            </h1>
            <ol class="breadcrumb">
                <li><a href="#"><i class="fa fa-user"></i> 云课堂</a></li>
                <li class="active">权限管理</li>
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
                                <div class="col-md-3 col-xs-3 col-md-offset-1 col-xs-offset-1">
                                    <div class="form-group form-inline">
                                        <label for="menuName"
                                               class="control-label">权限名称：</label>
                                        <input type="text" class="form-control input-sm menuName" id="menuName"/>
                                    </div>
                                </div>
                                <div class="col-md-3 col-xs-3">
                                    <div class="form-group form-inline">
                                        <label for="reservationtime" class="control-label">时间：</label>
                                        <input type="text" class="form-control input-sm" id="reservationtime">
                                        <!-- /.input group -->
                                    </div>
                                </div>
                                <div class="col-md-4 col-xs-4">
                                    <button type="submit" class="btn btn-primary btn_search">查询</button>
                                    <button type="submit" class="btn btn-primary btn_add">添加</button>
                                    <button type="submit" class="btn btn-primary btn_update">修改</button>
                                    <button type="submit" class="btn btn-primary btn_del">删除</button>
                                </div>
                            </div>
                            <div class="row" style="margin-top: 15px;">
                                <div class="col-xs-12 col-md-12">
                                    <table id="example1"
                                           class="table table-striped table-bordered table-condensed table-responsive">
                                        <thead>
                                        <tr>
                                            <th>权限编号</th>
                                            <th>权限名称</th>
                                            <th>权限地址</th>
                                            <th>css样式</th>
                                            <th>创建时间</th>
                                            <th>更新时间</th>
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
        <!-- /.content -->
    </div>
    <!-- /.content-wrapper -->

    <jsp:include page="../common/footer.jsp"/>
</div>
<!-- ./wrapper -->

<jsp:include page="../common/include_scripts.jsp"/>
<!-- page script -->
<script>
    $(function () {
        var datatable = $("#example1").DataTable({
            "bProcessing": true,
            "bServerSide": true,//当datatable获取数据时候是否显示正在处理提示信息。
            "sAjaxSource": 'listMenuJson.action',
            //"sAjaxSource": 'User_list.action',
            'bFilter': false,  //是否使用内置的过滤功能
            "bPaginate": true, //翻页功能
            "bLengthChange": false, //改变每页显示数据数量
            "bSort": false, //排序功能
            "bInfo": true,//页脚信息ss
            //"bAutoWidth": true,//自动宽度
            "sServerMethod": "POST",
            "fnServerParams": function (aoData) {
                aoData.push(
                    {"name": "menuName", "value": $(".menuName").val()},
                    {"name": "dateRange", "value": $("#reservationtime").val()}
                    );
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

            "aoColumns": [
                {
                    "mDataProp": "id",
                    "render": function (obj) {
                        var str = '<input type="radio" class="menuID" name="menuID" value="' + obj + '"/>&nbsp;&nbsp;' + obj;
                        return str;
                    }
                },
                {
                    "mDataProp": "name"
                },
                {
                    "mDataProp": "url"

                },
                {
                    "mDataProp": "iconskin"

                },
                {
                    "mDataProp": "createDatetime",
                    "render": function (obj) {
                        if (obj != null) {
                            var ltime = new Date(obj)
                            res = ltime.Format("yyyy-MM-dd hh:mm:ss");
                        } else {
                            res = "";
                        }
                        return res;
                    }

                },
                {
                    "mDataProp": "updateDatetime",
                    "render": function (obj) {
                        if (obj != null) {
                            var ltime = new Date(obj)
                            res = ltime.Format("yyyy-MM-dd hh:mm:ss");
                        } else {
                            res = "";
                        }
                        return res;
                    }

                }

            ],
            "aoColumnDefs": [{
                sDefaultContent: '',
                aTargets: ['_all']
            }],
            //"scrollY": "50px",
            //"scrollCollapse": "true",//垂直滚动条
            //"scrollX": true,//水平滚动条
            "sDom": "<'row'<'col-md-6'l><'col-md-6'f>r>t<'row'<'col-md-12'i><'col-md-12 center-block'p>>",
//            "sPaginationType": "bootstrap",
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
//        $(".dataTables_filter input").addClass("form-control input-sm u_search");
        //$(".dataTables_filter input").attr("placeholder","输入用户名");
//        $(".dataTables_length select").addClass("form-control input-sm");



        /**
         * 查询
         */
        $(".btn_search").click(function () {
            datatable.draw();
        });

        $(".btn_add").click(function () {
            layer.open({
                type: 2,
                area: ['700px', '530px'],
                fixed: false, //不固定
                maxmin: true,
                content: 'addMenu.action'
            });
        });

        $(".btn_update").click(function () {
            var menuID = $('input[name="menuID"]:checked').val();
            console.debug("menuID:"+menuID);
            if (menuID == null || menuID == "") {
                layer.alert("请选择需要修改的权限编号");
            } else {
                layer.open({
                    type: 2,
                    area: ['700px', '530px'],
                    fixed: false, //不固定
                    maxmin: true,
                    content: "updateMenu.action?menuId="+menuID
                });
            }


        });

        $(".btn_del").click(function () {
            var menuID = $('input[name="menuID"]:checked').val();
            console.debug("menuID:"+menuID);
            if (menuID == null || menuID == "") {
                layer.alert("请选择需要删除的权限");
            } else {
                layer.confirm("确认删除该权限", {
                    icon: 3,
                    btn: ['确认', '取消'] //按钮
                }, function () {
                    $.post('delMenuJson.action',{'menuId':menuID},function (data) {
                        var jsonStr = eval('(' + data.jsonStr + ')'); ;
                        var flag = jsonStr.flag;
                        if("success"==flag){
                            layer.msg("删除成功!",{icon:1,time: 1000},function() {
                                datatable.draw();
                            });
                        }

                    });
                }, function () {
                    layer.msg('已取消', {icon: 1});
                });
            }

        });


    });
    //Date range picker with time picker
    $('#reservationtime').daterangepicker({
        locale:{
//            format:"MM/DD/YYYY HH:mm",
            applyLabel:"确定",
            cancelLabel:"取消",
            monthNames:["一月", "二月", "三月", "四月", "五月", "六月", "七月", "八月", "九月", "十月", "十一月", "十二月"],
            daysOfWeek:["日", "一", "二", "三", "四", "五", "六"],
        },
        startDate:moment().startOf('year'),
        endDate:moment().endOf('year'),
        autoApply:true
//        timePicker:true,
//        timePicker24Hour:true,
//        timePickerIncrement:5
    });
</script>
</body>
</html>
