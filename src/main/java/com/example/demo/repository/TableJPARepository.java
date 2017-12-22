package com.example.demo.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Component;

import com.example.demo.model.*;



@Component
public interface TableJPARepository extends JpaRepository<Table, Long> {

	Table findByName(String name);

	
}