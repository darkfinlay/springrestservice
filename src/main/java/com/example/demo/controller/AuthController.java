package com.example.demo.controller;

import javax.servlet.http.HttpSession;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.util.UriComponentsBuilder;

import com.example.demo.model.Session;
import com.example.demo.model.Users;

import com.example.demo.repository.SessionJPARepository;
import com.example.demo.repository.UserJPARepository;

@RestController
@RequestMapping("/log")
public class AuthController {
	
	
	private static final Logger log =  LoggerFactory.getLogger(AuthController.class);
	
	@Autowired
	private SessionJPARepository sessionRepository;
	
	@Autowired
	private UserJPARepository userRepository;
	
	
	@RequestMapping(value = "/login/", method = RequestMethod.POST)
	public ResponseEntity<Users> logIn(@RequestBody Users user,    UriComponentsBuilder ucBuilder) {
		
		System.out.println("Logging in...");
		
		Session session = sessionRepository.findByUserName(user.getUserName());
	
		Session nueva;
		if (session == null) {
			
			System.out.println("Not user logged");
			
			log.info("Not user logged");
			nueva = new Session();
			
			Users usuarioBuscado = userRepository.findByPassword(user.getPassword());
		
			if(usuarioBuscado != null) {	
			nueva.setUserName(user.getUserName());	
			sessionRepository.save(nueva);
			return new ResponseEntity<>(usuarioBuscado, HttpStatus.OK);
			}else {			
				return new ResponseEntity<>(null, HttpStatus.UNAUTHORIZED);
			}
		}
		
		return new ResponseEntity<>(user, HttpStatus.UNAUTHORIZED);
	}

	@RequestMapping(value = "/logout/", method = RequestMethod.POST)
	public ResponseEntity<Boolean> logOut(@RequestBody Users user,    UriComponentsBuilder ucBuilder) {
		
		Session session = sessionRepository.findByUserName(user.getUserName());
		
		if (session != null) {			
			sessionRepository.delete(session);
			return new ResponseEntity<>(true, HttpStatus.OK);		
		}	
		return new ResponseEntity<>(false, HttpStatus.UNAUTHORIZED);
	}

	
}
