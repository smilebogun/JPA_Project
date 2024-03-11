let adoptObject= {
    init: function(){
        let _this = this;   // this.insertUser(); 로 사용해도 됌

        // 람다식으로 진행
        $("#btn-adopt").on("click", () =>{
            _this.insertAdopt();
        });
    },  // init: function()

    insertAdopt: function(){
        alert("입양예정 요청")
        //let petNo = $("#petNo").val();
        let adopt = {
            petNo: $("#petNo").val(),
            shelName: $("#shelName").val(),
            shelArea: $("#shelArea").val(),
            petSpecies: $("#petSpecies").val(),
            petName: $("#petName").val(),
            petAge: $("#petAge").val(),
            petGender: $("#petGender").val(),
            petNeutering: $("#petNeutering").val(),
            petInoculation: $("#petInoculation").val(),
            petPlace: $("#petPlace").val(),
            petRemarks: $("#petRemarks").val(),
            petImage: $("#petImage").val(),
            petAdopt: $("#petAdopt").val(),
            petSysdate: $("#petSysdate").val(),
            petUpdate: $("#petUpdate").val()
        };
        console.log(adopt);

        // .done() : 성공처리되었을때 처리되는 함수
        // .fail() : 실패했을대 처리되는 함수
        $.ajax({
            type:"post",
            url:"/petAdopt.do",
            //url:"/petAdopt.do/"+petNo,
            data:JSON.stringify(adopt),
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
                if(errors != null) warning = warning + errors + "\n";
                alert(warning);
            }
        }).fail(function(error){
            console.log(error)
            alert("에러발생 : "+ error);
        });

    },  // insertadopt: function()
} // let adoptObject

adoptObject.init();