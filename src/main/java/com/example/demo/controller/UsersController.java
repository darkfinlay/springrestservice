package com.example.demo.controller;



import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.util.UriComponentsBuilder;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;

import com.example.demo.model.*;
import com.example.demo.repository.*;

import java.util.*;

@RestController
@RequestMapping("/users")
public class UsersController {
	
	@Autowired
	private UserJPARepository userJPARepository;
	

	
	
    //-------------------Retrieve All Users--------------------------------------------------------
    
    @RequestMapping(value = "/users/", method = RequestMethod.GET)
    public ResponseEntity<List<Users>> listAllUsers() {
        List<Users> users = userJPARepository.findAll();
        if(users.isEmpty()){
            return new ResponseEntity<List<Users>>(HttpStatus.NO_CONTENT);//You many decide to return HttpStatus.NOT_FOUND
        }
        return new ResponseEntity<List<Users>>(users, HttpStatus.OK);
    }
 
 
    //-------------------Retrieve Single User--------------------------------------------------------
     
    @RequestMapping(value = "/users/{id}", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Users> getUser(@PathVariable("id") long id) {
        System.out.println("Fetching User with id " + id);
        Users user = userJPARepository.findOne(id);
        if (user == null) {
            System.out.println("User with id " + id + " not found");
            return new ResponseEntity<Users>(HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<Users>(user, HttpStatus.OK);
    }
	

	
	
	@RequestMapping(value = "/users/", method = RequestMethod.POST)
    public ResponseEntity<Void> createUser(@RequestBody Users user,    UriComponentsBuilder ucBuilder) {
        System.out.println("Creating User " + user.getName());
        
        
        
 
        userJPARepository.saveAndFlush(user);
 
        HttpHeaders headers = new HttpHeaders();
        headers.setLocation(ucBuilder.path("/user/{id}").buildAndExpand(user.getUserId()).toUri());
        return new ResponseEntity<Void>(headers, HttpStatus.CREATED);
       // return userJPARepository.findByName(user.getName());
	
	}
	
	
	@RequestMapping(value = "/users/{id}", method = RequestMethod.PUT)
	public ResponseEntity<Users> updateUser(@PathVariable("id") long id, @RequestBody Users user) {
		System.out.println("Updating User " + id);
		
		Users currentUser = userJPARepository.findOne(id);
		
		if (currentUser==null) {
			System.out.println("User with id " + id + " not found");
			return new ResponseEntity<Users>(HttpStatus.NOT_FOUND);
		}

		currentUser.setName(user.getName());		
		currentUser.setSalary(user.getSalary());
		
		userJPARepository.save(currentUser);
		return new ResponseEntity<Users>(currentUser, HttpStatus.OK);
	}
	
	@RequestMapping(value = "/users/{id}", method = RequestMethod.DELETE)
    public ResponseEntity<Users> deleteUser(@PathVariable("id") long id) {
        System.out.println("Fetching & Deleting User with id " + id);
 
        Users user = userJPARepository.findOne(id);
        if (user == null) {
            System.out.println("Unable to delete. User with id " + id + " not found");
            return new ResponseEntity<Users>(HttpStatus.NOT_FOUND);
        }
 
        userJPARepository.delete(id);
        return new ResponseEntity<Users>(HttpStatus.NO_CONTENT);
    }
	

}
