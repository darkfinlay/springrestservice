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
@RequestMapping("/session")
public class SessionController {
	
	@Autowired
	private SessionJPARepository sessionJPARepository;
	
	
 //-------------------Retrieve All Users--------------------------------------------------------
    
    @RequestMapping(value = "/session/", method = RequestMethod.GET)
    public ResponseEntity<List<Session>> listAllUsers() {
        List<Session> session = sessionJPARepository.findAll();
        if(session.isEmpty()){
            return new ResponseEntity<List<Session>>(HttpStatus.NO_CONTENT);//You many decide to return HttpStatus.NOT_FOUND
        }
        return new ResponseEntity<List<Session>>(session, HttpStatus.OK);
    }
 
 
    //-------------------Retrieve Single User--------------------------------------------------------
     
    @RequestMapping(value = "/session/{id}", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Session> getUser(@PathVariable("id") long id) {
        System.out.println("Fetching User with id " + id);
        Session session =  sessionJPARepository.findOne(id);
        if (session == null) {
            System.out.println("User with id " + id + " not found");
            return new ResponseEntity<Session>(HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<Session>(session, HttpStatus.OK);
    }
	

	@RequestMapping(value = "/session/", method = RequestMethod.POST)
    public ResponseEntity<Void> createSession(@RequestBody Session session,    UriComponentsBuilder ucBuilder) {
       // System.out.println("Creating session " + session.getName());
 
       /* if (sessionJPARepository.exists()) {
            System.out.println("A session with name " + session.getName() + " already exist");
            return new ResponseEntity<Void>(HttpStatus.CONFLICT);
        }*/
 
		//session.setUserId(session.getEmail());
		
        sessionJPARepository.save(session);
 
        HttpHeaders headers = new HttpHeaders();
        headers.setLocation(ucBuilder.path("/session/{id}").buildAndExpand(session.getId()).toUri());
        return new ResponseEntity<Void>(headers, HttpStatus.CREATED);
    }
	
	
	@RequestMapping(value = "/session/{id}", method = RequestMethod.PUT)
	public ResponseEntity<Session> updateSession(@PathVariable("id") long id, @RequestBody Session session) {
		System.out.println("Updating session " + id);
		
		Session currentsession = sessionJPARepository.findOne(id);
		
		if (currentsession==null) {
			System.out.println("session with id " + id + " not found");
			return new ResponseEntity<Session>(HttpStatus.NOT_FOUND);
		}

		//currentsession.setName(session.getName());
		//currentsession.setTeamId(session.getTeamId());
		//currentsession.setSalary(session.getSalary());
		
		sessionJPARepository.save(currentsession);
		return new ResponseEntity<Session>(currentsession, HttpStatus.OK);
	}
	
	@RequestMapping(value = "/session/{id}", method = RequestMethod.DELETE)
    public ResponseEntity<Session> deletesession(@PathVariable("id") long id) {
        System.out.println("Fetching & Deleting session with id " + id);
 
        Session session = sessionJPARepository.findOne(id);
        if (session == null) {
            System.out.println("Unable to delete. session with id " + id + " not found");
            return new ResponseEntity<Session>(HttpStatus.NOT_FOUND);
        }
 
        sessionJPARepository.delete(id);
        return new ResponseEntity<Session>(HttpStatus.NO_CONTENT);
    }
	

}
