/**
 *  유효성 검사 javascript file -> *.js
 */

function inputChk(){
	if(document.joinForm.id.value.length == 0){
		alert("아이디를 입력하세요!!");
		joinForm.id.focus();
		return false;
	}
	
	if(document.joinForm.id.value.length < 4){
		alert("아이디는 최소 4글자 이상이어야 합니다!!");
		joinForm.id.focus();
		return false;
	}
	
	if(document.joinForm.pw.value.length == 0){
		alert("비밀번호를 입력하세요!!");
		joinForm.pw.focus();
		return false;
	}
	
	if(document.joinForm.pw.value.length < 4){
		alert("비밀번호는 최소 4글자 이상이어야 합니다!!");
		joinForm.pw.focus();
		return false;
	}
	
	if(document.joinForm.pw.value != document.joinForm.pwConfirm.value){
		alert("비밀번호가 일치하지 않습니다.");
		joinForm.pwConfirm.focus();
		return false;
	}
	
	if(document.joinForm.name.value.length == 0){
		alert("이름을 입력하세요!!");
		joinForm.name.focus();
		return false;
	}
	
	if(document.joinForm.age.value.length == 0){
		alert("나이를 입력하세요!!");
		joinForm.age.focus();
		return false;
	}
	
	if(document.joinForm.age.value.length > 2){
		alert("나이는 2자리까지만 입력이 가능합니다!!");
		joinForm.age.focus();
		return false;
	}
	
	if(document.joinForm.tel.value.length == 0){
		alert("전화번호를 입력하세요!!");
		joinForm.tel.focus();
		return false;
	}
	
	if(document.joinForm.email.value.length == 0){
		alert("이메일을 입력하세요!!");
		joinForm.email.focus();
		return false;
	}
	
	if(document.joinForm.zipcode.value.length == 0){
		alert("우편번호를 입력하세요!!");
		joinForm.addr.focus();
		return false;
	}
	
	if(document.joinForm.roadAddr.value.length == 0){
		alert("주소를 입력하세요!!");
		joinForm.addr.focus();
		return false;
	}
	
	if(document.joinForm.detailAddr.value.length == 0){
		alert("상세주소를 입력하세요!!");
		joinForm.addr.focus();
		return false;
	}
	
	if(document.joinForm.plusAddr.value.length == 0){
		alert("참고항목을 입력하세요!!");
		joinForm.addr.focus();
		return false;
	}

	/* button 누르면 데이터가 action으로 감 */
	// document.joinForm.submit();
	return true;
}