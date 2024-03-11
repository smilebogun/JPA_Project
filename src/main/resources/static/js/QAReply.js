let QAReplyObject = {

    init: function(){
        // let _this = this;
        $("#btn-save-QAReply").on("click", () =>{
            // _this.insertUser();
            this.insertQAReply(); // this = userObject
        });
        $("#btn-save-adminQAReply").on("click", () =>{
            // _this.insertUser();
            this.insertAdminQAReply(); // this = userObject
        });

        // if($("button[name="+rno+']').text() === "수정완료"){
        //     $("button[name="+rno+']').on("click", () =>{
        //         // _this.insertUser();
        //         this.updateReply(); // this = userObject
        //     });
        // }

    },
    QAUpdate : function (id){
          let content = $("textarea[name="+id+']');
          let reUpdate = $("button[name="+id+']');
          let rplUpdate = $("button[name="+id+'a]')

      if("textarea[name="+id+']') {
          content.attr("disabled", false);
          content.select();
          content[0].style.border = "1px solid black"
          reUpdate.text("수정완료")
          reUpdate.attr("hidden", true)
          rplUpdate.attr("hidden", false)
      }
    },
    updateQAReply: function (rid,bid) {
        alert("댓글 등록 요청");

        let id = $("#id").val();
        let QAReplyContent =$("#re-content").val()

        //alert(QAReplyContent);
        // done() : 성공 처리되었을 때
        // fail() : 실패했을 때
        $.ajax({
            type:"put",
            url:"/QAR/"+rid,
            data:QAReplyContent,
            contentType: "text/plain;charset=UTF-8"
        }).done(function(response){
            if(response.status == 200){
                alert(response.data);
                location = "/QA/get/"+bid;
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

    insertQAReply: function () {
        alert("댓글 등록 요청");

        let id = $("#id").val();

         let QAReplyContent =$("#QAReply-content").val()

        //alert(id);
        //alert(QAReplyContent);
        // done() : 성공 처리되었을 때
        // fail() : 실패했을 때
        $.ajax({
            type:"post",
            url:"/QAR/"+id,
            data:QAReplyContent,
            contentType: "text/plain;charset=UTF-8"
        }).done(function(response){
            if(response.status == 200){
                alert(response.data);
                location = "/QA/get/"+id;
            }else{
                alert(response.data);
            }
        }).fail(function(error){
            alert("에러 발생 : " + error);
        });
    },
    deleteQAReply: function (rno,id) {
        alert("댓글 삭제 요청");

            $.ajax({
                type:"delete",
                url:"/QAR/"+rno,
            }).done(function(response){
                if(response.status === 200){
                    alert(response.data);
                    location = "/QA/get/"+id;
                }else{
                    alert(response.data);
                }
            }).fail(function(error){
                alert("에러 발생 : " + error);
            });

    },
    insertAdminQAReply: function () {
        alert("댓글 등록 요청");

        let id = $("#id").val();
        let QAReplyContent =$("#QAReply-content").val()

        //alert(id);
        //alert(QAReplyContent);
        // done() : 성공 처리되었을 때
        // fail() : 실패했을 때
        $.ajax({
            type:"post",
            url:"/QAR/"+id,
            data:QAReplyContent,
            contentType: "text/plain;charset=UTF-8"
        }).done(function(response){
            if(response.status == 200){
                alert(response.data);
                location = "/admin/QA/get/"+id;
            }else{
                alert(response.data);
            }
        }).fail(function(error){
            alert("에러 발생 : " + error);
        });
    },
    updateAdminQAReply: function (rid,bid) {
        alert("댓글 등록 요청");

        let id = $("#id").val();
        let QAReplyContent =$("#re-content").val()

        //alert(QAReplyContent);
        // done() : 성공 처리되었을 때
        // fail() : 실패했을 때
        $.ajax({
            type:"put",
            url:"/QAR/"+rid,
            data:QAReplyContent,
            contentType: "text/plain;charset=UTF-8"
        }).done(function(response){
            if(response.status == 200){
                alert(response.data);
                location = "/admin/QA/get/"+bid;
            }else{
                alert(response.data);
            }
        }).fail(function(error){
            alert("에러 발생 : " + error);
        });
    },
    deleteAdminQAReply: function (rno,id) {
        alert("댓글 삭제 요청");

        $.ajax({
            type:"delete",
            url:"/QAR/"+rno,
        }).done(function(response){
            if(response.status == 200){
                alert(response.data);
                location = "/admin/QA/get/"+id;
            }else{
                alert(response.data);
            }
        }).fail(function(error){
            alert("에러 발생 : " + error);
        });
    },

};


QAReplyObject.init();