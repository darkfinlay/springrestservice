package com.example.demo.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Component;

import com.example.demo.model.*;

@Component
public interface RoomJPARepository extends JpaRepository<Room, Long> {

	
	Room findByName(String name);
	
}
