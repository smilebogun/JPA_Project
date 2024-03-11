function sample6_execDaumPostcode() {
    new daum.Postcode({
        oncomplete: function(data) {
            // 팝업에서 검색결과 항목을 클릭했을때 실행할 코드를 작성하는 부분.

            // 각 주소의 노출 규칙에 따라 주소를 조합한다.
            // 내려오는 변수가 값이 없는 경우엔 공백('')값을 가지므로, 이를 참고하여 분기 한다.
            var addr = ''; // 주소 변수
            var extraAddr = ''; // 참고항목 변수

            //사용자가 선택한 주소 타입에 따라 해당 주소 값을 가져온다.
            if (data.userSelectedType === 'R') { // 사용자가 도로명 주소를 선택했을 경우
                addr = data.roadAddress;
            } else { // 사용자가 지번 주소를 선택했을 경우(J)
                addr = data.jibunAddress;
            }

            // 사용자가 선택한 주소가 도로명 타입일때 참고항목을 조합한다.
            if(data.userSelectedType === 'R'){
                // 법정동명이 있을 경우 추가한다. (법정리는 제외)
                // 법정동의 경우 마지막 문자가 "동/로/가"로 끝난다.
                if(data.bname !== '' && /[동|로|가]$/g.test(data.bname)){
                    extraAddr += data.bname;
                }
                // 건물명이 있고, 공동주택일 경우 추가한다.
                if(data.buildingName !== '' && data.apartment === 'Y'){
                    extraAddr += (extraAddr !== '' ? ', ' + data.buildingName : data.buildingName);
                }
                // 표시할 참고항목이 있을 경우, 괄호까지 추가한 최종 문자열을 만든다.
                if(extraAddr !== ''){
                    extraAddr = ' (' + extraAddr + ')';
                }
                // 조합된 참고항목을 해당 필드에 넣는다.
                // document.getElementById("sample6_extraAddress").value = extraAddr;

            } else {
                // document.getElementById("sample6_extraAddress").value = '';
            }

            // 우편번호와 주소 정보를 해당 필드에 넣는다.
            document.getElementById('sample6_postcode').value = data.zonecode;
            document.getElementById("sample6_address").value = addr;
            // 커서를 상세주소 필드로 이동한다.
            document.getElementById("sample6_detailAddress").focus();
        }
    }).open();
}
//shelter 등록
let shelterObject = {
    init:function () {
        let _this = this;

        $("#btn-save").on("click", () => {
            _this.insertShelter();
        });

        $("#btn-update").on("click", () => {
            _this.updateShelter();
        });

        $("#btn-delete").on("click", () => {
            _this.deleteShelter();
        });


    },
    insertShelter: function(){
        alert("보호소 등록 요청");
        let shelter = {
            shelName: $("#shel_name").val(),
            shelArea: $("#shel_area option:selected").val(),
            shelAddress: $("#sample6_address").val(),
            shelDetailAddress: $("#sample6_detailAddress").val(),
            shelPostcode: $("#sample6_postcode").val(),
            shelTel: $("#shel_tel").val()
        };

        console.log(shelter);

        $.ajax({
            type:"post",
            url:"/shelter",
            data:JSON.stringify(shelter),
            contentType: "application/json; charset=utf-8"
        }).done(function(response){
            console.log(response);
            let status = response.status;
            if(status == 200 || status == 409){
                let errors = response.data;
                alert(errors);
                location = "/admin/shelterList";
            }else {
                let warning = "";
                let errors = response.data;
                if (errors.shelName != null) warning = warning + errors.shelName + "\n";
                if (errors.shelArea != null) warning = warning + errors.shelArea;
                alert(warning);
            }

        }).fail(function(error){
            alert("에러 발생 :" + error);
        });

    },
    // 수정
    updateShelter: function(){
        alert("보호소 수정 요청");
        let shelter = {
            no : $("#no").val(),
            shelName: $("#shel_name").val(),
            shelArea: $("#shel_area option:selected").val(),
            shelAddress: $("#sample6_address").val(),
            shelDetailAddress: $("#sample6_detailAddress").val(),
            shelPostcode: $("#sample6_postcode").val(),
            shelTel: $("#shel_tel").val()
        };

        console.log(shelter);

        $.ajax({
            type:"PUT",
            url:"/shelter",
            data:JSON.stringify(shelter),
            contentType: "application/json; charset=utf-8"
        }).done(function(response){
            console.log(response);
            if(response.status == 200){
                alert(response.data);
                location="/admin/shelterList";
            }else {
                let warning = "";
                let errors = response.data;
                if (errors.shelName != null) warning = warning + errors.shelName + "\n";
                if (errors.shelArea != null) warning = warning + errors.shelArea;
                alert(warning);
                alert("수정실패");
            }

        }).fail(function(error){
            alert("에러 발생 :" + error);
        });

    },

    //삭제
    deleteShelter: function(no){
        alert("보호소 삭제 요청");
        console.log(no);

        if(confirm("정말로 삭제 하시겠습니까?")){
            $.ajax({
                type:"delete",
                url:"/shelter/"+no
            }).done(function(response){
                console.log(response);
                if(response.status == 200){
                    alert(response.data);
                    alert("삭제완료")
                    location="/admin/shelterList";
                }else {
                    alert("삭제실패");
                }

            }).fail(function(error){
                alert("에러 발생 :" + error);
            });
        } else {
            return false;
        }
    }

}
shelterObject.init();





