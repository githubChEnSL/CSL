<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>门店信息管理</title>
</head>
<script type="text/javascript">
//表格初始化
$(function(){
	userListInit();
})
// 分页查询参数
function queryParams(params) {
	var temp = {
		limit : params.limit,
		offset : params.offset,
	}
	return temp;
}
function userListInit() {
	$('#userList')
			.bootstrapTable(
					{
						columns : [
								{
									field : 'userCount',
									title : '门店编号'
								},
								{
									field : 'userName',
									title : '门店名称'
								},
								{
									field : 'userRole',
									title : '门店店主'
								},

								{
									field : 'operation',
									title : '操作',
									formatter : function(value, row, index) {
										var s = '<button class="btn btn-info btn-sm edit"><span>编辑</span></button>';
										var d = '<button class="btn btn-danger btn-sm delete"><span>删除</span></button>';
										var fun = '';
										return s + ' ' + d;
									},
									events : {
										// 操作列中编辑按钮的动作
										'click .edit' : function(e, value,
												row, index) {
											//selectID = row.userCount;
											//rowEditOperation(row);
										},
										'click .delete' : function(e,
												value, row, index) {
											selectID = row.userCount;
											$('#deleteWarning_modal')
													.modal('show');
										}
									}
								} ],
						url : 'StoreMangement',
						onLoadError : function(status) {
							
						},
						method : 'GET',
						queryParams : queryParams,
						sidePagination : "server",//分页方式，服务器端分页
						dataType : 'json',
						pagination : false,//是否开启分页
						pageList : [ 5, 10, 25, 50, 100 ],//可供选择的每页的行数
						clickToSelect : true
					//是否启用点击选中行
					});
}

// 表格刷新
function tableRefresh() {
	$('#userList').bootstrapTable('refresh', {
		query : {}
	});
}
</script>
<body>
	<div class="panel panel-default">
		<ol class="breadcrumb">
			<li>门店信息管理</li>
		</ol>
		<div class="panel-body">
			<div class="row">
				<div class="col-md-3 col-sm-3">
					<select  name="" id="college_selector"
						class="form-control selector_college">
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
				<div class="col-md-12">
					<table id="userList" class="table table-striped"></table>
				</div>
			</div>
		</div>
	</div>
<!-- //////////////////////////////////////////////////////////////////////////////////////////// -->
	<!-- 添加用户信息模态框 -->
	<div id="add_modal" class="modal fade" table-index="-1" role="dialog"
		aria-labelledby="myModalLabel" aria-hidden="true"
		data-backdrop="static">
		<div class="modal-dialog">
			<div class="modal-content">
				<div class="modal-header">
					<button class="close" type="button" data-dismiss="modal"
						aria-hidden="true">&times;</button>
					<h4 class="modal-title" id="myModalLabel">添加用户信息</h4>
				</div>
				<div class="modal-body">
					<!-- 模态框的内容 -->
					<div class="row">
						<div class="col-md-1 col-sm-1"></div>
						<div class="col-md-8 col-sm-8">
							<form class="form-horizontal" role="form" id="user_form"
								style="margin-top: 25px">
								<div class="form-group">
									<label for="" class="control-label col-md-4 col-sm-4">
										<span>用户账号：</span>
									</label>
									<div class="col-md-8 col-sm-8">
										<input type="text" class="form-control" id="user_count"
											name="user_count" placeholder="用户账户">
									</div>
								</div>
								<div class="form-group">
									<label for="" class="control-label col-md-4 col-sm-4">
										<span>用户名称：</span>
									</label>
									<div class="col-md-8 col-sm-8">
										<input type="text" class="form-control" id="user_name"
											name="user_name" placeholder="用户名称">
									</div>
								</div>
								<div class="form-group">
									<label for="" class="control-label col-md-4 col-sm-4">
										<span>所属学校：</span>
									</label>
									<div class="col-md-8 col-sm-8">
										<select name="" class="form-control selector_college"
											id="add_college_selector">
										</select>
									</div>
								</div>
								<div class="form-group">
									<label for="" class="control-label col-md-4 col-sm-4">
										<span>所属部门：</span>
									</label>
									<div class="col-md-8 col-sm-8">
										<select name="" class="form-control selector_depart"
											id="add_depart_selector">
										</select>
									</div>
								</div>
								<div class="form-group">
									<label for="" class="control-label col-md-4 col-sm-4">
										<span>角色权限：</span>
									</label>
									<div class="col-md-8 col-sm-8">
										<select name="" class="form-control" id="user_role">
											<option value="管理员">管理员</option>
											<option value="盘点员">盘点员</option>
										</select>
									</div>
								</div>
						</div>
						</form>
					</div>
					<div class="col-md-1 col-sm-1"></div>
				</div>
			</div>
			<div class="modal-footer">
				<button class="btn btn-default" type="button" data-dismiss="modal">
					<span>取消</span>
				</button>
				<button class="btn btn-success" type="button" id="add_modal_submit">
					<span>提交</span>
				</button>
			</div>
		</div>
	</div>
	</div>




	<!-- 删除提示模态框 -->
	<div class="modal fade" id="deleteWarning_modal" table-index="-1"
		role="dialog" aria-labelledby="myModalLabel" aria-hidden="true">
		<div class="modal-dialog">
			<div class="modal-content">
				<div class="modal-header">
					<button class="close" type="button" data-dismiss="modal"
						aria-hidden="true">&times;</button>
					<h4 class="modal-title" id="myModalLabel">警告</h4>
				</div>
				<div class="modal-body">
					<div class="row">
						<div class="col-md-3 col-sm-3" style="text-align: center;">

						</div>
						<div class="col-md-8 col-sm-8">
							<h3>是否确认删除该条用户信息</h3>

						</div>
					</div>
				</div>
				<div class="modal-footer">
					<button class="btn btn-default" type="button" data-dismiss="modal">
						<span>取消</span>
					</button>
					<button class="btn btn-danger" type="button" id="delete_confirm">
						<span>确认删除</span>
					</button>
				</div>
			</div>
		</div>
	</div>

	<!-- 编辑客户信息模态框 -->
	<div id="edit_modal" class="modal fade" table-index="-1" role="dialog"
		aria-labelledby="myModalLabel" aria-hidden="true"
		data-backdrop="static">
		<div class="modal-dialog">
			<div class="modal-content">
				<div class="modal-header">
					<button class="close" type="button" data-dismiss="modal"
						aria-hidden="true">&times;</button>
					<h4 class="modal-title" id="myModalLabel">编辑用户信息</h4>
				</div>
				<div class="modal-body">
					<!-- 模态框的内容 -->
					<div class="row">
						<div class="col-md-1 col-sm-1"></div>
						<div class="col-md-8 col-sm-8">
							<form class="form-horizontal" role="form" id="user_form_edit"
								style="margin-top: 25px">
								<div class="form-group">
									<label for="" class="control-label col-md-4 col-sm-4">
										<span>用户账号：</span>
									</label>
									<div class="col-md-8 col-sm-8">
										<input type="text" disabled="disabled" class="form-control"
											id="user_count_edit" name="user_count" placeholder="用户账号">
									</div>
								</div>
								<div class="form-group">
									<label for="" class="control-label col-md-4 col-sm-4">
										<span>用户名称：</span>
									</label>
									<div class="col-md-8 col-sm-8">
										<input type="text" class="form-control" id="user_name_edit"
											name="user_name" placeholder="用户名称">
									</div>
								</div>
								<div class="form-group">
									<label for="" class="control-label col-md-4 col-sm-4">
										<span>用户密码：</span>
									</label>
									<div class="col-md-8 col-sm-8">
										<input type="text" class="form-control"
											id="user_passward_edit" name="user_passward"
											placeholder="用户密码">
									</div>
								</div>
								<div class="form-group">
									<label for="" class="control-label col-md-4 col-sm-4">
										<span>用户角色权限：</span>
									</label>
									<div class="col-md-5 col-sm-5">
										<select name="" class="form-control" id="user_role_edit">
											<option value="管理员">管理员</option>
											<option value="盘点员">盘点员</option>
										</select>
									</div>
								</div>

							</form>
						</div>
						<div class="col-md-1 col-sm-1"></div>
					</div>
				</div>
				<div class="modal-footer">
					<button class="btn btn-default" type="button" data-dismiss="modal">
						<span>取消</span>
					</button>
					<button class="btn btn-success" type="button"
						id="edit_modal_submit">
						<span>确认更改</span>
					</button>
				</div>
			</div>
		</div>
	</div>
</body>
</html>