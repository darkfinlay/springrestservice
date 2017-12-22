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
@RequestMapping("/team")
public class TeamController {
	
	@Autowired
	private TeamJPARepository teamJPARepository;
 //-------------------Retrieve All Users--------------------------------------------------------
    
    @RequestMapping(value = "/team/", method = RequestMethod.GET)
    public ResponseEntity<List<Team>> listAllUsers() {
        List<Team> team = teamJPARepository.findAll();
        if(team.isEmpty()){
            return new ResponseEntity<List<Team>>(HttpStatus.NO_CONTENT);//You many decide to return HttpStatus.NOT_FOUND
        }
        return new ResponseEntity<List<Team>>(team, HttpStatus.OK);
    }
 
 
    //-------------------Retrieve Single User--------------------------------------------------------
     
    @RequestMapping(value = "/team/{id}", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Team> getUser(@PathVariable("id") long id) {
        System.out.println("Fetching User with id " + id);
        Team team = teamJPARepository.findOne(id);
        if (team == null) {
            System.out.println("User with id " + id + " not found");
            return new ResponseEntity<Team>(HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<Team>(team, HttpStatus.OK);
    }
	
	@RequestMapping(value = "/team/", method = RequestMethod.POST)
    public ResponseEntity<Void> createTeam(@RequestBody Team team,    UriComponentsBuilder ucBuilder) {
        System.out.println("Creating Team " + team.getName());
 
       /* if (TeamJPARepository.exists()) {
            System.out.println("A Team with name " + Team.getName() + " already exist");
            return new ResponseEntity<Void>(HttpStatus.CONFLICT);
        }*/
 
        teamJPARepository.save(team);
 
        HttpHeaders headers = new HttpHeaders();
        headers.setLocation(ucBuilder.path("/Team/{id}").buildAndExpand(team.getId()).toUri());
        return new ResponseEntity<Void>(headers, HttpStatus.CREATED);
    }
	
	
	@RequestMapping(value = "/team/{id}", method = RequestMethod.PUT)
	public ResponseEntity<Team> updateTeam(@PathVariable("id") long id, @RequestBody Team team) {
		System.out.println("Updating Team " + id);
		
		Team currentTeam = teamJPARepository.findOne(id);
		
		if (currentTeam==null) {
			System.out.println("Team with id " + id + " not found");
			return new ResponseEntity<Team>(HttpStatus.NOT_FOUND);
		}

		currentTeam.setName(team.getName());
		//currentTeam.setTeamId(Team.getTeamId());
		//currentTeam.setSalary(Team.getSalary());
		
		teamJPARepository.save(currentTeam);
		return new ResponseEntity<Team>(currentTeam, HttpStatus.OK);
	}
	
	@RequestMapping(value = "/team/{id}", method = RequestMethod.DELETE)
    public ResponseEntity<Team> deleteTeam(@PathVariable("id") long id) {
        System.out.println("Fetching & Deleting Team with id " + id);
 
        Team Team = teamJPARepository.findOne(id);
        if (Team == null) {
            System.out.println("Unable to delete. Team with id " + id + " not found");
            return new ResponseEntity<Team>(HttpStatus.NOT_FOUND);
        }
 
        teamJPARepository.delete(id);
        return new ResponseEntity<Team>(HttpStatus.NO_CONTENT);
    }
	

}
