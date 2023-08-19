package com.seong.rd.reply;

import java.math.BigDecimal;
import java.util.Date;

public class Reply {
	private BigDecimal rr_no;
	private BigDecimal rr_rs_no;
	private String rr_writer;
	private String rr_txt;
	private Date rr_date;
	
	public Reply() {
		// TODO Auto-generated constructor stub
	}

	public Reply(BigDecimal rr_no, BigDecimal rr_rs_no, String rr_writer, String rr_txt, Date rr_date) {
		super();
		this.rr_no = rr_no;
		this.rr_rs_no = rr_rs_no;
		this.rr_writer = rr_writer;
		this.rr_txt = rr_txt;
		this.rr_date = rr_date;
	}

	public BigDecimal getRr_no() {
		return rr_no;
	}

	public void setRr_no(BigDecimal rr_no) {
		this.rr_no = rr_no;
	}

	public BigDecimal getRr_rs_no() {
		return rr_rs_no;
	}

	public void setRr_rs_no(BigDecimal rr_rs_no) {
		this.rr_rs_no = rr_rs_no;
	}

	public String getRr_writer() {
		return rr_writer;
	}

	public void setRr_writer(String rr_writer) {
		this.rr_writer = rr_writer;
	}

	public String getRr_txt() {
		return rr_txt;
	}

	public void setRr_txt(String rr_txt) {
		this.rr_txt = rr_txt;
	}

	public Date getRr_date() {
		return rr_date;
	}

	public void setRr_date(Date rr_date) {
		this.rr_date = rr_date;
	}
	
	
}
