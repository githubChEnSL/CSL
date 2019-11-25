<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>个人信息管理</title>
</head>
<script type="text/javascript">
	$(function() {
		updatePassword();//修改密码
		refresh();//刷新信息
		update();//编辑资料模态框

		updatename();//确认修改名称
		updatepass();//确认修改密码
	})
	//修改资料模态框
	function update() {
		$('#update').click(function() {
			$('#updateNameModal').modal('show');
		})
	}
	//修改密码
	function updatePassword() {
		$('#add_password').click(function() {
			$('#updatePasswordModal').modal('show');
		})
	}
	//刷新信息表
	function refresh() {
		$.ajax({
			url : "PersonalMangement",
			type : "GET",
			dataType : "json",
			contentType : "application/json",
			success : function(data) {
				//将信息显示在框中
				$('#id').val(data.LoginId);
				$('#name').val(data.LoginName);
				$('#store').val(data.LoginStoreName);
				$('#role').val(data.LoginRoleName);

				//显示在修改名称框中
				$('#updateRegulatorName').val(data.LoginName);
			},
			error : function(data) {
				$('#tipsmsg').html("请求错误");
				$('#TipsModal').modal('show');
			}
		})
	}
	//确认修改名称
	function updatename() {
		$('#updatenamebut').click(function() {
			$('#updateNameModal').modal('hide');
			var data = {
				Type : "updatename",
				updateName : $('#updateRegulatorName').val()
			}
			$.ajax({
				url : "PersonalMangement",
				type : "post",
				data : data,
				dataType : "json",
				success : function(data) {
					$('#tipsmsg').html(data.msg);
					$('#TipsModal').modal('show');
					refresh();//刷新信息
				},
				error : function() {
					$('#tipsmsg').html("请求错误");
					$('#TipsModal').modal('show');
				}
			})
		})
	}
	//确认修改密码
	function updatepass() {
		$('#updatepassbut').click(function() {
			//判断两次密码是否一致
			var oldP = $('#oldpassword').val();
			var newP = $('#newpassword').val();
			var trueP = $('#truepassword').val();
			$('#updatePasswordModal').modal('hide');
			if (newP != "" && trueP != "" && oldP != "") {
				if (newP == trueP) {
					var data = {
						Type : "updatepass",
						oldpassword : oldP,
						truepassword : trueP
					}
					$.ajax({
						url : "PersonalMangement",
						type : "post",
						data : data,
						dataType : "json",
						success : function(data) {
							$('#tipsmsg').html(data.msg);
							$('#TipsModal').modal('show');
							refresh();//刷新信息
						},
						error : function() {
							$('#tipsmsg').html("请求错误");
							$('#TipsModal').modal('show');
						}
					})
				} else {
					$('#tipsmsg').html("新密码不一致");
					$('#TipsModal').modal('show');
				}
			} else {
				$('#tipsmsg').html("密码不能为空");
				$('#TipsModal').modal('show');
			}
			
		})
	}
</script>
<body>
	<div class="panel panel-default">
		<ol class="breadcrumb">
			<li>个人信息管理</li>
		</ol>
		<div class="panel-body">
			<div class="row" style="margin-top: 25px">
				<div class="col-md-5" style="text-align: center;">
					<button class="btn btn-sm btn-success" id="add_password">
						<span>修改密码</span>
					</button>
				</div>
				<div class="col-md-5" style="text-align: right;">
					<button class="btn btn-sm btn-info" id="update">
						<span>修改名称</span>
					</button>
				</div>
			</div>
			<div class="row">
				<div class="col-md-1 col-sm-1"></div>
				<div class="col-md-8 col-sm-8">
					<div class="form-horizontal">
						<div class="form-group">
							<label for="" class="control-label col-md-4 col-sm-4"> <span>编号：</span>
							</label>
							<div class="col-md-8 col-sm-8">
								<input type="text" disabled="disabled" class="form-control"
									id="id" name="id" placeholder="员工编号" value="">
							</div>
						</div>
						<div class="form-group">
							<label for="" class="control-label col-md-4 col-sm-4"> <span>名称：</span>
							</label>
							<div class="col-md-8 col-sm-8">
								<input type="text" disabled="disabled" class="form-control"
									id="name" name="name" placeholder="员工名称 " value="">
							</div>
						</div>
						<div class="form-group">
							<label for="" class="control-label col-md-4 col-sm-4"> <span>所属门店：</span>
							</label>
							<div class="col-md-8 col-sm-8">
								<input type="text" disabled="disabled" class="form-control"
									id="store" name="store" placeholder="所属门店" value="">
							</div>
						</div>
						<div class="form-group">
							<label for="" class="control-label col-md-4 col-sm-4"> <span>身份权限：</span>
							</label>
							<div class="col-md-8 col-sm-8">
								<input type="text" disabled="disabled" class="form-control"
									id="role" name="role" placeholder="身份权限" value="">
							</div>
						</div>
					</div>
				</div>
			</div>
		</div>
	</div>
	<!-- 模态框（Modal）修改名称 -->
	<div class="modal fade in" id="updateNameModal" tabindex="-1"
		role="dialog" aria-hidden="">
		<div class="modal-dialog">
			<div class="modal-content">
				<div class="modal-header">
					<button type="button" class="close" data-dismiss="modal"
						aria-hidden="true">&times;</button>
					<h4 class="modal-title">修改名称</h4>
				</div>
				<div class="modal-body">
					<div class="row">
						<div class="col-md-1 col-sm-1"></div>
						<div class="col-md-8 col-sm-8">
							<div class="form-horizontal">
								<div class="form-group">
									<label for="" class="control-label col-md-4 col-sm-4">
										<span>编号：</span>
									</label>
									<div class="col-md-8 col-sm-8">
										<input type="text" disabled="disabled" class="form-control"
											id="updatenameId" value="${sessionScope.id}">
									</div>
								</div>
								<div class="form-group">
									<label for="" class="control-label col-md-4 col-sm-4">
										<span>名称：</span>
									</label>
									<div class="col-md-8 col-sm-8">
										<input type="text" class="form-control"
											id="updateRegulatorName" name="updateRegulatorName" value="">
									</div>
								</div>
							</div>
						</div>
					</div>
				</div>
				<div class="modal-footer">
					<button type="button" class="btn btn-default" data-dismiss="modal">取消</button>
					<button type="button" id="updatenamebut" class="btn btn-primary">修改</button>
				</div>
			</div>
		</div>
	</div>
	<!-- 模态框（Modal）修改密码 -->
	<div class="modal fade in" id="updatePasswordModal" tabindex="-1"
		role="dialog" aria-hidden="">
		<div class="modal-dialog">
			<div class="modal-content">
				<div class="modal-header">
					<button type="button" class="close" data-dismiss="modal"
						aria-hidden="true">&times;</button>
					<h4 class="modal-title">修改密码</h4>
				</div>
				<div class="modal-body">
					<div class="row">
						<div class="col-md-1 col-sm-1"></div>
						<div class="col-md-8 col-sm-8">
							<div class="form-horizontal">
								<div class="form-group">
									<label for="" class="control-label col-md-4 col-sm-4">
										<span>编号：</span>
									</label>
									<div class="col-md-8 col-sm-8">
										<input type="text" disabled="disabled" class="form-control"
											id="updateRegulatorid" name="updateRegulatorid"
											placeholder="员工编号" value="${sessionScope.id}">
									</div>
								</div>
								<div class="form-group">
									<label for="" class="control-label col-md-4 col-sm-4">
										<span>旧密码：</span>
									</label>
									<div class="col-md-8 col-sm-8">
										<input type="text" class="form-control" id="oldpassword"
											name="oldpassword" value="">
									</div>
								</div>
								<div class="form-group">
									<label for="" class="control-label col-md-4 col-sm-4">
										<span>新密码：</span>
									</label>
									<div class="col-md-8 col-sm-8">
										<input type="text" class="form-control" id="newpassword"
											name="newpassword" value="">
									</div>
								</div>
								<div class="form-group">
									<label for="" class="control-label col-md-4 col-sm-4">
										<span>确认新密码：</span>
									</label>
									<div class="col-md-8 col-sm-8">
										<input type="text" class="form-control" id="truepassword"
											name="truepassword" value="">
									</div>
								</div>
							</div>
						</div>
					</div>
				</div>
				<div class="modal-footer">
					<button type="button" class="btn btn-default" data-dismiss="modal">取消</button>
					<button type="button" id="updatepassbut" class="btn btn-primary">修改</button>
				</div>
			</div>
		</div>
	</div>
</body>
</html>