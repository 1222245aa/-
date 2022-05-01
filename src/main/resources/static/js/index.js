
$(function(){
	$("#publishBtn").click(publish);
});
//# sourceURL=dynamicScript.j
function publish() {
	$("#publishModal").modal("hide");
	$("#hintModal").modal("show");
	setTimeout(function(){
		$("#hintModal").modal("hide");
	}, 2000);
}