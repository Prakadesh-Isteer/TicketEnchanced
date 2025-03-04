package com.isteer.jdbc.model;

public class RequestPermission {
	
	private int requestId;
	private String urlPattern;
	private int roleId;
	
	public int getRequestId() {
		return requestId;
	}
	public void setRequestId(int requestId) {
		this.requestId = requestId;
	}
	public String getUrlPattern() {
		return urlPattern;
	}
	public void setUrlPattern(String urlPattern) {
		this.urlPattern = urlPattern;
	}
	public int getRoleId() {
		return roleId;
	}
	public void setRoleId(int roleId) {
		this.roleId = roleId;
	}
	
	@Override
	public String toString() {
		return "RequestPermission [requestId=" + requestId + ", urlPattern=" + urlPattern + ", roleId=" + roleId + "]";
	}
}
