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
    <title>${PROJECT_NAME} | 用户管理</title>
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
        <form action="addUserJson.action" method="post" class="form-horizontal addUserForm">
            <div class="box-body">
                <div class="form-group">
                    <label for="userName" class="col-sm-2 col-xs-2 control-label">用户名：</label>

                    <div class="col-sm-10 col-xs-10">
                        <input type="text" name="userName" class="form-control" id="userName" placeholder="请输入用户名...">
                    </div>
                </div>
                <div class="form-group">
                    <label for="userPass" class="col-sm-2  col-xs-2 control-label">密码：</label>

                    <div class="col-sm-10 col-xs-10">
                        <input type="password" name="userPass" class="form-control" id="userPass" placeholder="请输入密码...">
                    </div>
                </div>
                <div class="form-group">
                    <label for="userTel" class="col-sm-2  col-xs-2 control-label">手机号：</label>

                    <div class="col-sm-10 col-xs-10">
                        <input type="text" name="userTel" class="form-control" id="userTel" placeholder="请输入手机号...">
                    </div>
                </div>
                <div class="form-group">
                    <label for="userEmail" class="col-sm-2  col-xs-2 control-label">邮箱：</label>

                    <div class="col-sm-10 col-xs-10">
                        <input type="email" class="form-control" name="userEmail" id="userEmail" placeholder="请输入电子邮件地址...">
                    </div>
                </div>
                <div class="form-group">
                    <label class="col-sm-2 col-xs-2 control-label">选择角色</label>
                    <div class="col-sm-10 col-xs-10">
                        <select class="form-control userRole" name="userRoleId">
                            <option>--请选择角色--</option>
                        </select>
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
        $.ajax({
            type: "POST",
            dataType: "json",
            async: false,
            url: "listRoleJson.action",
            success: function (data) {
                console.debug(data.jsonStr);
                rs = eval('(' + data.jsonStr + ')');
                $(".userRole").html("");
                $(".userRole").html("<option value='0'>--全选--</option>");
                $.each(rs, function (i, item) {
                    $(".userRole").append(
                        "<option value=" + item.id + ">" + item.name+ "</option>");
                });
            }
        });
        $(".addUserForm").validate({
            rules:{
                userName:{required:true,maxlength:15,pattern:/^([a-zA-Z0-9]+)$/},
//                userPass:{required:true,minlength:6,maxlength:15},
                userTel:{required:true,minlength:11,maxlength:15,digits:true},
                userEmail:{required:true,maxlength:30},
                userRole:{required:true}
            },
            messages: {
                userName:{pattern:"请输入字母或数字"}
            }
        })

        var index = parent.layer.getFrameIndex(window.name); //获取当前窗体索引
        $('.addUserForm').ajaxForm(function (data) {
            var jsonStr = eval('(' + data.jsonStr + ')'); ;
            var flag = jsonStr.flag;
            console.debug("-----------------------"+flag)
            if("success"==flag){
             /*   layer.alert("用户添加成功", {icon: 1},function () {

                });*/

                layer.confirm("用户添加成功", {
                    icon: 1,
                    btn: ['返回列表', '继续添加'] //按钮
                }, function () {
                    parent.layer.close(index); //执行关闭
                    parent.location.href='listUser.action';
                }, function () {
                    $(".addUserForm")[0].reset();
                    layer.close();
                });
            }
        });
    });
</script>
</body>
</html>

