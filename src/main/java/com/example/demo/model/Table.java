package com.example.demo.model;

import java.util.List;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;

import com.fasterxml.jackson.annotation.JsonIgnore;
@Entity
public class Table {

	
	private long id;
	private Set<Users> users;
	private String name;
	
	@JsonIgnore
	private Room room;
	
	@ManyToOne(fetch=FetchType.EAGER)
    @JoinColumn(name="room_id")	
	public Room getRoom() {
		return room;
	}

	public void setRoom(Room room) {
		this.room = room;
	}

	public Table() {
		
		
	}
	
    @Id	
	@GeneratedValue
	public long getId() {
		return id;
	}
	

	public void setId(long table) {
		this.id = table;
	}
	
	public String getName() {
		return name;
	}


	public void setName(String name) {
		this.name = name;
	}
	
	@OneToMany(mappedBy="table")
	public Set<Users> getUsers() {
		return users;
	}

	public void setUsers(Set<Users> users) {
		this.users = users;
	}
	

}
