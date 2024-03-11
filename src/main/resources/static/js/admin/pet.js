function getShelList(e){
    $("#shelName option").remove();

    let target = document.getElementById("shelName");


    let shelter = document.getElementById("shelArea").value;

    console.log(shelter);



    $.ajax({
        url : "/pet/registerShelter", // 접속할 요청주소
        type : "post", // 전송방식(get, post)
        dataType : "json", // 서버에서 응답하는 데이터 형식
        data:shelter,
        contentType: "application/json; charset=utf-8"
    }).done(function(data){
        console.log(data);

        let shelArea = [];

        for(var i=0; i < data.length; i++ ){
            shelArea[i] = data[i].shelName;
        }

        for (x in shelArea){
            let opt = document.createElement("option");
            opt.value = shelArea[x];
            opt.innerHTML = shelArea[x];
            target.appendChild(opt);
        }

    }).fail(function(error){
        alert("에러 발생 :" + error);
    });

}

/**
 *  유효성 검사 javascript file -> *.js
 */

function inputChk(){
    if(document.petForm.petSpecies.value.length == 0){
        alert("강아지/고양이를 선택하세요!!");
        petForm.petSpecies.focus();
        return false;
    }

    if(document.petForm.petName.value.length == 0){
        alert("견/묘종을 적어주세요!!");
        petForm.petName.focus();
        return false;
    }

    if(document.petForm.petAge.value.length == 0){
        alert("나이를 적어주세요!!");
        petForm.petAge.focus();
        return false;
    }


    if(document.petForm.petGender.value.length == 0){
        alert("성별을 선택해주세요!!");
        petForm.petGender.focus();
        return false;
    }

    if(document.petForm.petNeutering.value.length == 0){
        alert("중성화 여부를 선택해주세요!!");
        petForm.petNeutering.focus();
        return false;
    }

    if(document.petForm.petInoculation.value.length == 0){
        alert("접종 여부를 선택해주세요!!");
        petForm.petInoculation.focus();
        return false;
    }

    if(document.petForm.petImage.value == ""){
        alert("사진을 등록 해주세요!!");
        return false;
    }

    if(document.petForm.shelArea.value.length == 0){
        alert("지역을 선택 해주세요!!");
        petForm.shelArea.focus();
        return false;
    }

    if(document.petForm.petPlace.value.length == 0){
        alert("발견된 장소를 상세히 적어주세요!!");
        petForm.petPlace.focus();
        return false;
    }
    // if(document.petForm.petPlace.value.length > 20){
    //     alert("발견된 장소를 너무 상세히 적었어요!!");
    //     petForm.petPlace.focus();
    //     return false;
    // }

    if(document.petForm.petRemarks.value.length == 0){
        alert("특이사항을 상세히 적어주세요!!");
        petForm.petRemarks.focus();
        return false;
    }
    // if(document.petForm.petRemarks.value.length > 20){
    //     alert("특이사항을 너무 상세히 적었어요!!");
    //     petForm.petRemarks.focus();
    //     return false;
    // }

    /* button 누르면 데이터가 action으로 감 */
    document.petForm.submit();
    return true;
}

/**
 *  유효성 검사 javascript file -> *.js // 수정OK
 */

function inputChk2(){
    if(document.petForm.petSpecies.value.length == 0){
        alert("강아지/고양이를 선택하세요!!");
        petForm.petSpecies.focus();
        return false;
    }

    if(document.petForm.petName.value.length == 0){
        alert("견/묘종을 적어주세요!!");
        petForm.petName.focus();
        return false;
    }

    if(document.petForm.petAge.value.length == 0){
        alert("나이를 적어주세요!!");
        petForm.petAge.focus();
        return false;
    }
    // if(document.petForm.petAge.value.length > 2){
    //     alert("나이를 정확하게 입력해주세요!!");
    //     petForm.petAge.focus();
    //     return false;
    // }

    if(document.petForm.petGender.value.length == 0){
        alert("성별을 선택해주세요!!");
        petForm.petGender.focus();
        return false;
    }

    if(document.petForm.petNeutering.value.length == 0){
        alert("중성화 여부를 선택해주세요!!");
        petForm.petNeutering.focus();
        return false;
    }

    if(document.petForm.petInoculation.value.length == 0){
        alert("접종 여부를 선택해주세요!!");
        petForm.petInoculation.focus();
        return false;
    }

    if(document.petForm.shelArea.value.length == 0){
        alert("지역을 선택 해주세요!!");
        petForm.shelArea.focus();
        return false;
    }

    if(document.petForm.petPlace.value.length == 0){
        alert("발견된 장소를 상세히 적어주세요!!");
        petForm.petPlace.focus();
        return false;
    }
    // if(document.petForm.petPlace.value.length > 20){
    //     alert("발견된 장소를 너무 상세히 적었어요!!");
    //     petForm.petPlace.focus();
    //     return false;
    // }

    if(document.petForm.petRemarks.value.length == 0){
        alert("특이사항을 상세히 적어주세요!!");
        petForm.petRemarks.focus();
        return false;
    }
    // if(document.petForm.petRemarks.value.length > 20){
    //     alert("특이사항을 너무 상세히 적었어요!!");
    //     petForm.petRemarks.focus();
    //     return false;
    // }

    /* button 누르면 데이터가 action으로 감 */
    document.petForm.submit();
    return true;
}

function deletePet(no){
    if(confirm("정말로 삭제 하시겠습니까?")){
        location = "/admin/petDelete?no="+no;
    }else{
        return false;
    }
}

