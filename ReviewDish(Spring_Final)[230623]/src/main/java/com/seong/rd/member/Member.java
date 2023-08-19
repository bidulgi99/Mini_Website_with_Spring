package com.seong.rd.member;

import java.util.Date;

public class Member {
	private String rm_id;
	private String rm_pw;
	private String rm_name;
	private Date rm_birthday;
	private String rm_address;
	private String rm_photo;
	
	public Member() {
		// TODO Auto-generated constructor stub
	}

	public Member(String rm_id, String rm_pw, String rm_name, Date rm_birthday, String rm_address, String rm_photo) {
		super();
		this.rm_id = rm_id;
		this.rm_pw = rm_pw;
		this.rm_name = rm_name;
		this.rm_birthday = rm_birthday;
		this.rm_address = rm_address;
		this.rm_photo = rm_photo;
	}

	public String getRm_id() {
		return rm_id;
	}

	public void setRm_id(String rm_id) {
		this.rm_id = rm_id;
	}

	public String getRm_pw() {
		return rm_pw;
	}

	public void setRm_pw(String rm_pw) {
		this.rm_pw = rm_pw;
	}

	public String getRm_name() {
		return rm_name;
	}

	public void setRm_name(String rm_name) {
		this.rm_name = rm_name;
	}

	public Date getRm_birthday() {
		return rm_birthday;
	}

	public void setRm_birthday(Date rm_birthday) {
		this.rm_birthday = rm_birthday;
	}

	public String getRm_address() {
		return rm_address;
	}

	public void setRm_address(String rm_address) {
		this.rm_address = rm_address;
	}

	public String getRm_photo() {
		return rm_photo;
	}

	public void setRm_photo(String rm_photo) {
		this.rm_photo = rm_photo;
	}
	
}
