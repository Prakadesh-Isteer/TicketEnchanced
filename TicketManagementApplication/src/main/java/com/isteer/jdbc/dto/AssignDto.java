package com.isteer.jdbc.dto;

import jakarta.validation.constraints.NotBlank;

public class AssignDto {
	@NotBlank(message = "USER NAME CANNOT BE EMPTY")
	private String userName;
	@NotBlank(message = "ROLE ASSINGN CANNOT BE EMPTY")
	private String role;
	public String getUserName() {
		return userName;
	}
	public void setUserName(String userName) {
		this.userName = userName;
	}
	public String getRole() {
		return role;
	}
	public void setRole(String role) {
		this.role = role;
	}
	

}
