package com.example.demo.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Component;

import com.example.demo.model.*;

@Component
public interface SessionJPARepository extends JpaRepository<Session, Long> {

	
	Session findByUserName(String s);
	
}
