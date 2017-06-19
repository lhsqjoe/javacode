<%--
  Created by IntelliJ IDEA.
  User: qiao
  Date: 17-5-27
  Time: 下午12:19
  To change this template use File | Settings | File Templates.
--%>
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
    <title>${PROJECT_NAME} | 日志管理</title>
    <jsp:include page="common/include_head.jsp"/>
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
                404 Error Page
            </h1>
            <ol class="breadcrumb">
                <li><a href="#"><i class="fa fa-dashboard"></i> Home</a></li>
                <li><a href="#">Examples</a></li>
                <li class="active">404 error</li>
            </ol>
        </section>

        <!-- Main content -->
        <section class="content">
            <div class="error-page">
                <h2 class="headline text-yellow"> 404</h2>

                <div class="error-content">
                    <h3><i class="fa fa-warning text-yellow"></i> Oops! Page not found.</h3>

                    <p>
                        We could not find the page you were looking for.
                        Meanwhile, you may <a href="../../index.html">return to dashboard</a> or try using the search form.
                    </p>

                    <form class="search-form">
                        <div class="input-group">
                            <input type="text" name="search" class="form-control" placeholder="Search">

                            <div class="input-group-btn">
                                <button type="submit" name="submit" class="btn btn-warning btn-flat"><i class="fa fa-search"></i>
                                </button>
                            </div>
                        </div>
                        <!-- /.input-group -->
                    </form>
                </div>
                <!-- /.error-content -->
            </div>
            <!-- /.error-page -->
        </section>
        <!-- /.content -->
    </div>
    <!-- /.content-wrapper -->
    <jsp:include page="common/footer.jsp"/>ss
</div>
<!-- ./wrapper -->

<jsp:include page="common/include_scripts.jsp"/>
<!-- page script -->
</body>
</html>


