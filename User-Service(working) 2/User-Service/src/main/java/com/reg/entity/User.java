package com.reg.entity;



import java.util.Collection;


import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import com.reg.util.Role;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;



@Entity
@Table(name="user")
public class User {
	    @Id
	    @Column(name="user_id", length = 45)
	    @GeneratedValue(strategy = GenerationType.AUTO)
	    private int userid;
	    @Column(name="user_name", length = 255)
	    private String username;
	    @Column(name="email", length = 255)
	    private String email;
	    @Column(name="password", length = 255)
	    private String password;
	    @Enumerated(EnumType.STRING)
	    @Column(name = "role", length = 50)
	   
	    private Role role;
		public User(int userid, String username, String email, String password, Role role) {
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
			return "User [userid=" + userid + ", username=" + username + ", email=" + email + ", password=" + password
					+ ", role=" + role + "]";
		}
		public User() {
			super();
			// TODO Auto-generated constructor stub
		}
//		@Override
//		public Collection<? extends GrantedAuthority> getAuthorities() {
//			// TODO Auto-generated method stub
//			return null;
//		}
//		@Override
//		public boolean isAccountNonExpired() {
//			// TODO Auto-generated method stub
//			return true;
//		}
//		@Override
//		public boolean isAccountNonLocked() {
//			// TODO Auto-generated method stub
//			return true;
//		}
//		@Override
//		public boolean isCredentialsNonExpired() {
//			// TODO Auto-generated method stub
//			return true;
//		}
//		@Override
//		public boolean isEnabled() {
//			// TODO Auto-generated method stub
//			return true;
//		}
	
		
	   
}
