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
<body>
<div class="container-fluid">
    <div class="box box-info">
        <div class="box-header with-border">
            <%--<h3 class="box-title">Horizontal Form</h3>--%>
        </div>
        <!-- /.box-header -->
        <!-- form start -->
        <form action="updateMenuJson.action" method="post" class="form-horizontal updateMenuForm">
            <input type="hidden" name="menuId" value="${menuInfo.id}">
            <input type="hidden" name="pid" id="menuPid" value="${menuInfo.pid}">
            <div class="box-body">
                <div class="form-group">
                    <label for="menuName" class="col-sm-2 col-xs-2 control-label">权限名称：</label>

                    <div class="col-sm-10 col-xs-10">
                        <input type="text" name="menuName" class="form-control" id="menuName" value="${menuInfo.name}">
                    </div>
                </div>
                <div class="form-group">
                    <label for="url" class="col-sm-2  col-xs-2 control-label">权限地址：</label>

                    <div class="col-sm-10 col-xs-10">
                        <input type="text" name="url" class="form-control" id="url" value="${menuInfo.url}">
                    </div>
                </div>
                <div class="form-group">
                    <label for="iconskin" class="col-sm-2  col-xs-2 control-label">css样式：</label>

                    <div class="col-sm-10 col-xs-10">
                        <input type="text" name="iconskin" class="form-control" id="iconskin"
                               value="${menuInfo.iconskin}">
                    </div>
                </div>
                <div class="form-group">
                    <label for="sn" class="col-sm-2  col-xs-2 control-label">排序编号：</label>

                    <div class="col-sm-10 col-xs-10">
                        <input type="number" class="form-control" name="sn" id="sn" value="${menuInfo.sn}">
                    </div>
                </div>
                <div class="form-group">
                    <label class="col-sm-2 col-xs-2 control-label">父级菜单:</label>
                    <div class="col-sm-10 col-xs-10">
                        <div id="treeView"></div>
                    </div>

                </div>
            </div>
            <!-- /.box-body -->
            <div class="box-footer">
                <button type="reset" class="btn btn-default">重置</button>
                <button type="submit" class="btn btn-primary pull-right btn_submit">确定</button>
            </div>
            <!-- /.box-footer -->
        </form>
    </div>
    <!-- /.box -->
</div>
<!-- ./wrapper -->

<jsp:include page="../common/include_scripts.jsp"/>
<!-- page script -->
<script>
    $(function () {
        var tree;
        $.ajax({
            type: "POST",
            dataType: "json",
            async: false,
            url: "listTreeJson.action",
            success: function (data) {
                console.debug(data.jsonStr);
                tree = eval('(' + data.jsonStr + ')');
            }
        });
        $('#treeView').treeview({
                expandIcon: 'glyphicon glyphicon-chevron-right',
                collapseIcon: 'glyphicon glyphicon-chevron-down',
                selectedBackColor: '#FFFFFF',
                selectedColor: '#428bca',
                showBorder: false,
//                showCheckbox:true,
                data: tree,
                onNodeSelected: function (event, node) {
                    console.debug("node.id" + node.id)
                    $("#menuPid").val(node.id);
                }
            }
        );
        $(".updateMenuForm").validate({
            rules: {
                menuName: {required: true, maxlength: 20},
                iconskin: {required: true, maxlength: 50},
                sn: {required: true, minlength: 1, maxlength: 2, digits: true},
            }
        })

        var index = parent.layer.getFrameIndex(window.name); //获取当前窗体索引
        $('.updateMenuForm').ajaxForm(function (data) {
            var jsonStr = eval('(' + data.jsonStr + ')');
            var flag = jsonStr.flag;
            console.debug("-----------------------" + flag)
            if ("success" == flag) {
                /*   layer.alert("用户添加成功", {icon: 1},function () {

                 });*/

                layer.msg("修改成功", {
                    icon: 1,
                    time: 1000
                }, function () {
                    parent.layer.close(index); //执行关闭
                    parent.location.href = 'listMenu.action';
                });
            }
        });
    });
</script>
</body>
</html>

