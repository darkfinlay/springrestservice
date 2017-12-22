package com.example.demo.model;

import java.util.List;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonManagedReference;

@Entity
public class Weapon 
{

	private long id;	
	private String name;
	
   @JsonIgnore
    private Set<Users> users;
	

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

	@JsonIgnore
	@ManyToMany(fetch=FetchType.EAGER,mappedBy="weapons")
	public Set<Users> getUser() {
		return users;
	}
	public void setUser(Set<Users> user) {
		this.users = user;
	}
	
}
