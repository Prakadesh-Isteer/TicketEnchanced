package com.isteer.jdbc.model;

import java.util.List;

import org.springframework.stereotype.Component;

import jakarta.validation.constraints.NotBlank;
@Component
public class UserDetailsSerect {

	private Integer userId;
	@NotBlank(message = "USERNAME CANNOT BE EMPTY")
    private String userName;
	@NotBlank(message = "PASSWORD CANNOT BE EMPTY")
    private String password;
    private List<Role> roles;
    
    public UserDetailsSerect(List<Role> roles) {
		super();
		this.roles = roles;
	}
    
	public UserDetailsSerect() {
	}

	public Integer getUserId() {
		return userId;
	}
	public void setUserId(Integer userId) {
		this.userId = userId;
	}
	public String getUserName() {
		return userName;
	}
	public void setUserName(String userName) {
		this.userName = userName;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public List<Role> getRoles() {
		return roles;
	}
	public void setRoles(List<Role> roles) {
		this.roles = roles;
	}
	@Override
	public String toString() {
		return "UserDetailsSerect [userId=" + userId + ", userName=" + userName + ", password=" + password + ", roles="
				+ roles + "]";
	}
	
    
    
	
}
