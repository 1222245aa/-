
$(function(){
	$("#publishBtn").click(publish);
});
//# sourceURL=dynamicScript.j
function publish() {
	$("#publishModal").modal("hide");
	//获取标题和内容
	var title = $("#recipient-name").val();
	var content = $("#message-text").val();
	//发送异步请求（post）
	$.post(
		CONTEXT_PATH + "/discuss/add",
		{"title":title,"content":content},
		function (data) {
			data = $.parseJSON(data);
			//再提示框中显示返回消息
			$("#hihtBody").text(data.msg);
			//显示提示框
			$("#hintModal").modal("show");
			//两秒后，自动隐藏提示框
			setTimeout(function(){
				$("#hintModal").modal("hide");
				//刷新页面
				if (data.code == 0) {
					window.location.reload();
				}
				}, 2000);
		}
	)


}