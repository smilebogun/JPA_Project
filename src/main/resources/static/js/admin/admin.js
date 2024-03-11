// let a = { key:value, key:value} // 자바스크립트 객체 선언

let adminObject = {
    init: function(){
        // let _this = this;

        $("#btn-save").on("click", () =>{
              this.insertAdmin();
        });

        $("#btn-delete").on("click", () =>{
            this.deleteAdmin();
        });

        $("#btn-update").on("click", () =>{
            this.updateAdmin();
        });
    },

    insertAdmin: function () {
        alert("회원 가입 요청");
        let admin = {
            adminId: $("#adminId").val(),
            adminPw: $("#adminPw").val(),
            adminName: $("#adminName").val(),
            adminTel: $("#adminTel").val(),
            adminEmail: $("#adminEmail").val(),
            adminRole: $("#adminRole").val()
        };

        // console.log(admin);

        // done() : 성공 처리되었을 때
        // fail() : 실패했을 때
        $.ajax({
            type:"post",
            url:"/admin/adminRegister",
            data:JSON.stringify(admin),
            contentType: "application/json; charset=utf-8"
        }).done(function(response){
            // // let warning = "";
            // let errors = response.data; // errorMap
            // console.log("errors = " + errors);
            // // console.log("errors:" + errors.adminId);
            // // if(errors.adminId != null) warning = warning + errors.adminId + "\n";
            // // if(errors.adminEmail != null) warning = warning + errors.adminEmail + "\n";
            // alert(errors)

            // 강사님
            let status = response.status;
            if(status === 409) { // 409 = CONFLICT, 400 = BAD.REQUEST
                let errors = response.data;
                alert(errors);
            }else if(status === 200){
                alert("회원가입 완료!!")
                location = "/adminHome";
            }else{
                let warning = "";
                let errors = response.data; // errorMap
                console.log("errors = " + errors);
                // console.log("errors:" + errors.adminId);
                if(errors.adminId != null) warning = warning + errors.adminId + "\n";
                if(errors.adminEmail != null) warning = warning + errors.adminEmail + "\n";
                alert(warning);
            }

        }).fail(function(error){
            alert("에러 발생 : " + error);
        });
    },

    deleteAdmin: function () {
        alert("관리자 삭제 요청");
        let no = $("#no").val();

        if(confirm("정말로 삭제 하시겠습니까?")) {
            // done() : 성공 처리되었을 때
            // fail() : 실패했을 때
            $.ajax({
                type:"DELETE",
                url:"/admin/" + no
            }).done(function(response){
                // console.log(response.data);
                if(response.status == 200){
                    alert(response.data);
                    location = "/admin/adminList";
                }else{
                    alert(response.data);
                }
            }).fail(function(error){
                alert("에러 발생 : " + error);
            });
        } else {
            return false;
        }


    },

    updateAdmin: function () {
        alert("관리자 수정 요청");
        let admin = {
            no : $("#no").val(),
            adminId: $("#adminId").val(),
            adminPw: $("#adminPw").val(),
            adminName: $("#adminName").val(),
            adminTel: $("#adminTel").val(),
            adminEmail: $("#adminEmail").val(),
        };

        // done() : 성공 처리되었을 때
        // fail() : 실패했을 때
        $.ajax({
            type:"PUT",
            url:"/admin",
            data:JSON.stringify(admin),
            contentType: "application/json; charset=utf-8"
        }).done(function(response){
            // console.log(response.data);
            if(response.status == 200){
                alert(response.data);
                location = "/admin/adminList";
            }else{
                console.log(response.status);
            }
        }).fail(function(error){
            alert("에러 발생 : " + error);
        });
    },
}

adminObject.init();