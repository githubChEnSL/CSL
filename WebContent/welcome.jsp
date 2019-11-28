<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>welcome</title>
</head>
<script type="text/javascript">
	$(function(){
		ReturnStore();
		ReturnRegulator();
		ReturnUser();
	})
	//转向门店管理
	function ReturnStore(){
		$('#ReturnStoresMangement').click(function(){
			$.ajax({
				type : "GET",
				url:"StoresMangement.jsp",
				dataType : "html",
				success:function(data){
					$('#panel').html(data);
				},
				error:function(data){
					$('#tipsmsg').html("请求错误");
					$('#TipsModal').modal('show');
				}
			})
		})
	}
	//转向员工管理
	function ReturnRegulator(){
		$('#ReturnRegulatorsMangement').click(function(){
			$.ajax({
				type : "GET",
				url:"RegulatorsMangement.jsp",
				dataType : "html",
				success:function(data){
					$('#panel').html(data);
				},
				error:function(data){
					$('#tipsmsg').html("请求错误");
					$('#TipsModal').modal('show');
				}
			})
		})
	}
	//转向会员管理
	function ReturnUser(){
		$('#ReturnUsersMangement').click(function(){
			$.ajax({
				type : "GET",
				url:"UsersMangement.jsp",
				dataType : "html",
				success:function(data){
					$('#panel').html(data);
				},
				error:function(data){
					$('#tipsmsg').html("请求错误");
					$('#TipsModal').modal('show');
				}
			})
		})
	}
</script>
<body>
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
							<a href="javascript:void(0)" class="thumbnail shortcut" id="ReturnStoresMangement">
								<h3 class="title">门店管理</h3>
							</a>
						</div>
						<div class="col-md-4 col-sm-4">
							<a href="javascript:void(0)" class="thumbnail shortcut" id="ReturnRegulatorsMangement">
								<h3 class="title">员工管理</h3>
							</a>
						</div>
						<div class="col-md-4 col-sm-4">
							<a href="javascript:void(0)" class="thumbnail shortcut" id="ReturnUsersMangement">
								<h3 class="title" >会员管理</h3>
							</a>
						</div>
					</div>
					<div class="col-md-1"></div>
				</div>
			</div>
		</div>
	</div>
</body>
</html>