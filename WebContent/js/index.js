/**
 * index.js
 */

$(function() {
	menuClickAction();
	welcomePageInit();
	homePage();	
});
// 加载欢迎界面
function welcomePageInit(){
	$('#panel').load('welcome.jsp');
}
// 跳回首页
function homePage(){
	$('.home').click(function(){
		window.location.href = "./index.jsp";
	})
}
// 侧边栏连接点击动作
function menuClickAction() {
	$(".menu_item").click(function() {
		var url = $(this).attr("name");
		delay(function(){
			$('#panel').load(url);
		}, 500);
	})
}
var delay = (function(){
	var timer = 0;
	return function(callback, ms){
	//重置js定时器
	clearTimeout (timer);
	//设定定时器
	timer = setTimeout(callback, ms);
	};
})();