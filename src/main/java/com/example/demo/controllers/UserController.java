package com.example.demo.controllers;

import java.util.List;


import com.example.demo.entities.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.example.demo.repositories.UserRepository;

@RestController
public class UserController {

	private final UserRepository userRepository;

	
	@Autowired
	public UserController(UserRepository userRepository) {
		this.userRepository = userRepository;
	}

	@CrossOrigin
	@GetMapping("/users")
	public List<User> users(){
		 return userRepository.findAll();
	}

	@CrossOrigin
	@GetMapping("/users/{id}")
	public ResponseEntity<User> getUserById(@PathVariable("id") final int user_id)
	{
		User user = userRepository.findById(user_id).get();
		return ResponseEntity.ok().body(user);
	}

	@CrossOrigin
	@GetMapping("/users/cluster/{id}")
	public List<User> getUsersByClusterId(@PathVariable("id") final int cluster_id){
		return userRepository.getUsersByClusterId(cluster_id);
	}

	@CrossOrigin
	@PostMapping("/users")
	public void add(@RequestBody User user)
	{
		userRepository.save(user);
	}

	@CrossOrigin
	@PutMapping("/users/{id}")
	public void edit(@RequestBody User user, @PathVariable("id") final Integer id)
	{
		User existedUser = userRepository.findById(id).get();
		existedUser.setId(user.getId());
		existedUser.setFirstName(user.getFirstName());
		existedUser.setLastName(user.getLastName());
				userRepository.save(existedUser);
	}

	@CrossOrigin
	@DeleteMapping("/users/{id}")
	public void delete (@PathVariable("id") final Integer id)
	{
		userRepository.deleteById(id);
	}

	@CrossOrigin
	@GetMapping("/users/delete")
	public void setNull (){
		List<User> userList = userRepository.findAll();
		for (User user : userList){
			user.setTotalBill(0);
			userRepository.save(user);
		}
	}
}
