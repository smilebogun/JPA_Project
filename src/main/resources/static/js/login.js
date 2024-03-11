let loginObject= {
    init: function(){
        //let _this = this;   // this.insertUser(); 로 사용해도 됌

        // 람다식으로 진행
        $("#btn-login").on("click", () =>{
            this.loginUser();
        });
    },  // init: function()

    loginUser: function(){
        alert("로그인 요청")
        let login = {
            id: $("#id").val(),
            pw: $("#pw").val()
        };
        console.log(login);

        // .done() : 성공처리되었을때 처리되는 함수
        // .fail() : 실패했을대 처리되는 함수
        $.ajax({
            type:"post",
            url:"/memberLogin.do",
            data:JSON.stringify(login),
            // let user라는 유저정보가 담긴 객체를 JSON형태인 .stringify(user)를 사용해서 보냄
            contentType:"application/json; charset=utf-8"
        }).done(function(response){
            console.log(response);
            console.log(response.status);
            console.log(response.data);
            if(response.status == 200){
                alert(response.data)
                location = "/";
            } else {
                alert(response.data)
            }
        }).fail(function(error){
            console.log(error)
            alert("에러발생 : "+ error);
        });

    },  // insertUser: function()
} // let userObject

loginObject.init();