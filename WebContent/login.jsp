<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>用户登陆</title>
<link href="css/bootstrap/bootstrap.css" rel="stylesheet">
<link href="css/login.css" rel="stylesheet">
</head>
<body>
	<div class="login_div">
		<form action="LoginServlet" method="post">
			<h2>用户登陆</h2>
			<div class="form-group">
				<input class="form form-control" type="text" placeholder="用户名"
					name="userName" maxlength="20" />
			</div>
			<div class="form-group">
				<input class="form form-control" type="password" placeholder="密码"
					name="password" maxlength="16" />
			</div>
			<div class="form-group">
				<label class="checkbox"> <input type="checkbox"
					name="remember"/>记住密码
				</label>
			</div>
			<div class="form-group">
				<input type="submit" class="btn btn-info col-sm-12" value="登陆 " />
			</div>
		</form>
	</div>
</body>
</html>