<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="CustomTag" prefix="custom" %>
<!DOCTYPE html>
<html>
<head>
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>错误信息</title>
<style type="text/css">
html,body{
	text-align: center;
}
</style>
</head>
<body class="text-center">
	<div class="card " style="height: 100%;width: 100%;">
		<div class="card-header">
			<h3><custom:errormessage></custom:errormessage></h3>
		</div>
		<a href="login.jsp">返回登陆</a>
		<div class="card-body bg-danger text-white">
		</div>
		<div class="card-footer">
			<p class="mt-2 mb-2 text-muted">&copy; 2017-2018</p>
		</div>
	</div>
</body>
</html>