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
@RequestMapping("/table")
public class TableController {
	
	@Autowired
	private TableJPARepository tableJPARepository;

	
	
 //-------------------Retrieve All Users--------------------------------------------------------
    
    @RequestMapping(value = "/table/", method = RequestMethod.GET)
    public ResponseEntity<List<Table>> listAllUsers() {
        List<Table> table = tableJPARepository.findAll();
        if(table.isEmpty()){
            return new ResponseEntity<List<Table>>(HttpStatus.NO_CONTENT);//You many decide to return HttpStatus.NOT_FOUND
        }
        return new ResponseEntity<List<Table>>(table, HttpStatus.OK);
    }
 
 
    //-------------------Retrieve Single User--------------------------------------------------------
     
    @RequestMapping(value = "/table/{id}", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Table> getUser(@PathVariable("id") long id) {
        System.out.println("Fetching User with id " + id);
        Table table = tableJPARepository.findOne(id);
        if (table == null) {
            System.out.println("User with id " + id + " not found");
            return new ResponseEntity<Table>(HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<Table>(table, HttpStatus.OK);
    }
	
	@RequestMapping(value = "/table/", method = RequestMethod.POST)
    public ResponseEntity<Void> createtable(@RequestBody Table table,    UriComponentsBuilder ucBuilder) {
        System.out.println("Creating table " + table.getName());
 
 
        
 
        tableJPARepository.save(table);
 
        HttpHeaders headers = new HttpHeaders();
        headers.setLocation(ucBuilder.path("/table/{id}").buildAndExpand(table.getId()).toUri());
        return new ResponseEntity<Void>(headers, HttpStatus.CREATED);
    }
	
	
	@RequestMapping(value = "/table/{id}", method = RequestMethod.PUT)
	public ResponseEntity<Table> updatetable(@PathVariable("id") long id, @RequestBody Table table) {
		System.out.println("Updating table " + id);
		
		Table currenttable = tableJPARepository.findOne(id);
		
		if (currenttable==null) {
			System.out.println("table with id " + id + " not found");
			return new ResponseEntity<Table>(HttpStatus.NOT_FOUND);
		}

		currenttable.setName(table.getName());
		//currenttable.setTeamId(table.getTeamId());
		//currenttable.setSalary(table.getSalary());
		
		tableJPARepository.save(currenttable);
		return new ResponseEntity<Table>(currenttable, HttpStatus.OK);
	}
	
	@RequestMapping(value = "/table/{id}", method = RequestMethod.DELETE)
    public ResponseEntity<Table> deletetable(@PathVariable("id") long id) {
        System.out.println("Fetching & Deleting table with id " + id);
 
        Table table = tableJPARepository.findOne(id);
        if (table == null) {
            System.out.println("Unable to delete. table with id " + id + " not found");
            return new ResponseEntity<Table>(HttpStatus.NOT_FOUND);
        }
 
        tableJPARepository.delete(id);
        return new ResponseEntity<Table>(HttpStatus.NO_CONTENT);
    }
	

}
