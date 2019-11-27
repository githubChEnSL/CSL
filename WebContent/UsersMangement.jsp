<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>会员信息管理</title>
</head>
<script type="text/javascript">
	var UserNum = "";
	$(function() {
		search();
		addUser(); //添加会员信息框
		Add();//添加会员信息
		Delete();//删除会员信息
		Update();//修改会员信息
		refresh();//刷新列表

		//监控查询条件
		$('#select_type').change(function() {
			var type = $('#select_type').val();
			if (type == "所有") {
				$('#select_Userid').attr("disabled", "disabled");
				$('#select_Userid').val("");
			} else if (type == "编号") {
				$('#select_Userid').removeAttr("disabled");
				$('#select_Userid').val("");
			}
		})
	})
	//刷新操作
	function refresh() {
		$("#userList").html("");
		var data = {
			action : "all",
			Userid : $('#select_Userid').val()
		}
		$
				.ajax({
					url : "UsersMangement",
					type : "post",
					data : data,
					dataType : "json",
					success : function(data) {
						//获取后台商店信息 
						var users = data.rows;
						$('#number').html("共" + users.length + "条数据");
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
	//查询会员信息
	function search() {
		$('#search_button').click(function() {
			refresh();
		})
	}
	//弹出添加框
	function addUser() {
		$('#add_users').click(function() {
			$('#addModal').modal('show');
		})
	}
	//弹出删除框
	function deleteUser(row) {
		$('#deleteModal').modal('show');
		UserNum = row.UserNum;
		$('#deletemsg').html("是否删除：" + row.UserName + "?");
	}
	//弹出修改框
	function updateUser(row) {
		$('#updateModal').modal('show');
		$('#updateuserid').val(row.UserNum);
		$('#updateuserName').val(row.UserName);
		$('#updateRoleName').val(row.UserRoleName);
	}
	//添加操作
	function Add() {
		$('#addbut').click(function() {
			var add1 = $('#adduserName').val();
			var add2 = $('#addRoleName').val();
			if (add1 == "" || add2 == "") {
				$('#tipsmsg').html("信息不可为空");
				$('#TipsModal').modal('show');
			} else {
				var data = {
					action : "add",
					UserName : $('#adduserName').val(),
					RoleName : $('#addRoleName').val()
				}
				//请求添加
				$.ajax({
					type : "POST",
					url : "UsersMangement",
					data : data,
					dataType : "json",
					async : true,
					success : function(data) {
						//刷新列表
						refresh();
						$('#addModal').modal('hide');
						$('#tipsmsg').html(data.msg);
						$('#TipsModal').modal('show');
					},
					error : function(data) {
						$('#tipsmsg').html("请求错误");
						$('#TipsModal').modal('show');
					}
				})
			}
		})
	}
	//删除操作
	function Delete() {
		$('#deletebut').click(function() {
			var data = {
				action : "delete",
				UserNum : UserNum
			}
			//请求删除
			$.ajax({
				type : "POST",
				url : "UsersMangement",
				data : data,
				async : true,
				success : function(data) {
					//刷新列表
					refresh();
					$('#deleteModal').modal('hide');
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
				<div class="col-md-1 col-sm-2">
					<span>查询条件:</span>
				</div>
				<div class="col-md-1 col-sm-3">
					<select name="" id="select_type" class="form-control ">
						<option value="所有">所有</option>
						<option value="编号">编号</option>
					</select>
				</div>
				<div class="col-md-3 col-sm-3">
					<input type="text" id="select_Userid" disabled="disabled"
						class="form-control" />
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
					<button class="btn btn-sm btn-info" id="add_users">
						<span>添加会员</span>
					</button>
				</div>

			</div>
			<div class="col-md-5" style="margin-top: 25px">
				<span id="number"></span>
			</div>
			<div class="row" style="margin-top: 15px">
				<div class="col-md-12" align="center">
					<table id="userList" style="text-align: center;"
						class="table table-striped"></table>
				</div>
			</div>
		</div>
	</div>
	<!-- 模态框（Modal）添加会员 -->
	<div class="modal fade in" id="addModal" tabindex="-1" role="dialog"
		aria-hidden="">
		<div class="modal-dialog">
			<div class="modal-content">
				<div class="modal-header">
					<button type="button" class="close" data-dismiss="modal"
						aria-hidden="true">&times;</button>
					<h4 class="modal-title">添加会员信息</h4>
				</div>
				<div class="modal-body">
					<div class="row">
						<div class="col-md-1 col-sm-1"></div>
						<div class="col-md-8 col-sm-8">
							<div class="form-horizontal">
								<div class="form-group">
									<label for="" class="control-label col-md-4 col-sm-4">
										<span>会员名称：</span>
									</label>
									<div class="col-md-8 col-sm-8">
										<input type="text" class="form-control" id="adduserName"
											name="adduserName" placeholder="门店名称">
									</div>
								</div>
								<div class="form-group">
									<label for="" class="control-label col-md-4 col-sm-4">
										<span>会员等级：</span>
									</label>
									<div class="col-md-8 col-sm-8">
										<select name="" id="addRoleName" class="form-control ">
											<option value="至尊会员">至尊会员</option>
											<option value="超级会员">超级会员</option>
											<option value="普通会员">普通会员</option>
										</select>
									</div>
								</div>

							</div>
						</div>
					</div>
				</div>
				<div class="modal-footer">
					<button type="button" class="btn btn-default" data-dismiss="modal">关闭</button>
					<button type="button" id="addbut" class="btn btn-primary">添加</button>
				</div>
			</div>
		</div>
	</div>
	<!-- 模态框（Modal）修改会员 -->
	<div class="modal fade in" id="updateModal" tabindex="-1" role="dialog"
		aria-hidden="">
		<div class="modal-dialog">
			<div class="modal-content">
				<div class="modal-header">
					<button type="button" class="close" data-dismiss="modal"
						aria-hidden="true">&times;</button>
					<h4 class="modal-title">修改门店信息</h4>
				</div>
				<div class="modal-body">
					<div class="row">
						<div class="col-md-1 col-sm-1"></div>
						<div class="col-md-8 col-sm-8">
							<div class="form-horizontal">
								<div class="form-group">
									<label for="" class="control-label col-md-4 col-sm-4">
										<span>会员编号：</span>
									</label>
									<div class="col-md-8 col-sm-8">
										<input type="text" disabled="disabled" class="form-control"
											id="updateuserid" name="updateuserid" placeholder="会员编号">
									</div>
								</div>
								<div class="form-group">
									<label for="" class="control-label col-md-4 col-sm-4">
										<span>会员名称：</span>
									</label>
									<div class="col-md-8 col-sm-8">
										<input type="text" class="form-control" id="updateuserName"
											name="updateuserName" placeholder="会员名称">
									</div>
								</div>
								<div class="form-group">
									<label for="" class="control-label col-md-4 col-sm-4">
										<span>门店店主：</span>
									</label>
									<div class="col-md-8 col-sm-8">
										<select name="" id="updateRoleName" class="form-control ">
											<option value="至尊会员">至尊会员</option>
											<option value="超级会员">超级会员</option>
											<option value="普通会员">普通会员</option>
										</select>
									</div>

								</div>

							</div>
						</div>
					</div>
				</div>
				<div class="modal-footer">
					<button type="button" class="btn btn-default" data-dismiss="modal">关闭</button>
					<button type="button" id="updatebut" class="btn btn-primary">提交</button>
				</div>
			</div>
		</div>
	</div>
	<!-- 模态框（Modal）删除会员 -->
	<div class="modal fade in" id="deleteModal" tabindex="-1" role="dialog"
		aria-hidden="">
		<div class="modal-dialog">
			<div class="modal-content">
				<div class="modal-header">
					<button type="button" class="close" data-dismiss="modal"
						aria-hidden="true">&times;</button>
					<h4 class="modal-title">删除会员信息</h4>
				</div>
				<div class="modal-body">
					<h3 id="deletemsg">是否删除该会员？</h3>
				</div>
				<div class="modal-footer">
					<button type="button" class="btn btn-default" data-dismiss="modal">关闭</button>
					<button type="button" id="deletebut" class="btn btn-primary">确定</button>
				</div>
			</div>
		</div>
	</div>
</body>
</html>