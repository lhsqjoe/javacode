<%--
  Created by IntelliJ IDEA.
  User: qiao
  Date: 17-5-26
  Time: 下午1:43
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<aside class="main-sidebar">
    <!-- sidebar: style can be found in sidebar.less -->
    <section class="sidebar">
        <!-- sidebar menu: : style can be found in sidebar.less -->
        <ul class="sidebar-menu" data-widget="tree">
            <li class="header"></li>
            <!--自定义菜单开始-->
            <!--服务器管理-->
           <li class="treeview">
                <a href="#">
                    <i class="fa fa-server"></i> <span>服务器管理</span>
                    <span class="pull-right-container">
	              <i class="fa fa-angle-left pull-right"></i>
	            </span>
                </a>
                 <ul class="treeview-menu">
                    <li><a href="listNc.action"><i class="fa fa-circle-o"></i>服务器管理</a></li>
                    <li><a href="findClc.action"><i class="fa fa-circle-o"></i>服务器详情</a></li>
                </ul>
            </li>

            <!--课程管理-->
            <li>
                <a href="#">
                    <i class="fa fa-book"></i> <span>课程管理</span>
                </a>
            </li>
            <!--桌面管理-->
            <li>
                <a href="tolistInstance.action">
                    <i class="fa fa-desktop"></i> <span>桌面管理</span>
                </a>
            </li>
            <!--终端管理-->
            <li>
                <a href="#">
                    <i class="fa fa-laptop"></i> <span>终端管理</span>
                </a>
            </li>

            <!--集群管理-->
            <li>
                <a href="listCluster.action">
                    <i class="fa fa-cloud"></i> <span>集群管理</span>
                </a>
            </li>
            <!-- 存储管理-->
            <li>
                <a href="listStorage.action">
                    <i class="fa fa-database"></i>
                    <span>存储管理</span>
                </a>
            </li>
            <!-- 网络管理-->
            <li class="treeview">
                <a href="#">
                    <i class="fa fa-internet-explorer"></i>
                    <span>网络管理</span>
                    <span class="pull-right-container">
              <i class="fa fa-angle-left pull-right"></i>
            </span>
                </a>
                <ul class="treeview-menu">
                    <li><a href="listNetNode.action"><i class="fa fa-circle-o"></i>网络节点</a></li>
                    <li><a href="listIpPool.action"><i class="fa fa-circle-o"></i> IP地址池</a></li>
                </ul>
            </li>
            <!-- 系统管理-->
            <li class="treeview">
                <a href="#">
                    <i class="fa  fa-gear"></i>
                    <span>系统管理</span>
                    <span class="pull-right-container">
              <i class="fa fa-angle-left pull-right"></i>
            </span>
                </a>
                <ul class="treeview-menu">
                    <li><a href="listUser.action"><i class="fa fa-circle-o"></i>用户管理</a></li>
                    <li><a href="listUserRole.action"><i class="fa fa-circle-o"></i>角色管理</a></li>
                    <li><a href="listMenu.action"><i class="fa fa-circle-o"></i>权限管理</a></li>
                    <li><a href="listLog.action"><i class="fa fa-circle-o"></i>日志管理</a></li>
                    <li><a href="#"><i class="fa fa-circle-o"></i> 产品授权</a></li>
                    <li><a href="listTerminalSoftwareInfo.action "><i class="fa fa-circle-o"></i> 终端升级</a></li>
                </ul>
            </li>
            <!--/.自定义菜单结束-->
        </ul>
    </section>
    <!-- /.sidebar -->
</aside>
