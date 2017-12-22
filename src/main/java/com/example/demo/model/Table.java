package com.example.demo.model;

import java.util.List;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;

import com.fasterxml.jackson.annotation.JsonIgnore;
@Entity
public class Table {

	
	private long id;
	private List<Users> users;
	private String name;
	
	
	private Set<Room> room;
	
	@JsonIgnore
	@ManyToMany(fetch=FetchType.EAGER,mappedBy="tables")
	public Set<Room> getRoom() {
		return room;
	}

	public void setRoom(Set<Room> room) {
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
	
	@ManyToMany(fetch=FetchType.EAGER,cascade=CascadeType.ALL)
    @JoinTable(name="Table_Users",
        joinColumns=
            @JoinColumn(name="roomid", referencedColumnName="id"),
        inverseJoinColumns=
            @JoinColumn(name="userid", referencedColumnName="userId")
        )
	public List<Users> getUsers() {
		return users;
	}

	public void setUsers(List<Users> users) {
		this.users = users;
	}
	

}
