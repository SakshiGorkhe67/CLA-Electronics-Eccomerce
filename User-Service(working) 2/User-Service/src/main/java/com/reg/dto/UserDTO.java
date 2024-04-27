package com.reg.dto;

import com.reg.util.Role;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

public class UserDTO {
	 private int userid;
	 
	    @NotEmpty
	    @Size(min = 4,message = "Username must be min of 4 characters !!")
	    private String username;
	    @Email(message = "Email address is not valid !!")
	    private String email;
	    @NotEmpty
	    @Size(min = 3, max = 10,message = "Password must be min of 3 chars and max of 10 chars !!")
	    private String password;
	    @NotNull
	    private Role role;
		public UserDTO(int userid,
				@NotEmpty @Size(min = 4, message = "Username must be min of 4 characters !!") String username,
				@Email(message = "Email address is not valid !!") String email,
				@NotEmpty @Size(min = 3, max = 10, message = "Password must be min of 3 chars and max of 10 chars !!") String password,
				Role role) {
			super();
			this.userid = userid;
			this.username = username;
			this.email = email;
			this.password = password;
			this.role = role;
		}
		public Role getRole() {
			return role;
		}
		public void setRole(Role role) {
			this.role = role;
		}
		public int getUserid() {
			return userid;
		}
		public void setUserid(int userid) {
			this.userid = userid;
		}
		public String getUsername() {
			return username;
		}
		public void setUsername(String username) {
			this.username = username;
		}
		public String getEmail() {
			return email;
		}
		public void setEmail(String email) {
			this.email = email;
		}
		public String getPassword() {
			return password;
		}
		public void setPassword(String password) {
			this.password = password;
		}
		@Override
		public String toString() {
			return "UserDTO [userid=" + userid + ", username=" + username + ", email=" + email + ", password="
					+ password + ", role=" + role + "]";
		}
		
	    
}
