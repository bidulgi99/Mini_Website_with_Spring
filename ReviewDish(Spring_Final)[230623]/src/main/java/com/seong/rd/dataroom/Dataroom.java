package com.seong.rd.dataroom;

import java.math.BigDecimal;
import java.util.Date;

public class Dataroom {
	private BigDecimal rd_no;
	private String rd_uploader;
	private String rd_title;
	private String rd_file;
	private String rd_category;
	private Date rd_date;
	
	public Dataroom() {
		// TODO Auto-generated constructor stub
	}

	public Dataroom(BigDecimal rd_no, String rd_uploader, String rd_title, String rd_file, String rd_category,
			Date rd_date) {
		super();
		this.rd_no = rd_no;
		this.rd_uploader = rd_uploader;
		this.rd_title = rd_title;
		this.rd_file = rd_file;
		this.rd_category = rd_category;
		this.rd_date = rd_date;
	}

	public BigDecimal getRd_no() {
		return rd_no;
	}

	public void setRd_no(BigDecimal rd_no) {
		this.rd_no = rd_no;
	}

	public String getRd_uploader() {
		return rd_uploader;
	}

	public void setRd_uploader(String rd_uploader) {
		this.rd_uploader = rd_uploader;
	}

	public String getRd_title() {
		return rd_title;
	}

	public void setRd_title(String rd_title) {
		this.rd_title = rd_title;
	}

	public String getRd_file() {
		return rd_file;
	}

	public void setRd_file(String rd_file) {
		this.rd_file = rd_file;
	}

	public String getRd_category() {
		return rd_category;
	}

	public void setRd_category(String rd_category) {
		this.rd_category = rd_category;
	}

	public Date getRd_date() {
		return rd_date;
	}

	public void setRd_date(Date rd_date) {
		this.rd_date = rd_date;
	}
	
}
