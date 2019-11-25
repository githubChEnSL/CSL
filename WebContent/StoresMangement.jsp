<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>门店信息管理</title>
</head>
<script type="text/javascript">
	var StoreId = "";
	$(function() {
		search();
		addStore(); //添加门店信息框
		Add();//添加门店信息
		Delete();//删除门店信息
		Update();//修改门店信息
		refresh();//刷新列表
	})
	//刷新操作
	function refresh() {
		$("#StoresList").html("");
		var data = {
			action : "all"
		}
		$
				.ajax({
					url : "StoreMangement",
					type : "post",
					data : data,
					dataType : "json",
					contentType : "application/json",
					success : function(data) {
						//获取后台商店信息 
						var stores = data.rows;
						$("#StoresList")
								.append(
										"<tr><td>门店编号</td><td>门店名称</td><td>门店管理员</td><td>操作</td></tr>");
						$
								.each(
										stores,
										function(key, value) {
											var row = JSON
													.stringify(stores[key]);
											$("#StoresList")
													.append(
															"<tr><td>"
																	+ stores[key].StoreNum
																	+ "</td><td>"
																	+ stores[key].StoreName
																	+ "</td><td>"
																	+ stores[key].RegulatorName
																	+ "</td><td><button class='btn btn-info' onclick='updateStore("
																	+ row
																	+ ")'><span>编辑</span></button>"
																	+ "  "
																	+ "<button class='btn btn-danger' onclick='deleteStore("
																	+ row
																	+ ")'><span>删除</span></button></td></tr>");
										})
					},
					error : function(data) {
						alert("请求失败");
					}
				})
	}
	//查询商店信息
	function search() {
		$('#search_button').click(function() {
			refresh();
		})
	}
	//弹出添加框
	function addStore() {
		$('#add_stores').click(function() {
			$('#addModal').modal('show');
		})
	}
	//弹出删除框
	function deleteStore(row) {
		$('#deleteModal').modal('show');
		StoreId = row.StoreNum;
		$('#deletemsg').html("是否删除：" + row.StoreName + "?");
	}
	//弹出修改框
	function updateStore(row) {
		$('#updateModal').modal('show');
		$('#updatestoreid').val(row.StoreNum);
		$('#updatestoreName').val(row.StoreName);
		$('#updateRegulatorName').val(row.RegulatorName);
	}
	//添加操作
	function Add() {
		$('#addbut').click(function() {
			var add1 = $('#addstoreName').val();
			var add2 = $('#addRegulatorName').val();
			if (add1 == "" || add2 == "") {
				$('#tipsmsg').html("信息不可为空");
				$('#TipsModal').modal('show');
			} else {
				var data = {
					action : "add",
					StoreName : $('#addstoreName').val(),
					RegulatorName : $('#addRegulatorName').val()
				}
				//请求添加
				$.ajax({
					type : "POST",
					url : "StoreMangement",
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
				StoreId : StoreId
			}
			//请求删除
			$.ajax({
				type : "POST",
				url : "StoreMangement",
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
				StoreId : $('#updatestoreid').val(),
				StoreName : $('#updatestoreName').val(),
				RegulatorName : $('#updateRegulatorName').val()
			}
			//请求删除
			//请求添加
			$.ajax({
				type : "POST",
				url : "StoreMangement",
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
			<li>门店信息管理</li>
		</ol>
		<c:if test="${sessionScope.roleId==1}">
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
					<button class="btn btn-sm btn-default" id="add_stores">
						<span>添加门店</span>
					</button>
				</div>
				<div class="col-md-5"></div>
			</div>

			<div class="row" style="margin-top: 15px">
				<div class="col-md-12" align="center" style="overflow-x: auto; overflow-y: auto; height: 445px; width:1200px;">
					<table id="StoresList" style="text-align: center;"
						class="table table-striped"></table>
				</div>
			</div>
		</div>
		</c:if>
		<c:if test="${sessionScope.roleId!=1}">您没有权限进行相关操作！</c:if>
	</div>
	<!-- 模态框（Modal）添加门店 -->
	<div class="modal fade in" id="addModal" tabindex="-1" role="dialog"
		aria-hidden="">
		<div class="modal-dialog">
			<div class="modal-content">
				<div class="modal-header">
					<button type="button" class="close" data-dismiss="modal"
						aria-hidden="true">&times;</button>
					<h4 class="modal-title">添加门店信息</h4>
				</div>
				<div class="modal-body">
					<div class="row">
						<div class="col-md-1 col-sm-1"></div>
						<div class="col-md-8 col-sm-8">
							<div class="form-horizontal">
								<div class="form-group">
									<label for="" class="control-label col-md-4 col-sm-4">
										<span>门店名称：</span>
									</label>
									<div class="col-md-8 col-sm-8">
										<input type="text" class="form-control" id="addstoreName"
											name="addstoreName" placeholder="门店名称">
									</div>
								</div>
								<div class="form-group">
									<label for="" class="control-label col-md-4 col-sm-4">
										<span>门店店主：</span>
									</label>
									<div class="col-md-8 col-sm-8">
										<input type="text" class="form-control" id="addRegulatorName"
											name="addRegulatorName" placeholder="门店店主">
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
	<!-- 模态框（Modal）修改门店 -->
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
										<span>门店编号：</span>
									</label>
									<div class="col-md-8 col-sm-8">
										<input type="text" disabled="disabled" class="form-control"
											id="updatestoreid" name="updatestoreid" placeholder="门店编号">
									</div>
								</div>
								<div class="form-group">
									<label for="" class="control-label col-md-4 col-sm-4">
										<span>门店名称：</span>
									</label>
									<div class="col-md-8 col-sm-8">
										<input type="text" class="form-control" id="updatestoreName"
											name="updatestoreName" placeholder="门店名称">
									</div>
								</div>
								<div class="form-group">
									<label for="" class="control-label col-md-4 col-sm-4">
										<span>门店店主：</span>
									</label>
									<div class="col-md-8 col-sm-8">
										<input type="text" class="form-control"
											id="updateRegulatorName" name="updateRegulatorName"
											placeholder="门店店主">
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
	<!-- 模态框（Modal）删除门店 -->
	<div class="modal fade in" id="deleteModal" tabindex="-1" role="dialog"
		aria-hidden="">
		<div class="modal-dialog">
			<div class="modal-content">
				<div class="modal-header">
					<button type="button" class="close" data-dismiss="modal"
						aria-hidden="true">&times;</button>
					<h4 class="modal-title">删除门店信息</h4>
				</div>
				<div class="modal-body">
					<h3 id="deletemsg">是否删除该门店？</h3>
				</div>
				<div class="modal-footer">
					<button type="button" class="btn btn-default" data-dismiss="modal">关闭</button>
					<button type="button" id="deletebut" class="btn btn-primary">确定</button>
				</div>
			</div>
		</div>
	</div>
	<!-- 模态框（Modal）操作状态提示信息 -->
	<div class="modal fade in" id="TipsModal" tabindex="-1" role="dialog"
		style="text-align: center;" aria-hidden="">
		<div class="modal-dialog">
			<div class="modal-content">
				<div class="modal-header">
					<button type="button" class="close" data-dismiss="modal"
						aria-hidden="true">&times;</button>
					<h4 class="modal-title">提示</h4>
				</div>
				<div class="modal-body">
					<h3 id="tipsmsg"></h3>
				</div>
				<div class="modal-footer">
					<button type="button" class="btn btn-default" data-dismiss="modal">关闭</button>
				</div>
			</div>
		</div>
	</div>
</body>
</html>