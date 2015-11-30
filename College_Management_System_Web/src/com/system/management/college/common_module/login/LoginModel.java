package com.system.management.college.common_module.login;

public class LoginModel {
	
	private String userId;
	private String pwd;
	private int privilege_level;
	private boolean status;
	private String message;
	
	
	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	public boolean isStatus() {
		return status;
	}

	public void setStatus(boolean status) {
		this.status = status;
	}

	public String getUserId() {
		return userId;
	}
	
	public void setUserId(String userId) {
		this.userId = userId;
	}
	
	public String getPwd() {
		return pwd;
	}
	
	public void setPwd(String pwd) {
		this.pwd = pwd;
	}
	
	public int getPrivilege_level() {
		return privilege_level;
	}
	
	public void setPrivilege_level(int privilege_level) {
		this.privilege_level = privilege_level;
	}
	
}
