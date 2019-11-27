<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>员工信息管理</title>
</head>
<script type="text/javascript">
	var RegulatorId = "";
	$(function() {
		search();
		addRegulator(); //添加员工信息框
		Add();//添加员工信息
		Delete();//删除员工信息
		Update();//修改员工信息
		refresh();
		listStoreName();//获取所有的门店名称
	})
	//获取所有的门店和管理员名称
	function listStoreName() {
		$.ajax({
			type : "GET",
			url : "RegulatorMangement",
			dataType : "json",
			success : function(data) {
				//获取角色名称
				var listrole=data.listRoleName;
				$.each(listrole, function(key, value) {
					$('#addRegulatorRoleName').append(
							"<option value='"+listrole[key]+"'>"
									+ listrole[key] + "</option>");
					$('#updateRegulatorRoleName').append(
							"<option value='"+listrole[key]+"'>"
									+ listrole[key] + "</option>");
					
				})
				//获取门店名称
				var liststore = data.liststorename;
				$('#Store_select').html("");
				$('#Store_select').append("<option value='所有'>所有</option>");
				$.each(liststore, function(key, value) {
					$('#Store_select').append(
							"<option value='"+liststore[key]+"'>"
									+ liststore[key] + "</option>");
					$('#addStoreName').append(
							"<option value='"+liststore[key]+"'>"
									+ liststore[key] + "</option>");
					$('#updateStoreName').append(
							"<option value='"+liststore[key]+"'>"
									+ liststore[key] + "</option>");
				})
				
			},
			error : function(data) {
				$('#tipsmsg').html("请求错误");
				$('#TipsModal').modal('show');
			}
		})
	}
	//刷新操作
	function refresh() {
		$("#RegulatorList").html("");
		var data = {
			action : "search",
			StoreName : $('#Store_select').val()
		}
		$
				.ajax({
					url : "RegulatorMangement",
					type : "GET",
					data : data,
					dataType : "json",
					contentType : "application/json",
					success : function(data) {
						//获取后台员工信息 
						var regulators = data.rows;
						$("#RegulatorList")
								.append(
										"<tr><td>员工编号</td><td>员工名称</td><td>员工角色</td><td>所属门店</td><td>操作</td></tr>");
						$
								.each(
										regulators,
										function(key, value) {
											var row = JSON
													.stringify(regulators[key]);
											$("#RegulatorList")
													.append(
															"<tr><td>"
																	+ regulators[key].RegulatorNum
																	+ "</td><td>"
																	+ regulators[key].RegulatorName
																	+ "</td><td>"
																	+ regulators[key].RegulatorRole
																	+ "</td><td>"
																	+ regulators[key].StoreName
																	+ "</td><td><button class='btn btn-info' onclick='updateRegulator("
																	+ row
																	+ ")'><span>编辑</span></button>"
																	+ "  "
																	+ "<button class='btn btn-danger' onclick='deleteRegulator("
																	+ row
																	+ ")'><span>删除</span></button></td></tr>");
										})
					},
					error : function(data) {
						$('#tipsmsg').html("请求错误");
						$('#TipsModal').modal('show');
					}
				})
	}
	//查询员工信息
	function search() {
		$('#search_button').click(function() {
			refresh();
		})
	}
	//弹出添加框
	function addRegulator() {
		$('#add_regulator').click(function() {
			$('#addModal').modal('show');
		})
	}
	//弹出删除框
	function deleteRegulator(row) {
		$('#deleteModal').modal('show');
		RegulatorId = row.RegulatorNum;
		$('#deletemsg').html("是否删除：" + row.RegulatorNum + "?");
	}
	//弹出修改框
	function updateRegulator(row) {
		$('#updateModal').modal('show');
		$('#updateRegulatorid').val(row.RegulatorNum);
		$('#updateRegulatorName').val(row.RegulatorName);
		$('#updateRegulatorRoleName').val(row.RegulatorRole);
		$('#updateStoreName').val(row.StoreName);
	}
	//添加操作
	function Add() {
		$('#addbut').click(function() {
			var data = {
				action : "add",
				regulatorName : $('#addregulatorName').val(),
				RegulatorRoleName : $('#addRegulatorRoleName').val(),
				StoreName:$('#addStoreName').val()
			}
			//请求添加
			$.ajax({
				type : "POST",
				url : "RegulatorMangement",
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
		})
	}
	//删除操作
	function Delete() {
		$('#deletebut').click(function() {
			var data = {
				action : "delete",
				RegulatorId : RegulatorId
			}
			//请求删除
			$.ajax({
				type : "POST",
				url : "RegulatorMangement",
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
				RegulatorId : $('#updateRegulatorid').val(),
				regulatorName : $('#updateRegulatorName').val(),
				RegulatorRoleName : $('#updateRegulatorRoleName').val(),
				StoreName:$('#updateStoreName').val()
			}
			//请求删除
			//请求添加
			$.ajax({
				type : "POST",
				url : "RegulatorMangement",
				data : data,
				async : true,
				success : function(data) {
					//刷新列表
					refresh();
					$('#updateModal').modal('hide');
					$('#tipsmsg').html(data.msg);
					$('#TipsModal').modal('show');
				},
				error : function(msg) {
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
			<li>员工信息管理</li>
		</ol>
		<div class="panel-body">
		<c:if test="${sessionScope.roleId==1}">
			<div class="row">
				<div class="col-md-1 col-sm-2">
					<span>选择门店：</span>
				</div>
				<div class="col-md-3 col-sm-3">
					<select name="" id="Store_select" class="form-control ">
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
		</c:if>
			<div class="row" style="margin-top: 25px">
				<div class="col-md-5">
					<button class="btn btn-sm btn-info" id="add_regulator">
						<span>添加员工</span>
					</button>
				</div>
				<div class="col-md-5"></div>
			</div>

			<div class="row" style="margin-top: 15px">
				<div class="col-md-12" align="center" style="overflow-x: auto; overflow-y: auto; height: 445px; width:1200px;">
					<table id="RegulatorList" style="text-align: center;"
						class="table table-striped"></table>
				</div>
			</div>
		</div>
	</div>
	<!-- 模态框（Modal）添加员工 -->
	<div class="modal fade in" id="addModal" tabindex="-1" role="dialog"
		aria-hidden="">
		<div class="modal-dialog">
			<div class="modal-content">
				<div class="modal-header">
					<button type="button" class="close" data-dismiss="modal"
						aria-hidden="true">&times;</button>
					<h4 class="modal-title">添加员工信息</h4>
				</div>
				<div class="modal-body">
					<div class="row">
						<div class="col-md-1 col-sm-1"></div>
						<div class="col-md-8 col-sm-8">
							<div class="form-horizontal">
								<div class="form-group">
									<label for="" class="control-label col-md-4 col-sm-4">
										<span>员工名称：</span>
									</label>
									<div class="col-md-8 col-sm-8">
										<input type="text" class="form-control" id="addregulatorName"
											name="addregulatorName" placeholder="员工名称">
									</div>
								</div>
								<div class="form-group">
									<label for="" class="control-label col-md-4 col-sm-4">
										<span>员工角色：</span>
									</label>
									<div class="col-md-8 col-sm-8">
										<select name="" id="addRegulatorRoleName"
											class="form-control ">
										</select>
									</div>
								</div>
								<div class="form-group">
									<label for="" class="control-label col-md-4 col-sm-4">
										<span>所属门店：</span>
									</label>
									<div class="col-md-8 col-sm-8">
										<select name="" id="addStoreName" class="form-control ">

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
	<!-- 模态框（Modal）修改员工 -->
	<div class="modal fade in" id="updateModal" tabindex="-1" role="dialog"
		aria-hidden="">
		<div class="modal-dialog">
			<div class="modal-content">
				<div class="modal-header">
					<button type="button" class="close" data-dismiss="modal"
						aria-hidden="true">&times;</button>
					<h4 class="modal-title">修改员工信息</h4>
				</div>
				<div class="modal-body">
					<div class="row">
						<div class="col-md-1 col-sm-1"></div>
						<div class="col-md-8 col-sm-8">
							<div class="form-horizontal">
								<div class="form-group">
									<label for="" class="control-label col-md-4 col-sm-4">
										<span>员工编号：</span>
									</label>
									<div class="col-md-8 col-sm-8">
										<input type="text" disabled="disabled" class="form-control"
											id="updateRegulatorid" name="updateRegulatorid"
											placeholder="员工编号">
									</div>
								</div>
								<div class="form-group">
									<label for="" class="control-label col-md-4 col-sm-4">
										<span>员工名称：</span>
									</label>
									<div class="col-md-8 col-sm-8">
										<input type="text" class="form-control"
											id="updateRegulatorName" name="updateRegulatorName"
											placeholder="员工名称">
									</div>
								</div>
								<div class="form-group">
									<label for="" class="control-label col-md-4 col-sm-4">
										<span>员工角色：</span>
									</label>
									<div class="col-md-8 col-sm-8">
										<select name="" id="updateRegulatorRoleName"
											class="form-control ">
										</select>
									</div>
								</div>
								<div class="form-group">
									<label for="" class="control-label col-md-4 col-sm-4">
										<span>所属门店：</span>
									</label>
									<div class="col-md-8 col-sm-8">
										<select name="" id="updateStoreName"
											class="form-control ">
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
	<!-- 模态框（Modal）删除员工 -->
	<div class="modal fade in" id="deleteModal" tabindex="-1" role="dialog"
		aria-hidden="">
		<div class="modal-dialog">
			<div class="modal-content">
				<div class="modal-header">
					<button type="button" class="close" data-dismiss="modal"
						aria-hidden="true">&times;</button>
					<h4 class="modal-title">删除员工信息</h4>
				</div>
				<div class="modal-body">
					<h3 id="deletemsg">是否删除该员工？</h3>
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