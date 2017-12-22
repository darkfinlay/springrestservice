package com.example.demo.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Component;

import com.example.demo.model.Users;

@Component
public interface UserJPARepository extends JpaRepository<Users, Long> {

	Users findByName(String name);

	Optional<Users> findByUserName(String userName);
	
	
	Users findByPassword(String password);
}
