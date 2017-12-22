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
@RequestMapping("/room")
public class RoomController {
	
	@Autowired
	private RoomJPARepository roomJPARepository;
	
  //-------------------Retrieve All Users--------------------------------------------------------
    
    @RequestMapping(value = "/room/", method = RequestMethod.GET)
    public ResponseEntity<List<Room>> listAllRooms() {
        List<Room> room = roomJPARepository.findAll();
        if(room.isEmpty()){
            return new ResponseEntity<List<Room>>(HttpStatus.NO_CONTENT);//You many decide to return HttpStatus.NOT_FOUND
        }
        return new ResponseEntity<List<Room>>(room, HttpStatus.OK);
    }
 
 
    //-------------------Retrieve Single User--------------------------------------------------------
     
    @RequestMapping(value = "/room/{id}", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Room> getRoom(@PathVariable("id") long id) {
        System.out.println("Fetching User with id " + id);
        Room room = roomJPARepository.findOne(id);
        if (room == null) {
            System.out.println("User with id " + id + " not found");
            return new ResponseEntity<Room>(HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<Room>(room, HttpStatus.OK);
    }
	
	@RequestMapping(value = "/room/", method = RequestMethod.POST)
    public ResponseEntity<Void> createRoom(@RequestBody Room room,    UriComponentsBuilder ucBuilder) {
        System.out.println("Creating room " + room.getName());
 
       /* if (roomJPARepository.exists()) {
            System.out.println("A room with name " + room.getName() + " already exist");
            return new ResponseEntity<Void>(HttpStatus.CONFLICT);
        }*/
 
        roomJPARepository.save(room);
 
        HttpHeaders headers = new HttpHeaders();
        headers.setLocation(ucBuilder.path("/room/{id}").buildAndExpand(room.getId()).toUri());
        return new ResponseEntity<Void>(headers, HttpStatus.CREATED);
    }
	
	
	@RequestMapping(value = "/room/{id}", method = RequestMethod.PUT)
	public ResponseEntity<Room> updateRoom(@PathVariable("id") long id, @RequestBody Room
			room) {
		System.out.println("Updating room " + id);
		
		Room currentRoom = roomJPARepository.findOne(id);
		
		if (currentRoom==null) {
			System.out.println("room with id " + id + " not found");
			return new ResponseEntity<Room>(HttpStatus.NOT_FOUND);
		}

		currentRoom.setName(room.getName());
		//currentRoom.setTeamId(room.getTeamId());
		//currentRoom.setSalary(room.getSalary());
	
		roomJPARepository.save(currentRoom);
		return new ResponseEntity<Room>(currentRoom, HttpStatus.OK);
	}
	
	@RequestMapping(value = "/room/{id}", method = RequestMethod.DELETE)
    public ResponseEntity<Room> deleteRoom(@PathVariable("id") long id) {
        System.out.println("Fetching & Deleting room with id " + id);
 
        Room room = roomJPARepository.findOne(id);
        if (room == null) {
            System.out.println("Unable to delete. room with id " + id + " not found");
            return new ResponseEntity<Room>(HttpStatus.NOT_FOUND);
        }
 
        roomJPARepository.delete(id);
        return new ResponseEntity<Room>(HttpStatus.NO_CONTENT);
    }
	

}
