let userObject= {
    init: function(){
        let _this = this;   // this.insertUser(); 로 사용해도 됌

        // 람다식으로 진행
        $("#btn-save").on("click", () =>{
            _this.insertUser();
        });
    },  // init: function()

    insertUser: function(){
        alert("회원가입 요청")

        let isIdCheck = $("#isIdCheck").val();
        let isPwCheck = $("#isPwCheck").val();
        let isTelCheck = $("#isTelCheck").val();

        if(isIdCheck === "no" || isPwCheck === "no"){
            alert("아이디 중복체크 또는 비밀번호를 확인해주세요!!")
            $("#id").select();
            return false;
        }

        if(isTelCheck === "no"){
            alert("전화번호 중복체크를 확인해주세요!!")
            $("#tel").select();
            return false;
        }

        if(!inputChk()){	// inputChk() 함수를 호출하면서 리턴값이 false일때
            alert("inputchk() 함수에서 거부");    // valid.js
            return false;
        }

        if(!isEmailCheck){
            alert("이메일 인증을 해주세요!!");
            $("#email").select();
            return false;
        }

        let user = {
            id: $("#id").val(),
            pw: $("#pw").val(),
            name: $("#name").val(),
            age: $("#age").val(),
            tel: $("#tel").val(),
            email: $("#email").val(),
            zipcode: $("#zipcode").val(),
            roadAddr: $("#roadAddr").val(),
            detailAddr: $("#detailAddr").val(),
            plusAddr: $("#plusAddr").val()
        };
        console.log(user);

        // .done() : 성공처리되었을때 처리되는 함수
        // .fail() : 실패했을대 처리되는 함수
        $.ajax({
            type:"post",
            url:"/memberInsert.do",
            data:JSON.stringify(user),      
            // let user라는 유저정보가 담긴 객체를 JSON형태인 .stringify(user)를 사용해서 보냄
            contentType:"application/json; charset=utf-8"
        }).done(function(response){
            console.log(response);
            console.log(response.status);
            console.log(response.data);
            // 유효성쪽 에러체크
            let warning = "";
            let errors = response.data; // 넘어온 errorMap
            let status = response.status; // 넘어온 errorMap
            console.log("status = ", status);

            if(response.status == 200){
                alert(response.data)
                location = "/";
            } else if(status === 409) { // CONFLICT
                alert(errors);
            } else {        // 오류코드 400번일때 // BAD_REQUEST
                if(errors.name != null) warning = warning + errors.name + "\n";
                if(errors.pw!= null) warning = warning + errors.pw + "\n";
                if(errors.email != null) warning = warning + errors.email + "\n";
                alert(warning);
            }
        }).fail(function(error){
            console.log(error)
            alert("에러발생 : "+ error);
        });

    },  // insertUser: function()
} // let userObject

userObject.init();