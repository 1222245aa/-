//js的作用将json字符串转化为json对象给服务器，这里面我们传了map封装了Count和likeStatus
//传入的参数btn表示按钮
function like(btn, entityType, entityId, entityUserId, postId) {
    //${post}表示传给服务器的数据
    $.post(
        //先传路径
        CONTEXT_PATH + "/like",
        //再传参数
        {"entityType": entityType, "entityId": entityId, "entityUserId": entityUserId, "postId": postId},
        //再传数据
        function (data) {
            //将json转化为对象
            data = $.parseJSON(data);
            //0表示状态正确
            if (data.code == 0) {
                $(btn).children("i").text(data.likeCount);
                $(btn).children("b").text(data.likeStatus == 1 ? '已赞' : "赞");
            } else {
                //统一处理异常
                alert(data.msg);
            }
        }
    );
}
    // 置顶
    function setTop() {
        $.post(
            CONTEXT_PATH + "/discuss/top",
            {"id":$("#postId").val()},
            function(data) {
                data = $.parseJSON(data);
                if(data.code == 0) {
                    $("#topBtn").attr("disabled", "disabled");
                } else {
                    alert(data.msg);
                }
            }
        );
    }

// 加精
    function setWonderful() {
        $.post(
            CONTEXT_PATH + "/discuss/wonderful",
            {"id":$("#postId").val()},
            function(data) {
                data = $.parseJSON(data);
                if(data.code == 0) {
                    $("#wonderfulBtn").attr("disabled", "disabled");
                } else {
                    alert(data.msg);
                }
            }
        );
    }

// 删除
    function setDelete() {
        $.post(
            CONTEXT_PATH + "/discuss/delete",
            {"id":$("#postId").val()},
            function(data) {
                data = $.parseJSON(data);
                if(data.code == 0) {
                    location.href = CONTEXT_PATH + "/index";
                } else {
                    alert(data.msg);
                }
            }
        );
    }
