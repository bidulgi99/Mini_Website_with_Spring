package com.seong.rd.sns;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

import com.seong.rd.reply.Reply;

public class SNSMsg {
	private BigDecimal rs_no;
	private String rs_writer;
	private String rs_txt;
	private Date rs_date;
	private String rs_color;
	private String rm_photo;
	private List<Reply> rs_reply;
	
	public SNSMsg() {
		// TODO Auto-generated constructor stub
	}

	public SNSMsg(BigDecimal rs_no, String rs_writer, String rs_txt, Date rs_date, String rs_color, String rm_photo,
			List<Reply> rs_reply) {
		super();
		this.rs_no = rs_no;
		this.rs_writer = rs_writer;
		this.rs_txt = rs_txt;
		this.rs_date = rs_date;
		this.rs_color = rs_color;
		this.rm_photo = rm_photo;
		this.rs_reply = rs_reply;
	}

	public BigDecimal getRs_no() {
		return rs_no;
	}

	public void setRs_no(BigDecimal rs_no) {
		this.rs_no = rs_no;
	}

	public String getRs_writer() {
		return rs_writer;
	}

	public void setRs_writer(String rs_writer) {
		this.rs_writer = rs_writer;
	}

	public String getRs_txt() {
		return rs_txt;
	}

	public void setRs_txt(String rs_txt) {
		this.rs_txt = rs_txt;
	}

	public Date getRs_date() {
		return rs_date;
	}

	public void setRs_date(Date rs_date) {
		this.rs_date = rs_date;
	}

	public String getRs_color() {
		return rs_color;
	}

	public void setRs_color(String rs_color) {
		this.rs_color = rs_color;
	}

	public String getRm_photo() {
		return rm_photo;
	}

	public void setRm_photo(String rm_photo) {
		this.rm_photo = rm_photo;
	}

	public List<Reply> getRs_reply() {
		return rs_reply;
	}

	public void setRs_reply(List<Reply> rs_reply) {
		this.rs_reply = rs_reply;
	}

	
	
	
}
