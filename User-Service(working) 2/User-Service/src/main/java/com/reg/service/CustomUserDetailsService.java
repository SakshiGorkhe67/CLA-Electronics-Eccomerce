package com.reg.service;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;

import com.reg.config.CustomUserDetails;
import com.reg.dto.UserDTO;
import com.reg.entity.User;
import com.reg.repo.UserRepo;

@Component
public class CustomUserDetailsService implements UserDetailsService {
	
	@Autowired
	private UserRepo repository;

	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		// TODO Auto-generated method stub
		Optional<UserDTO> credential =repository.findByUsername(username);
		
		return credential.map(CustomUserDetails::new).orElseThrow(()->new UsernameNotFoundException("user not found with name"+username));

	}
	
	

}
