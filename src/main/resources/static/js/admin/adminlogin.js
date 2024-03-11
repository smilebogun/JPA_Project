// let a = { key:value, key:value} // 자바스크립트 객체 선언

let adminloginObject = {
    init: function(){
        $("#btn-login").on("click", () =>{
              this.adminlogin();
        });
    },
    adminlogin: function () {
        alert("로그인 요청");
        let admin = {
            adminId: $("#adminId").val(),
            adminPw: $("#adminPw").val(),
        };

        // done() : 성공 처리되었을 때
        // fail() : 실패했을 때
        $.ajax({
            type:"post",
            url:"/admin/adminLogin",
            data:JSON.stringify(admin),
            contentType: "application/json; charset=utf-8"
        }).done(function(response){
            // console.log(response.data);
            if(response.status == 200){
                location = "/adminHome";
            }else{
                alert(response.data);
            }
        }).fail(function(error){
            alert("에러 발생 : " + error);
        });
    },
}
adminloginObject.init();