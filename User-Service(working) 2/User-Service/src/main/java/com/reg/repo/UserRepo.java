package com.reg.repo;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.stereotype.Repository;

import com.reg.dto.UserDTO;
import com.reg.entity.User;

@Repository
public interface UserRepo extends JpaRepository<User,Integer>{
	 Optional<User> findOneByEmailAndPassword(String email, String password);
	    User findByEmail(String email);
//	   deleteById(Long userId);
		Optional<UserDTO> findByUsername(String username);
}
