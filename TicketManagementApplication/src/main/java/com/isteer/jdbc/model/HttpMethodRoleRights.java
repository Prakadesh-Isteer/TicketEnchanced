package com.isteer.jdbc.model;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public class HttpMethodRoleRights {
    @NotBlank(message = "KINDLY GIVE httpMethod")
	private String httpMethod;
    @NotNull(message = "KINDLY SPESIFY THE ROLE")
	private Integer roleId;
	
	
	public Integer getRoleId() {
		return roleId;
	}
	public void setRoleId(Integer roleId) {
		this.roleId = roleId;
	}
	public String getHttpMethod() {
		return httpMethod;
	}
	public void setHttpMethod(String httpMethod) {
		this.httpMethod = httpMethod;
	}

	
	@Override
	public String toString() {
		return "HttpMethodRoleRights [httpMethod=" + httpMethod + ", roleId=" + roleId + "]";
	}
}
