let replyObject = {

    init: function(){
        // let _this = this;
        let rno = $("#rno").val();
        $("#btn-save-reply").on("click", () =>{
            // _this.insertUser();
            this.insertReply(); // this = userObject
        });
        $("#btn-save-adminReply").on("click", () =>{
            // _this.insertUser();
            this.insertAdminReply(); // this = userObject
        });

        // if($("button[name="+rno+']').text() === "수정완료"){
        //     $("button[name="+rno+']').on("click", () =>{
        //         // _this.insertUser();
        //         this.updateReply(); // this = userObject
        //     });
        // }

    },
    update : function (rno){
        let content = $("textarea[name="+rno+']');
        let reUpdate = $("button[name="+rno+']');
        let rplUpdate = $("button[name="+rno+'a]')

        if("textarea[name="+rno+']') {
            content.attr("disabled", false);
            content.select();
            content[0].style.border = "1px solid black"
            reUpdate.text("수정완료")
            reUpdate.attr("hidden", true)
            rplUpdate.attr("hidden", false)
        }
    },
    updateReply: function (rno,bno) {
        alert("댓글 수정 요청");
        let content = $("textarea[name="+rno+']');
        // let rno = $("#rno").val();
        // let bno = $("#bno").text();

        let reply = {
            replyContent: content.val(),
        };
        //alert(reply)
        // done() : 성공 처리되었을 때
        // fail() : 실패했을 때
        $.ajax({
            type:"post",
            url:"/reply/update/"+rno,
            data:JSON.stringify(reply),
            contentType: "application/json; charset=utf-8"
        }).done(function(response){
            if(response.status == 200){
                alert(response.data);
                location = "/post/get/"+bno;
            }else{
                alert(response.data);
            }
        }).fail(function(error){
            alert("에러 발생 : " + error);
        });
    },
//     function update(rno,bno) {
//     if($("textArea[name=rno]")){
//         $("#re-content").attr("disabled",false)
//     }
// }

    insertReply: function () {
        alert("댓글 등록 요청");

        let bno = $("#bno").val();

        let reply = {
            replyContent: $("#reply-content").val(),
        };
        //alert(reply.replyContent)
        // done() : 성공 처리되었을 때
        // fail() : 실패했을 때
        $.ajax({
            type:"post",
            url:"/reply/"+bno,
            data:JSON.stringify(reply),
            contentType: "application/json; charset=utf-8"
        }).done(function(response){
            if(response.status == 200){
                alert(response.data);
                location = "/post/get/"+bno;
            }else{
                alert(response.data);
            }
        }).fail(function(error){
            alert("에러 발생 : " + error);
        });
    },
    deleteReply: function (rno,bno) {
        alert("댓글 삭제 요청");

        $.ajax({
            type:"delete",
            url:"/reply/"+rno,
        }).done(function(response){
            if(response.status == 200){
                alert(response.data);
                location = "/post/get/"+bno;
            }else{
                alert(response.data);
            }
        }).fail(function(error){
            alert("에러 발생 : " + error);
        });
    },
    updateAdminReply: function (rno,bno) {
        alert("댓글 수정 요청");
        let content = $("textarea[name="+rno+']');
        // let rno = $("#rno").val();
        // let bno = $("#bno").text();

        let reply = {
            replyContent: content.val(),
        };
        //alert(reply)
        // done() : 성공 처리되었을 때
        // fail() : 실패했을 때
        $.ajax({
            type:"post",
            url:"/reply/update/"+rno,
            data:JSON.stringify(reply),
            contentType: "application/json; charset=utf-8"
        }).done(function(response){
            if(response.status == 200){
                alert(response.data);
                location = "/admin/post/get/"+bno;
            }else{
                alert(response.data);
            }
        }).fail(function(error){
            alert("에러 발생 : " + error);
        });
    },
//     function update(rno,bno) {
//     if($("textArea[name=rno]")){
//         $("#re-content").attr("disabled",false)
//     }
// }

    insertAdminReply: function () {
        alert("댓글 등록 요청");

        let bno = $("#bno").val();

        let reply = {
            replyContent: $("#reply-content").val(),
        };
        //alert(reply)
        // done() : 성공 처리되었을 때
        // fail() : 실패했을 때
        $.ajax({
            type:"post",
            url:"/admin/reply/"+bno,
            data:JSON.stringify(reply),
            contentType: "application/json; charset=utf-8"
        }).done(function(response){
            if(response.status == 200){
                alert(response.data);
                location = "/admin/post/get/"+bno;
            }else{
                alert(response.data);
            }
        }).fail(function(error){
            alert("에러 발생 : " + error);
        });
    },
    deleteAdminReply: function (rno,bno) {
        alert("댓글 삭제 요청");

        $.ajax({
            type:"delete",
            url:"/reply/"+rno,
        }).done(function(response){
            if(response.status == 200){
                alert(response.data);
                location = "/admin/post/get/"+bno;
            }else{
                alert(response.data);
            }
        }).fail(function(error){
            alert("에러 발생 : " + error);
        });
    },

};


replyObject.init();