function join_submit(form){
	var idField = form.rm_id;
	var pwField = form.rm_pw;
	var pwChkField = form.pwCheck;
	
	if(isEmpty(idField) || containsHSChar(idField)){
		alert("ID를 맞게 입력해주세요!");
		idField.focus();
		idField.value = "";
		return false;
	}
	
	if(isEmpty(pwField) || chkSet(pwField, "1234567890")){
		alert("비밀번호를 올바르게 입력해주세요! (숫자를 반드시 포함)");
		pwField.focus();
		pwField.value = "";
		return false;
	}
	if(equalPw(pwField,pwChkField)){
		alert("입력한 비밀번호와 확인 비밀번호가 일치하지 않습니다!");
		pwChkField.focus();
		pwChkField.value = "";
		return false;
	}
	
	var nameField = form.rm_name;
	if(isEmpty(nameField)){
		alert("설정할 이름을 선택해주세요!");
		nameField.focus();
		return false;
	}
	var sn1 = form.sn1;
	var sn2 = form.sn2;
	if(isNotNum(sn1)||isNotNum(sn2)){
		alert("주민번호는 숫자만 입력할 수 있습니다!");
		return false;
	}
	
	
	var addr1 = form.addrNum;
	var addr2 = form.addr;
	var addr3 = form.addrSpec;
	
	if(isEmpty(addr1)||isEmpty(addr2)||isEmpty(addr3)||isNotNum(addr1)
			||isShort(addr1,5)){
		alert("올바른 주소를 입력해주쇼");
		return false;
	}
	
	var photoField = form.photoInput;
	
	if(isNotType(photoField, "jpg")&&isNotType(photoField, "png")&&isNotType(photoField, "gif")||
			isEmpty(photoField)){
		alert("사진은 .jpg .png, .gif 확장자만 선택이 가능합니다. 올바른 사진을 입력해주세요");
		return false;
	}
	
	
	
	return true;
}