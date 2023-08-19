package com.seong.rd.weathercolor;

import java.math.BigDecimal;

public class Weather {
	private BigDecimal rwc_temp;
	private BigDecimal rwc_humidity;
	private String rwc_description;
	private String rwc_color;
	
	public Weather() {
		// TODO Auto-generated constructor stub
	}

	public Weather(BigDecimal rwc_temp, BigDecimal rwc_humidity, String rwc_description, String rwc_color) {
		super();
		this.rwc_temp = rwc_temp;
		this.rwc_humidity = rwc_humidity;
		this.rwc_description = rwc_description;
		this.rwc_color = rwc_color;
	}

	public BigDecimal getRwc_temp() {
		return rwc_temp;
	}

	public void setRwc_temp(BigDecimal rwc_temp) {
		this.rwc_temp = rwc_temp;
	}

	public BigDecimal getRwc_humidity() {
		return rwc_humidity;
	}

	public void setRwc_humidity(BigDecimal rwc_humidity) {
		this.rwc_humidity = rwc_humidity;
	}

	public String getRwc_description() {
		return rwc_description;
	}

	public void setRwc_description(String rwc_description) {
		this.rwc_description = rwc_description;
	}

	public String getRwc_color() {
		return rwc_color;
	}

	public void setRwc_color(String rwc_color) {
		this.rwc_color = rwc_color;
	}
	
}
