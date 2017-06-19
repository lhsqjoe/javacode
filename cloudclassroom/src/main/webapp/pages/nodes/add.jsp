<%--
  Created by IntelliJ IDEA.
  User: qiao
  Date: 17-5-25
  Time: 下午11:59
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
    <meta charset="utf-8">
    <title>${PROJECT_NAME} | 服务器管理</title>
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
        <form  class="form-horizontal addUserForm">
            <div class="box-body">
                <div class="form-group">
                    <label for="userName" class="col-sm-3 col-xs-3 control-label">服务器名称：</label>

                    <div class="col-sm-9 col-xs-9">
                        <input type="text" name="userName" class="form-control" id="userName" placeholder="请输入服务器名称...">
                    </div>
                </div>
                <div class="form-group">
                    <label for="userPass" class="col-sm-3  col-xs-3 control-label">服务器ip：</label>

                    <div class="col-sm-9 col-xs-9">
                        <input type="text" name="ncIp" class="form-control" id="userPass" placeholder="请输入服务器ip...">
                    </div>
                </div>
                <div class="form-group">
                    <label for="userTel" class="col-sm-3  col-xs-3 control-label">ROOT密码：</label>

                    <div class="col-sm-9 col-xs-9">
                        <input type="text" name="userPass" class="form-control" id="userTel" placeholder="请输入密码...">
                    </div>
                </div>
                <div class="form-group">
                    <label for="userEmail" class="col-sm-3  col-xs-3 control-label">系统盘存储协议：</label>
                    <div class="col-sm-9 col-xs-9">
                        <select class="form-control" name="sysStoreFlag" id="sysStoreFlag">
					      <option value="0" selected="selected">本地存储</option>
					      <option value="1">NFS存储</option>
					    </select>
                    </div>
                </div>
                <div class="form-group" id="sharetr"style="display:none;" id="sharetr">
                    <label for="userEmail" class="col-sm-3  col-xs-3 control-label">系统盘路径：</label>
                    <div class="col-sm-9 col-xs-9">
                        <select class="form-control" name="sysStorePath">
						  <c:forEach var="item" items="${datanfsList}" >
	                    	<option value="${item.id}">${item.ip}:${item.dictionary}</option>
	                	</c:forEach>
					    </select>
                    </div>
                </div>
                <div class="form-group">
                    <label class="col-sm-3 col-xs-3 control-label">数据盘存储协议：</label>
                    <div class="col-sm-9 col-xs-9">
                        <select class="form-control" name="dataStoreFlag" id="dataStoreFlagId" >
					      <option value="0" selected="selected">本地存储</option>
                			<option value="1">NFS存储</option></select>
					    </select>
                    </div>
                </div>
                <div class="form-group" style="display:none;" id="datasharetr">
                    <label class="col-sm-3 col-xs-3 control-label">数据盘存储路径：</label>
                    <div class="col-sm-9 col-xs-9">
                        <select class="form-control" name="dataStorePath">
					      <c:forEach var="item" items="${datanfsList}" >
		                    <option value="${item.id}">${item.ip}:${item.dictionary}</option>
		                </c:forEach>
					    </select>
                    </div>
                </div>
                <div class="form-group" >
                    <label class="col-sm-3 col-xs-3 control-label">备份盘存储协议：</label>
                    <div class="col-sm-9 col-xs-9">
                        <select class="form-control" name="backupStoreFlag" id="backupStoreFlagId">
					      <option value="0" selected="selected">本地存储</option>
					      <option value="1">NFS存储</option>
					    </select>
                    </div>
                </div>
                <div class="form-group" id="bksharetr" id="sharetr"style="display:none;">
                    <label class="col-sm-3 col-xs-3 control-label">备份盘存储路径：</label>
                    <div class="col-sm-9 col-xs-9">
                        <select class="form-control" name="backupStoreFlag">
					      <c:forEach var="item" items="${bknfsList}" >
		                    <option value="${item.id}">${item.ip}:${item.dictionary}</option>
		                </c:forEach>
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
    	$("#sysStoreFlag").change(function(){
            if($(this).children('option:selected').val()==1){
                $("#sharetr").show();
             //   $("#typealert").empty();
            }else{
                $("#sharetr").hide();
            }
        });
    	$("#dataStoreFlagId").change(function(){
            if($(this).children('option:selected').val()==1){
                $("#datasharetr").show();
             //   $("#typealert").empty();
            }else{
                $("#datasharetr").hide();
            }
        });
    	$("#backupStoreFlagId").change(function(){
            if($(this).children('option:selected').val()==1){
                $("#bksharetr").show();
             //   $("#typealert").empty();
            }else{
                $("#bksharetr").hide();
            }
        });
        $(".addUserForm").validate({
            rules:{
                userName:{required:true,maxlength:15,pattern:/^([a-zA-Z0-9]+)$/},
                ncIp:{required:true,ipv4:true},
                userPass:{required:true},
            },
            messages: {
                userName:{pattern:"请输入字母或数字"},
                ncIp:{ipv4:"请输入正确的ip地址"},
            }
        })

    	$('.addUserForm').on("submit",function() {
    		        $(this).ajaxSubmit(
    		            {
    		                url: 'addNcJson.action',                 //默认是form的action
    		                type: 'post',               //默认是form的method（get or post）
    		                dataType: "json",           //html(默认), xml, script, json...接受服务端返回的类型
    		                //clearForm: true,          //成功提交后，清除所有表单元素的值
    		                //resetForm: true,          //成功提交后，重置所有表单元素的值
    		               // target: '#output',          //把服务器返回的内容放入id为output的元素中
    		                //timeout: 3000,               //限制请求的时间，当请求大于3秒后，跳出请求
    		                //提交前的回调函数
    		                beforeSubmit: function(arr,$form,options){
    		                    console.log("beforeSubmit",arr,$form,options)
    		                },
    		                //提交成功后的回调函数
    		                success: function(data,status,xhr,$form){
    		                	/*  layer.ready(function(){
        		               		 // layer.msg('很高兴一开场就见到你');
        		               	var index = layer.load(2, {
        		           			  shade: [0.1,'#00c0ef'] //0.1透明度的白色背景
        		           			});
        		               		});
    		                	layer.closeAll('loading') */
    		                    console.log("success",data,status,xhr,$form);
    		                    var jsonStr = eval('(' + data.jsonStr + ')'); ;
    		                    var flag = jsonStr.flag;
    		                    if("success"==flag){
    		                     	 layer.msg('添加成功！',{  icon: 0,
    		                    		  time: 1000}, function(){
    		                    			layer.closeAll();  
    		                    			parent.location.href='listNc.action';
    		                    		});
    		                      }else{
    		                     	 layer.msg('添加失败！',{
    		                     		  time: 1000}, function(){
    		                    			layer.closeAll();  
    		                    			parent.location.href='listNc.action';}
    		                     		  );
    		                      }
    		                },
    		                error: function(xhr, status, error, $form){
    		                    console.log("error",xhr, status, error, $form)
    		                },
    		                complete: function(xhr, status, $form){
    		                    console.log("complete",xhr, status, $form)
    		                }
    		            }
    		        );
    		        return false; //阻止表单默认提交
    		    });
    });
</script>
</body>
</html>

