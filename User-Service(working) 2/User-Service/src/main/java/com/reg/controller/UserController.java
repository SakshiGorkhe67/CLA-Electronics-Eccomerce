package com.reg.controller;


import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.reg.dto.UserDTO;
import com.reg.entity.User;
import com.reg.exceptions.ResourceNotFoundException;
import com.reg.dto.LoginDTO;
import com.reg.response.LoginResponse;
import com.reg.service.UserService;
import com.reg.util.Role;

import jakarta.validation.Valid;

@RestController
@CrossOrigin(origins="http://localhost:3000/")
@RequestMapping("/auth")
public class UserController {
	 @Autowired
	    private UserService userService;
	 
	 	@Autowired
	 	private AuthenticationManager authenticationManager;
	 
	    @PostMapping(path = "/save")
	    public ResponseEntity<String> saveUser(@Valid @RequestBody UserDTO userDTO)
	    {
	    	 String username = userService.addUser(userDTO);
	         return ResponseEntity.ok("User " + username + " saved successfully");
	    }
	    
	    @GetMapping("/getUserById/{userId}")
	    public ResponseEntity<?> getUserById(@PathVariable int userId) {
	        try {
	            User user = userService.getUserById(userId);
	            return ResponseEntity.ok(user);
	        } catch (ResourceNotFoundException e) {
	            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("User not found");
	        }
	    }

	    
	    @PostMapping("/token")
	    public String getToken(@RequestBody UserDTO userDTO) {
	    	
	    	Authentication authenticate = authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(userDTO.getUsername(),userDTO.getPassword()));
	    	if(authenticate.isAuthenticated()) {
	    	return userService.generateToken(userDTO.getUsername());
	    	}else {
	    		throw new RuntimeException("invalid access");
	    	}
	    }
	    
	    @GetMapping("/validate")
	    public String validateToken(@RequestParam("token") String token) {
	    	userService.validateToken(token);
	    	return "Token is valid";
	    }
	    
	    
	    
	    @GetMapping("/roles")
	    public ResponseEntity<?> getRoles() {
	        Role[] roles = Role.values();
	        return ResponseEntity.ok(roles);
	    }
	    
	    @DeleteMapping(path = "/delete/{userId}")
	    public ResponseEntity<?> deleteUser(@PathVariable int userId) {
	    	
	    	try {
	        userService.deleteUser(userId);
	        return ResponseEntity.ok("User deleted successfully");
	    }
	    	catch (ResourceNotFoundException e) {
	            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("User not found");
	        } catch (Exception e) {
	            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("An error occurred while deleting the user");
	        }
	    }
	    
	    @GetMapping(path = "/getAllUsers")
	    public ResponseEntity<?> getAllUsers() {
	    	
	    	List<User> result = userService.getAllUsers();
	        return ResponseEntity.ok().body(result);
	    }	  
	    
	    @PutMapping(path = "/update/{userId}")
	    public ResponseEntity<?> updateUser(@Valid @PathVariable int userId, @RequestBody UserDTO userDTO,@RequestParam Role role) {
	    	try {
	    		 userService.updateUser(userId, userDTO,role);
	 	        return ResponseEntity.ok("User updated successfully");
	    	}
	    	catch (ResourceNotFoundException e) {
	            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("User not found");
	        }
	    }
	    
	    
	    @PostMapping(path = "/login")
	    public ResponseEntity<?> loginUser(@Valid @RequestBody LoginDTO loginDTO)
	    {
	        LoginResponse loginResponse = userService.loginUser(loginDTO);
	        return ResponseEntity.ok(loginResponse);
	    }
	    
	    @ExceptionHandler(ResourceNotFoundException.class)
	    @ResponseStatus(HttpStatus.NOT_FOUND)
	    public ResponseEntity<String> handleResourceNotFoundException(ResourceNotFoundException ex) {
	        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(ex.getMessage());
	    }
	    
	    @ExceptionHandler(IllegalArgumentException.class)
	    @ResponseStatus(HttpStatus.BAD_REQUEST)
	    public ResponseEntity<String> handleIllegalArgumentException(IllegalArgumentException ex) {
	        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(ex.getMessage());
	    }
	    
	    @ExceptionHandler(RuntimeException.class)
	    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
	    public ResponseEntity<String> handleRuntimeException(RuntimeException ex) {
	        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(ex.getMessage());
	    }
}
	
