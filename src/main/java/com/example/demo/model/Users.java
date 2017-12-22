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
import javax.persistence.OneToOne;



import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonSetter;

@Entity
public class Users {
	
	private Long userId;
	
	private String name;
	private Team team;
	
	private String userName;
	
	private String password;
	private Integer salary;
	
	
	private Set<Weapon> weapons;
	
	
	
	private Set<Table> tables;
	

	@JsonIgnore
	@ManyToMany(fetch=FetchType.EAGER,mappedBy="users")
	public Set<Table> getTable() {
		return tables;
	}

	public void setTable(Set<Table> table) {
		this.tables = table;
	}

	public Users() {
		
		
	}
	
	public Users(Users user) {
    this.userName =  user.getName();
    this.password = user.getPassword();
    this.name = user.getName();			
	}
	
	@Id	
	@GeneratedValue
	public Long getUserId() {
		return userId;
	}
	public void setUserId(Long id) {
		this.userId = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}

	public Integer getSalary() {
		return salary;
	}
	public void setSalary(Integer salary) {
		this.salary = salary;
	}
	
	public String getUserName() {
		return userName;
	}
	public void setUserName(String user) {
		this.userName = user;
	}
	
	@JsonIgnore
	public String getPassword() {
		return password;
	}
	
	@JsonSetter
	public void setPassword(String password) {
		this.password = password;
	}

	@OneToOne
	public Team getTeam() {
		return team;
	}

	public void setTeam(Team team) {
		this.team = team;
	}

	@ManyToMany(fetch=FetchType.EAGER,cascade=CascadeType.ALL)
    @JoinTable(name="User_Weapon",
        joinColumns=
            @JoinColumn(name="userId", referencedColumnName="userId"),
        inverseJoinColumns=
            @JoinColumn(name="id", referencedColumnName="ID")
        )
	public Set<Weapon> getWeapons() {
		return weapons;
	}

	public void setWeapons(Set<Weapon> weapons) {
		this.weapons = weapons;
	}
	

}
