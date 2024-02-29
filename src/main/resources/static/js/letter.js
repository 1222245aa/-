$(function(){
	//点击对话框的时候会调用这个方法
	$("#sendBtn").click(send_letter);
	$(".close").click(delete_msg);
});

function send_letter() {
	//将对话框隐藏
	$("#sendModal").modal("hide");
	//这中间是我们将数据发送并返回的地方
	var toName = $("#recipient-name").val();
	var content = $("#message-text").val();
	$.post(
		CONTEXT_PATH + "/letter/send",
		{"toName":toName,"content":content},
		function (data) {
			data = $.parseJSON(data);
			if (data.code == 0) {
				$("#hintBody").text("发送成功");
			}
			else {
				$("#hintBody").text(data.msg);
			}
			//将提示框展示
			$("#hintModal").modal("show");

			setTimeout(function(){
				$("#hintModal").modal("hide");
				location.reload();
			}, 2000);
		}
	);

}

function delete_msg() {
	// TODO 删除数据
	$(this).parents(".media").remove();
}