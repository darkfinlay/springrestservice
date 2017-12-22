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
@RequestMapping("/weapon")
public class WeaponController {
	
	@Autowired
	private WeaponJPARepository weaponJPARepository;
	
 //-------------------Retrieve All Users--------------------------------------------------------
    
    @RequestMapping(value = "/weapon/", method = RequestMethod.GET)
    public ResponseEntity<List<Weapon>> listAllUsers() {
        List<Weapon> weapon = weaponJPARepository.findAll();
        if(weapon.isEmpty()){
            return new ResponseEntity<List<Weapon>>(HttpStatus.NO_CONTENT);//You many decide to return HttpStatus.NOT_FOUND
        }
        return new ResponseEntity<List<Weapon>>(weapon, HttpStatus.OK);
    }
 
 
    //-------------------Retrieve Single User--------------------------------------------------------
     
    @RequestMapping(value = "/weapon/{id}", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Weapon> getUser(@PathVariable("id") long id) {
        System.out.println("Fetching User with id " + id);
        Weapon weapon = weaponJPARepository.findOne(id);
        if (weapon == null) {
            System.out.println("User with id " + id + " not found");
            return new ResponseEntity<Weapon>(HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<Weapon>(weapon, HttpStatus.OK);
    }
	
	@RequestMapping(value = "/weapon/", method = RequestMethod.POST)
    public ResponseEntity<Void> createWeapon(@RequestBody Weapon weapon,    UriComponentsBuilder ucBuilder) {
        System.out.println("Creating Weapon " + weapon.getName());
 
       /* if (weaponJPARepository.exists()) {
            System.out.println("A Weapon with name " + user.getName() + " already exist");
            return new ResponseEntity<Void>(HttpStatus.CONFLICT);
        }*/
 
        weaponJPARepository.save(weapon);
 
        HttpHeaders headers = new HttpHeaders();
        headers.setLocation(ucBuilder.path("/weapon/{id}").buildAndExpand(weapon.getId()).toUri());
        return new ResponseEntity<Void>(headers, HttpStatus.CREATED);
    }
	
	
	@RequestMapping(value = "/weapon/{id}", method = RequestMethod.PUT)
	public ResponseEntity<Weapon> updateWeapon(@PathVariable("id") long id, @RequestBody Weapon weapon) {
		System.out.println("Updating Weapon " + id);
		
		Weapon currentWeapon = weaponJPARepository.findOne(id);
		
		if (currentWeapon==null) {
			System.out.println("weapon with id " + id + " not found");
			return new ResponseEntity<Weapon>(HttpStatus.NOT_FOUND);
		}

		currentWeapon.setName(weapon.getName());
		
		
		
		weaponJPARepository.save(currentWeapon);
		return new ResponseEntity<Weapon>(currentWeapon, HttpStatus.OK);
	}
	
	@RequestMapping(value = "/weapon/{id}", method = RequestMethod.DELETE)
    public ResponseEntity<Weapon> deleteWeapon(@PathVariable("id") long id) {
        System.out.println("Fetching & Deleting Weapon with id " + id);
 
        Weapon weapon = weaponJPARepository.findOne(id);
        if (weapon == null) {
            System.out.println("Unable to delete. Weapon with id " + id + " not found");
            return new ResponseEntity<Weapon>(HttpStatus.NOT_FOUND);
        }
 
        weaponJPARepository.delete(id);
        return new ResponseEntity<Weapon>(HttpStatus.NO_CONTENT);
    }
	

}
