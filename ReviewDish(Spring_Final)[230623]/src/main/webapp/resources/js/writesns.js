function sns_write_check(f){
	var title = f.rs_color;
	var txt = f.rs_txt;
	
	if(isEmpty(txt)){
		alert("내용을 작성해주세요!");
		return false;
	}
	

	
	return true;
}

function snsPageChange(page){
	location.href = "sns.page.change?page=" + page;
}

function delMsg(no){ //파라미터 명과 필드명이 같아야하므로
	if(confirm("해당 게시글을 삭제하시겠습니까?")){
		location.href = "sns.delete?rs_no="+no;
		
	}
}

function updateMsg(no,txt,page){
	txt = prompt("수정할 내용", txt, page);
	if(txt!=null){
		location.href = "sns.update?rs_no="+no+"&rs_txt="+txt+"&page="+page;
	}
}

function writeReply(f){
	var replyField = f.replyInput;
	if(isEmpty(replyField)){
		alert("댓글 내용을 입력해주세요!");
		replyField.focus();
		return false;
	}
	return true;
}

function updateReply(no,txt,rr_rs_no,page) {
	txt = prompt("수정할 내용", txt, page);
	if(txt!=null){
		location.href = "sns.reply.update?rr_no="+no+"&rr_rs_no="+rr_rs_no+"&rr_txt="+txt+"&page="+page;
	}
}

function deleteReply(no, page){ 
	if(confirm("해당 댓글을 삭제하시겠습니까?")){
		location.href = "sns.reply.delete?rr_no="+no+"&page="+page;
		
	}
}

function deleteFile(no,filename){
	if(confirm("해당 파일을 삭제하시겠습니까?")){
		location.href = "dataroom.delete?rd_no="+no+"&rd_file="+filename;
	}
}