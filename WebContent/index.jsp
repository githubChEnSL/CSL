<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="CustomTag" prefix="custom"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>店铺管理系统</title>
<link rel="stylesheet" type="text/css"
	href="${pageContext.request.contextPath}/css/bootstrap.min.css">
<link rel="stylesheet" type="text/css"
	href="${pageContext.request.contextPath}/css/index.css">
<script type="text/javascript"
	src="${pageContext.request.contextPath}/js/jquery-2.2.3.min.js"></script>
<script type="text/javascript"
	src="${pageContext.request.contextPath}/js/bootstrap/bootstrap.js"></script>
<script type="text/javascript"
	src="${pageContext.request.contextPath}/js/bootstrap/bootstrap.min.js"></script>
<script type="text/javascript"
	src="${pageContext.request.contextPath}/js/index.js"></script>
</head>
<body>
	<!-- 导航栏 -->
	<div id="">
		<!-- 此处加载顶部导航栏 -->
		<!-- 导航栏 -->
		<nav class="navbar navbar-default navbar-fixed-top" role="navigation">
		<div class="container-fluid">
			<!-- 导航栏标题 -->
			<div class="navbar-header">
				<a href="javascript:void(0)" class="navbar-brand home">店铺管理系统后台</a>
			</div>
			<!-- 导航栏下拉菜单；用户信息与注销登陆 -->
			<div>
				<ul class="nav navbar-nav navbar-right">
					<li class="dropdown"><a href="#" class="dropdown-toggle"
						data-toggle="dropdown"> <span>欢迎&nbsp;</span> 用户名:<custom:name></custom:name></span>
							<span class="caret"></span>
					</a>
						<ul class="dropdown-menu">
							<li><a href="javascript:void(0)" class="menu_item"
								name="Exit.jsp">&nbsp;&nbsp;注销登录</a></li>
						</ul>
				</ul>
			</div>
		</div>
		</nav>
	</div>
	<div class="container-fluid" style="padding-left: 0px;">
		<div class="row">
			<!-- 左侧导航栏 -->
			<div id="sideBar" class="col-md-2 col-sm-3">
				<!--  此处加载左侧导航栏 -->
				<!-- 左侧导航栏  -->
				<div class="panel-group" id="accordion">
					<div class="panel panel-default" style="background-color: #f1f1f1">
						<a href="#collapse1" data-toggle="collapse"
							data-parent="#accordion">
							<div class="panel-heading">
								<div class="panel-title" class="parentMenuTitle collapseHead">
									门店管理
									<div class="pull-right">
										<span class="caret"></span>
									</div>
								</div>
							</div>
						</a>
						<div id="collapse1" class="panel-collapse collapse collapseBody">
							<div class="panel-body" style="background-color: #fff">
								<ul class="list-group">
									<a href="javascript:void(0)" id="" class="menu_item"
										name="StoresMangement.jsp">
										<button class="list-group-item">门店信息</button>
									</a>
								</ul>
							</div>
						</div>
					</div>
					<div class="panel panel-default" style="background-color: #f1f1f1">
						<a href="#collapse2" data-toggle="collapse"
							data-parent="#accordion" class="parentMenuTitle collapseHead">
							<div class="panel-heading">
								<h4 class="panel-title">
									员工管理
									<div class="pull-right">
										<span class="caret"></span>
									</div>
								</h4>
							</div>
						</a>
						<div id="collapse2" class="panel-collapse collapse collapseBody">
							<div class="panel-body" style="background-color: #fff">

								<ul class="list-group">
									<a href="javascript:void(0)" id="" class="menu_item"
										name="RegulatorsMangement.jsp">
										<li class="list-group-item">员工信息</li>
									</a>
								</ul>
							</div>
						</div>
					</div>
					<div class="panel panel-default" style="background-color: #f1f1f1">
						<a href="#collapse3" data-toggle="collapse"
							data-parent="#accordion" class="parentMenuTitle collapseHead">
							<div class="panel-heading">
								<h4 class="panel-title">
									会员管理
									<div class="pull-right	">
										<span class="caret"></span>
									</div>
								</h4>
							</div>
						</a>
						<div id="collapse3" class="panel-collapse collapse collapseBody">
							<div class="panel-body" style="background-color: #fff">
								<ul class="list-group">
									<a href="javascript:void(0)" id="" class="menu_item"
										name="UsersMangement.jsp">
										<li class="list-group-item">会员信息</li>
									</a>

								</ul>
							</div>
						</div>
					</div>
					<div class="panel panel-default" style="background-color: #f1f1f1">
						<a href="#collapse4" data-toggle="collapse"
							data-parent="#accordion" class="parentMenuTitle collapseHead">
							<div class='panel-heading'>
								<h4 class="panel-title">
									个人信息管理
									<div class="pull-right">
										<span class="caret"></span>
									</div>
								</h4>
							</div>
						</a>
						<div id="collapse4" class="panel-collapse collapse collapseBody">
							<div class="panel-body" style="background-color: #fff">
								<ul class="list-group">
									<a href="javascript:void(0)" id="" class="menu_item"
										name="PersonalMangement.jsp">
										<li class="list-group-item">个人信息</li>
									</a>
								</ul>
							</div>
						</div>
					</div>
					<div class="panel panel-default" style="background-color: #f1f1f1">
						<a href="#collapse5" data-toggle="collapse"
							data-parent="#accordion" class="parentMenuTitle collapseHead">
							<div class="panel-heading">
								<h4 class="panel-title">
									系统维护
									<div class="pull-right	">
										<span class="caret"></span>
									</div>
								</h4>
							</div>
						</a>
						<div id="collapse5" class="panel-collapse collapse collapseBody">
							<div class="panel-body" style="background-color: #fff">
								<ul class="list-group">
									<a href="javascript:void(0)" id="" class="menu_item" name="">
										<li class="list-group-item">登陆日志</li>
									</a>
									<a href="javascript:void(0)" class="menu_item" name="Exit.jsp">
										<li class="list-group-item">注销登陆</li>
									</a>
								</ul>
							</div>
						</div>
					</div>
				</div>
			</div>
			<!-- 面板区域 -->
			<div id="panel" class="col-md-10 col-sm-9">
				<!--  此处异步加载各个面板 -->
				<!-- 欢迎界面 -->
				<div class="panel panel-default">
					<!-- 面包屑 -->
					<ol class="breadcrumb">
						<li>主页</li>
					</ol>
					<div class="panel-body" style="background-color: #fff">
						<div class="row" style="margin-top: 100px; margin-bottom: 100px">
							<div class="col-md-1"></div>
							<div class="col-md-10" style="text-align: center">
								<div class="col-md-4 col-sm-4">
									<a href="javascript:void(0)" class="thumbnail shortcut">
										<h3 class="title">添加门店</h3>
									</a>
								</div>
								<div class="col-md-4 col-sm-4">
									<a href="javascript:void(0)" class="thumbnail shortcut">
										<h3 class="title">添加员工</h3>
									</a>
								</div>
								<div class="col-md-4 col-sm-4">
									<a href="javascript:void(0)" class="thumbnail shortcut">
										<h3 class="title">添加会员</h3>
									</a>
								</div>
							</div>
							<div class="col-md-1"></div>
						</div>
					</div>
				</div>
			</div>
		</div>
	</div>
</body>
</html>