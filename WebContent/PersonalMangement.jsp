<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>个人信息管理</title>
</head>
<script type="text/javascript">
	var UserNum = "";
	$(function() {
		Update();//修改会员信息
		refresh();//刷新列表
	})
	//刷新操作
	function refresh() {
		$("#userList").html("");
		var data = {
			action : "all"
		}
		$.ajax({
					url : "UsersMangement",
					type : "post",
					data : data,
					dataType : "json",
					contentType : "application/json",
					success : function(data) {
						//获取后台商店信息 
						var users = data.rows;
						$("#userList")
								.append(
										"<tr><td>会员编号</td><td>会员名称</td><td>会员等级</td><td>操作</td></tr>");
						$
								.each(
										users,
										function(key, value) {
											var row = JSON
													.stringify(users[key]);
											$("#userList")
													.append(
															"<tr><td>"
																	+ users[key].UserNum
																	+ "</td><td>"
																	+ users[key].UserName
																	+ "</td><td>"
																	+ users[key].UserRoleName
																	+ "</td><td><button class='btn btn-info' onclick='updateUser("
																	+ row
																	+ ")'><span>编辑</span></button>"
																	+ "  "
																	+ "<button class='btn btn-danger' onclick='deleteUser("
																	+ row
																	+ ")'><span>删除</span></button></td></tr>");
										})
					},
					error : function(data) {
						alert("请求失败");
					}
				})
	}

	//修改操作
	function Update() {
		$('#updatebut').click(function() {
			var data = {
				action : "update",
				UserNum : $('#updateuserid').val(),
				UserName : $('#updateuserName').val(),
				RoleName : $('#updateRoleName').val()
			}
			//请求删除
			//请求添加
			$.ajax({
				type : "POST",
				url : "UsersMangement",
				data : data,
				async : true,
				success : function(data) {
					//刷新列表
					refresh();
					$('#updateModal').modal('hide');
					$('#tipsmsg').html(data.msg);
					$('#TipsModal').modal('show');
				},
				error : function(data) {
					$('#addModal').modal('hide');
					$('#tipsmsg').html("请求错误");
					$('#TipsModal').modal('show');
				}
			})
		})
	}
</script>
<body>
	<div class="panel panel-default">
		<ol class="breadcrumb">
			<li>会员信息管理</li>
		</ol>
		<div class="panel-body">
			<div class="row">
				<div class="col-md-3 col-sm-3">
					<select name="" id="Store_select" class="form-control ">
						<option value="">所有</option>
					</select>
				</div>
				<div class="col-md-6 col-sm-6">
					<div>
						<div class="col-md-2 col-sm-2">
							<button id="search_button" class="btn btn-success">
								<span>查询</span>
							</button>
						</div>
					</div>
				</div>
			</div>
			<div class="row" style="margin-top: 25px">
				<div class="col-md-5">
					<button class="btn btn-sm btn-default" id="add_users">
						<span>添加会员</span>
					</button>
				</div>
				<div class="col-md-5"></div>
			</div>

			<div class="row" style="margin-top: 15px">
				<div class="col-md-12" align="center">
					<table id="userList" style="text-align: center;"
						class="table table-striped"></table>
				</div>
			</div>
		</div>
	</div>
</body>
</html>