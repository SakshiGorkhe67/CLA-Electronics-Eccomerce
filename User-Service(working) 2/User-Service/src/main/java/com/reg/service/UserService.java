package com.reg.service;

import com.reg.dto.UserDTO;
import com.reg.entity.User;

import java.util.List;

import com.reg.dto.LoginDTO;
import com.reg.response.LoginResponse;
import com.reg.util.Role;

import jakarta.validation.Valid;

public interface UserService {
	 String addUser(UserDTO userDTO);
	    LoginResponse loginUser(LoginDTO loginDTO);
		
		String deleteUser(int userId);
		List<User> getAllUsers();
		//String updateUser(int userId, UserDTO userDTO);
		String updateUser(int userId, UserDTO userDTO, Role role);
		String generateToken(String username);
		void validateToken(String token);
		User getUserById(int userId);
		
}
