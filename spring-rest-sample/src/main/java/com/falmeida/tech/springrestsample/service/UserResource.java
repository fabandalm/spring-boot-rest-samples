package com.falmeida.tech.springrestsample.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import com.falmeida.tech.springrestsample.model.User;
import com.falmeida.tech.springrestsample.model.UserDAOImpl;

@RestController
public class UserResource {

	@Autowired
	private UserDAOImpl userDAO;
	
	@GetMapping(path="/users")
	public List<User> retrieveAllUsers(){
		return userDAO.findAll();
	}
	
	@GetMapping("/users/{id}")
	public User retrieveUser(@PathVariable int id) {
		return userDAO.findUserById(id);
	}
	
}
