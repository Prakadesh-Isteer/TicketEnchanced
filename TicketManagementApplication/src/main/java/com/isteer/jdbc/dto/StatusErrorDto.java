package com.isteer.jdbc.dto;

public class StatusErrorDto {
	private int errorCode;
	public StatusErrorDto(int errorCode, String errorMessage) {
		super();
		this.errorCode = errorCode;
		this.errorMessage = errorMessage;
	}
	public StatusErrorDto() {
		// TODO Auto-generated constructor stub
	}
	private String errorMessage;
	public int getErrorCode() {
		return errorCode;
	}
	public void setErrorCode(int errorCode) {
		this.errorCode = errorCode;
	}
	public String getErrorMessage() {
		return errorMessage;
	}
	public void setErrorMessage(String errorMessage) {
		this.errorMessage = errorMessage;
	}

}
