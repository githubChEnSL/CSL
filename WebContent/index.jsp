<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="CustomTag" prefix="custom" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>主页</title>
<link rel="stylesheet" type="text/css" href="css/index.css">
<!-- 应用bootstrap的css -->
<link rel="stylesheet" type="text/css" href="css/bootstrap/bootstrap-grid.css">
<link rel="stylesheet" type="text/css" href="css/bootstrap/bootstrap-grid.min.css">
<link rel="stylesheet" type="text/css" href="css/bootstrap/bootstrap-reboot.css"/>
<link rel="stylesheet" type="text/css" href="css/bootstrap/bootstrap-reboot.min.css"/>
<link rel="stylesheet" type="text/css" href="css/bootstrap/bootstrap.css"/>
<link rel="stylesheet" type="text/css" href="css/bootstrap/bootstrap.min.css"/>

<script  src="js/jquery-2.2.3.min.js" type="text/javascript"></script>
<!-- 引用bootstrap的js -->
<script  src="js/bootstrap/bootstrap.bundle.js" type="text/javascript"></script>
<script  src="js/bootstrap/bootstrap.bundle.min.js" type="text/javascript"></script>
<script  src="js/bootstrap/bootstrap.js" type="text/javascript"></script>
<script  src="js/bootstrap/bootstrap.min.js" type="text/javascript"></script>
</head>
<body>
<div id="student">
	<!-- 系统页面头部-显示系统名称等信息 -->
	<div id="head">
		<div class="container">
			<div class="row">
				<div class="col-sm-12">
					<h3>学生信息管理系统</h3>
				</div>
			</div>
		</div>
	</div>
	<!-- 系统导航，显示当前主工作区对应的菜单目录信息 -->
	<div id="navigation">
		<button class="btn btn-default" onclick="welcome()">首页</button>
		<button class="btn btn-default">学生信息管理</button>
	</div>
	<!-- 系统主体，显示系统菜单栏和主工作区 -->
	<div id="body">
		<!-- 菜单，显示系统菜单在左侧 -->
		<div id="menu">
		</div>
		<!-- 主工作区 ，显示信息列表在右侧-->
		<div id="main">
		</div>
	</div>
	<!-- 页面尾部，显示版权信息、联系方式 -->
	<div id="footer">
		<custom:name></custom:name>
	</div>
</div>
</body>
</html>