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
</head>
<body>
	<!-- 导航栏 -->
	<div id="navBar">
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
						data-toggle="dropdown"> <span>欢迎&nbsp;</span> <span
							id="nav_userName">用户名:<custom:name></custom:name></span> <span
							class="caret"></span>
					</a>
						<ul class="dropdown-menu">
							<a href="javascript:void(0)" id="signOut"> &nbsp;&nbsp;注销登录</a></li>
				</ul>
				</li>
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
								<h4 class="panel-title" class="parentMenuTitle collapseHead">
									门店管理
									<div class="pull-right">
										<span class="caret"></span>
									</div>
								</h4>
							</div>
						</a>
							<div id="collapse1" class="panel-collapse collapse collapseBody">
								<div class="panel-body" style="background-color: #fff">
									<ul class="list-group">
										<a href="javascript:void(0)" id="entryTag" class="menu_item"
											name="pageForward/entryTag">
											<li class="list-group-item">标签操作</li>
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
						<div id="collapse2"
							class="panel-collapse collapse collapseBody">
							<div class="panel-body" style="background-color: #fff">

								<ul class="list-group">
										<a href="javascript:void(0)" id="" class="menu_item"
											name="pageForward/AssetInfoManagement">
											<li class="list-group-item">资产信息</li>
										</a>
									<a href="javascript:void(0)" id="" class="menu_item"
										name="pageForward/inventoryGoods">
										<li class="list-group-item">盘点记录</li>
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
										name="pageForward/userManagement">
										<li class="list-group-item">盘点员管理</li>
									</a>

								</ul>
							</div>
						</div>
					</div>
					<div class="panel panel-default" style="background-color: #f1f1f1">
						<a href="#collapse4" data-toggle="collapse"
							data-parent="#accordion" class="parentMenuTitle collapseHead">
							<div class="panel-heading">
								<h4 class="panel-title">
									个人信息管理
									<div class="pull-right	">
										<span class="caret"></span>
									</div>
								</h4>
							</div>
						</a>
						<div id="collapse4" class="panel-collapse collapse collapseBody">
							<div class="panel-body" style="background-color: #fff">
								<ul class="list-group">
									<shiro:hasRole name="管理员">
										<a href="javascript:void(0)" id="" class="menu_item"
											name="pageForward/excelManagement">
											<li class="list-group-item">导入表格信息管理</li>
										</a>
									</shiro:hasRole>
									<shiro:hasRole name="超级管理员">
										<a href="javascript:void(0)" id="" class="menu_item"
											name="pageForward/collegeInfoManagement">
											<li class="list-group-item">学校信息管理</li>
										</a>
										<a href="javascript:void(0)" id="" class="menu_item"
											name="pageForward/departInfoManagement">
											<li class="list-group-item">部门信息管理</li>
										</a>
									</shiro:hasRole>
									<shiro:hasRole name="管理员">
										<a href="javascript:void(0)" id="" class="menu_item"
											name="pageForward/storeInfoManagement">
											<li class="list-group-item">存放地信息管理</li>
										</a>
									</shiro:hasRole>
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
									<a href="javascript:void(0)" id="" class="menu_item"
										name="pageForward/passwordModification">
										<li class="list-group-item">更改密码</li>
									</a>
									<a href="javascript:void(0)" id="" class="menu_item"
										name="pageForward/userOperationManagement">

										<li class="list-group-item">系统日志</li>
									</a>
									<a href="javascript:void(0)" id="" class="menu_item"
										name="pageForward/accessRecordManagement">
										<li class="list-group-item">登陆日志</li>
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
										<div class="caption">
											<h3 class="title">添加门店</h3>
										</div>
									</a>
								</div>
								<div class="col-md-4 col-sm-4">
									<a href="javascript:void(0)" class="thumbnail shortcut"> 
										<div class="caption">
											<h3 class="title">添加员工</h3>
										</div>
									</a>
								</div>
								<div class="col-md-4 col-sm-4">
									<a href="javascript:void(0)" class="thumbnail shortcut">
										<div class="caption">
											<h3 class="title">添加会员</h3>
										</div>
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

	<!-- 提示消息模态框 -->
	<div class="modal fade" id="global_info_modal" table-index="-1"
		role="dialog" aria-labelledby="myModalLabel" aria-hidden="true">
		<div class="modal-dialog">
			<div class="modal-content">
				<div class="modal-header">
					<button class="close" type="button" data-dismiss="modal"
						aria-hidden="true">&times;</button>
					<h4 class="modal-title" id="myModalLabel">信息</h4>
				</div>
				<div class="modal-body">
					<div class="row">
						<div class="col-md-4 col-sm-4"></div>
						<div class="col-md-4 col-sm-4">
							<div id="info_success" class=" hide" style="text-align: center;">
								<img src="" alt="" style="width: 100px; height: 100px;">
							</div>
							<div id="info_error" style="text-align: center;">
								<img src="" alt="" style="width: 100px; height: 100px;">
							</div>
						</div>
						<div class="col-md-4 col-sm-4"></div>
					</div>
					<div class="row" style="margin-top: 10px">
						<div class="col-md-3"></div>
						<div class="col-md-6" style="text-align: center;">
							<h4 id="info_summary"></h4>
						</div>
						<div class="col-md-3"></div>
					</div>
					<dic class="row" style="margin-top: 10px">
					<div class="col-md-3"></div>
					<div class="col-md-6" style="text-align: center;">
						<p id='info_content'></p>
					</div>
					<div class="col-md-3"></div>
					</dic>
				</div>
				<div class="modal-footer">
					<button class="btn btn-default" type="button" data-dismiss="modal">
						<span>&nbsp;&nbsp;&nbsp;关闭&nbsp;&nbsp;&nbsp;</span>
					</button>
				</div>
			</div>
		</div>
	</div>

	<!-- 初始密码修改框 -->
	<div class="modal fade" id="init_password_modify" table-index="-1"
		role="dialog" aria-labelledby="myModalLabel" aria-hidden="true"
		data-backdrop="static" data-keyboard="false">
		<div class="modal-dialog">
			<div class="modal-content">
				<div class="modal-header">
					<h4 class="modal-title" id="myModalLabel">修改初始密码</h4>
				</div>
				<div class="modal-body">
					<div class="row" style="margin-top: 25px">
						<div class="col-md-1 col-sm-1"></div>
						<div class="col-md-10 col-sm-10">

							<form action="" class="form-horizontal" style="" role="form"
								id="form">
								<div class="form-group">
									<label for="" class="control-label col-md-4 col-sm-4">
										输入原密码: </label>
									<div class="col-md-6 col-sm-6">
										<input type="password" class="form-control" id="oldPassword"
											name="oldPassword">
									</div>
								</div>

								<div class="form-group">
									<label for="" class="control-label col-md-4 col-sm-4">
										输入新密码: </label>
									<div class="col-md-6 col-sm-6">
										<input type="password" class="form-control" id="newPassword"
											name="newPassword">
									</div>
								</div>

								<div class="form-group">
									<label for="" class="control-label col-md-4 col-sm-4">
										确认新密码: </label>
									<div class="col-md-6 col-sm-6 has-feedback">
										<input type="password" class="form-control"
											id="newPassword_re" name="newPassword_re">
									</div>
								</div>
								<input id="reset" type="reset" style="display: none">
							</form>

						</div>
						<div class="col-md-1 col-sm-1"></div>
					</div>

					<div class="row">
						<div class="col-md-1 col-sm-1"></div>
						<div class="col-md-10 col-sm-10">
							<div class="alert alert-info" style="margin-top: 25px">
								<p>登录密码修改规则说明：</p>
								<p>1.密码长度为6~16位，至少包含数字、字母、特殊符号中的两类，字母区分大小写</p>
								<p>2.密码不可与账号相同</p>
							</div>
						</div>
						<div class="col-md-1 col-sm-1"></div>
					</div>
				</div>
				<div class="modal-footer">
					<button class="btn btn-success" id="init_password_modify_submit">
						<span>&nbsp;&nbsp;&nbsp;确定修改&nbsp;&nbsp;&nbsp;</span>
					</button>
				</div>
			</div>
		</div>
	</div>

	<script type="text/javascript"
		src="${pageContext.request.contextPath}/js/jquery-2.2.3.min.js"></script>
	<script type="text/javascript"
		src="${pageContext.request.contextPath}/js/bootstrap/bootstrap.js"></script>
	<script type="text/javascript"
		src="${pageContext.request.contextPath}/js/bootstrap/bootstrap.min.js"></script>
	<script type="text/javascript"
		src="${pageContext.request.contextPath}/js/bootstrap/bootstrap.bundle.js"></script>
</body>
</html>