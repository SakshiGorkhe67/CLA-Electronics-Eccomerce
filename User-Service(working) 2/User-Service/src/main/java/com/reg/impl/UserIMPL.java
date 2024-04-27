package com.reg.impl;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.reg.dto.UserDTO;
import com.reg.dto.LoginDTO;
//import com.reg.dto.LoginDTO;
import com.reg.entity.User;
import com.reg.exceptions.ResourceNotFoundException;
import com.reg.repo.UserRepo;
import com.reg.response.LoginResponse;
import com.reg.service.JwtService;
import com.reg.service.UserService;
import com.reg.util.Role;

@Service
public class UserIMPL implements UserService {

	@Autowired
	public UserRepo userRepo;
    @Autowired
    private PasswordEncoder passwordEncoder;
    
    @Autowired
    private JwtService jwtService;
    
    @Override
    public String addUser(UserDTO userDTO) {
        // Check if a user with the same email already exists
        User existingUser = userRepo.findByEmail(userDTO.getEmail());
        if (existingUser != null) {
            // User with the provided email already exists
            throw new IllegalStateException("User with this email already exists");
        }
        
        // Create a new user and save it
        User user = new User(
                userDTO.getUserid(),
                userDTO.getUsername(),
                userDTO.getEmail(),
                passwordEncoder.encode(userDTO.getPassword()),
                userDTO.getRole()
        );
        userRepo.save(user);
        return user.getUsername();
    }
    
    
    @Override
    public User getUserById(int userId) {
        Optional<User> optionalUser = userRepo.findById(userId);
        if (optionalUser.isPresent()) {
            return optionalUser.get();
        } else {
            throw new ResourceNotFoundException("User not found with ID: " + userId);
        }
    }

    
    public String generateToken(String username) {
    	return jwtService.generateToken(username);
    	
    }
    
    public void validateToken(String token) {
    	jwtService.validateToken(token);
    }
    
    public LoginResponse  loginUser(LoginDTO loginDTO) {
        String msg = "";
        User user1 = userRepo.findByEmail(loginDTO.getEmail());
        if (user1 != null) {
            String password = loginDTO.getPassword();
            String encodedPassword = user1.getPassword();
            Boolean isPwdRight = passwordEncoder.matches(password, encodedPassword);
            if (isPwdRight) {
                Optional<User> user = userRepo.findOneByEmailAndPassword(loginDTO.getEmail(), encodedPassword);
                if (user.isPresent()) {
                	  Role role = user.get().getRole();
                    return new LoginResponse("Login Success", true , role);
                } else {
                    return new LoginResponse("Login Failed",false, null);
                }
            } else {
                return new LoginResponse("password Not Match", false,null);
            }
        }else {
            return new LoginResponse("Email not exits", false,null);
        }
    }
    
    @Override
    public String deleteUser(int userId) {
    	 Optional<User> optionalUser = userRepo.findById(userId);
         if (optionalUser.isPresent()) {
             userRepo.deleteById(userId);
             return "User deleted successfully";
         } else {
             throw new ResourceNotFoundException("User not found with ID: " + userId);
         }
     
    }
    
    @Override
    public List<User> getAllUsers() {
        return userRepo.findAll();
    }
    
    public String updateUser(int userId, UserDTO userDTO,Role role) {
        Optional<User> optionalUser = userRepo.findById(userId);
        if (optionalUser.isPresent()) {
            User user = optionalUser.get();
            user.setUsername(userDTO.getUsername());
            user.setEmail(userDTO.getEmail());
            user.setPassword(passwordEncoder.encode(userDTO.getPassword()));
            user.setRole(role);
            userRepo.save(user);
            return "User updated successfully";
        } else {
        	 throw new ResourceNotFoundException("User not found with ID: " + userId);
        }
    }

    public Role[] getAvailableRoles() {
        return Role.getAllRoles();
    }
	
    
    

}
