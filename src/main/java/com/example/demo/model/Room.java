package com.example.demo.model;

import java.util.*;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.OneToMany;
@Entity
public class Room {
	
	private long id;
    private String name;
	private Set<Table> tables;
	
	
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

	@ManyToMany(fetch=FetchType.EAGER,cascade=CascadeType.ALL)
    @JoinTable(name="Room_Table",
        joinColumns=
            @JoinColumn(name="roomid", referencedColumnName="id"),
        inverseJoinColumns=
            @JoinColumn(name="id", referencedColumnName="id")
        )
	public Set<Table> getTables() {
		return tables;
	}


	public void setTables(Set<Table> tables) {
		this.tables = tables;
	}
	
	
	

}
