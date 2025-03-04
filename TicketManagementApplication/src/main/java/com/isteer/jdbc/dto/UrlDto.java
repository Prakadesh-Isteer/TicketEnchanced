package com.isteer.jdbc.dto;

import jakarta.validation.constraints.NotBlank;

public class UrlDto {
	@NotBlank(message = "url cannot be blank")
	private String url;
	@NotBlank(message = "Role cannot be blank")
	private String role;
	public String getUrl() {
		return url;
	}
	public void setUrl(String url) {
		this.url = url;
	}
	public String getRole() {
		return role;
	}
	public void setRole(String role) {
		this.role = role;
	}
	

}
