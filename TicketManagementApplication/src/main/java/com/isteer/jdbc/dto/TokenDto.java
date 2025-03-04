package com.isteer.jdbc.dto;

public class TokenDto {
	private int statusCode;
	private String token;
	private String userName;
	
	public TokenDto(int statusCode,String userName, String token ) {
		super();
		this.userName = userName;
		this.statusCode = statusCode;
		this.token = token;
		
	}
	
	public String getUserName() {
		return userName;
	}
	public void setUserName(String userName) {
		this.userName = userName;
	}
	public String getToken() {
		return token;
	}
	public void setToken(String token) {
		this.token = token;
	}
	public int getStatusCode() {
		return statusCode;
	}
	public void setStatusCode(int statusCode) {
		this.statusCode = statusCode;
	}

}
