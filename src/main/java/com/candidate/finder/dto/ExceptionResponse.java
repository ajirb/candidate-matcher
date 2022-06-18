package com.candidate.finder.dto;

import java.util.Date;

public class ExceptionResponse {
	private String message;
	private Date time = new Date();
	public ExceptionResponse() {
		super();
	}
	public ExceptionResponse(String message) {
		super();
		this.message = message;
	}
	public String getMessage() {
		return message;
	}
	public void setMessage(String message) {
		this.message = message;
	}
	public Date getTime() {
		return time;
	}
	public void setTime(Date time) {
		this.time = time;
	}
}
