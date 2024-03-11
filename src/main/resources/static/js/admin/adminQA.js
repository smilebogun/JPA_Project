let QAObject = {
    init: function(){
        // let _this = this;

        $("#register").on("click", () =>{
            this.insertQA();
        });
        // $("#update").on("click",() => {
        //    this.updateQA();
        // });
        $("#QA-del").on("click",() => {
            this.deleteAdminQA();
        })

    },

    insertQA: function (){
        alert("공지사항 등록")
        //입력 받은 값 받아오기
        // let image = $("#image");
        // let files = image[0].files[0];
        let QA={
               qaTitle : $("#title").val(),
               qaContent : $("#content").val()
            };

        // data.append("data",files);
        // done() : 성공 처리되었을 때
        // fail() : 실패했을 때
        $.ajax({
            type: "post",
            url: "/SC/qaRegister",
            data:JSON.stringify(QA),
            // let user라는 유저정보가 담긴 객체를 JSON형태인 .stringify(user)를 사용해서 보냄
            contentType:"application/json; charset=utf-8"
        }).done(function (response) {
            let warning = ""
            let errors = response.data;
            if(errors.qaTitle != null)warning = warning + errors.qaTitle + "\n";
            if(errors.qaContent != null)warning = warning + errors.qaContent;

            if(response.status == 200){
                alert(response.data);
                location = "/post";
            }else {
                console.log(warning);
                alert(warning);
            }
        }).fail(function(error){
            alert("에러 발생 : " + error);
        });
    },
    deleteAdminQA: function (){
        alert("공지사항 수정")
        let id = $("#id").val()

        $.ajax({
            type: "delete",
            url: "/QA/"+id
        }).done(function (response) {
            let warning = ""
            let errors = response.data;
            if(errors.qaTitle != null)warning = warning + errors.qaTitle + "\n";
            if(errors.qaContent != null)warning = warning + errors.qaContent;

            if(response.status == 200){
                alert(response.data);
                location = "/SC/Q&A";
            }else {
                console.log(warning);
                alert(warning);
            }
        }).fail(function(error){
            alert("에러 발생 : " + error);
        });
    },
}

QAObject.init();