package com.reg.response;

import com.reg.util.Role;

public class LoginResponse {
	String message;
    Boolean status;
    Role role;
    public LoginResponse(String message, Boolean status, Role role) {
		super();
		this.message = message;
		this.status = status;
		this.role = role;
	}
	public Role getRole() {
		return role;
	}
	public void setRole(Role role) {
		this.role = role;
	}
	public String getMessage() {
        return message;
    }
    public void setMessage(String message) {
        this.message = message;
    }
    public Boolean getStatus() {
        return status;
    }
    public void setStatus(Boolean status) {
        this.status = status;
    }
    
}
