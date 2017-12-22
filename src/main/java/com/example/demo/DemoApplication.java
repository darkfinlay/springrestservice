package com.example.demo;

import javax.activation.CommandObject;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import com.example.demo.model.Users;
import com.example.demo.repository.UserJPARepository;

@SpringBootApplication
public class DemoApplication implements CommandLineRunner {

	@Autowired
	private UserJPARepository usersJPARepository;
	
	public static void main(String[] args) {
		SpringApplication.run(DemoApplication.class, args);
	}
	
	
	@Override
	public void run(String... args) throws Exception {
		Users employee = getUser();
		usersJPARepository.save(employee);
	}
	
	private Users getUser() {
		Users user = new Users();
		user.setName("admin");
		user.setPassword("admin");
		user.setUserName("admin");
	
		return user;
	}
}
