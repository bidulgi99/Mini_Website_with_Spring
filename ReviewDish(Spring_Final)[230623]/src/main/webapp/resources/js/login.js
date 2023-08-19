function loginCheck(f){
	var idField = f.rm_id;
	var pwField = f.rm_pw;
	if(isEmpty(idField)||isEmpty(pwField)){
		alert("빈칸없이 ID & PW를 입력해주세요!");
		return false;
	}
	
	return true;
}