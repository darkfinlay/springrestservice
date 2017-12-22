package com.example.demo.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Component;

import com.example.demo.model.*;


@Component
public interface WeaponJPARepository extends JpaRepository<Weapon, Long> {

	Weapon findByName(String name);
	
	

	
}
