package com.example.demo.model;

import java.util.*;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToMany;
@Entity
public class Room {
	
	private long id;
    private String name;
	private List<Table> tables;
	
	
	public Room() {
		
		
	}
	
	public Room(Room room) {
		
		this.id = room.getId();
		this.name = room.getName();
		
	}

    public Room(long id, String name) {
		super();
		this.id = id;
		this.name = name;
	}
	
    
    @Id	
	@GeneratedValue
	public long getId() {
		return id;
	}
	public void setId(long id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}

	@OneToMany(mappedBy = "room", cascade = CascadeType.ALL, 
	        orphanRemoval = true)
	public List<Table> getTables() {
		return tables;
	}


	public void setTables(List<Table> tables) {
		this.tables = tables;
	}
	
	
	

}
