package com.falmeida.tech.springrestsample.service;

import java.net.URI;
import java.util.Iterator;
import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.server.mvc.ControllerLinkBuilder;
import org.springframework.hateoas.server.mvc.WebMvcLinkBuilder;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.falmeida.tech.springrestsample.model.User;
import com.falmeida.tech.springrestsample.model.UserDAOImpl;
import com.falmeida.tech.springrestsample.model.UserRepository;

@SuppressWarnings("deprecation")
@RestController
public class UserResource {

	@Autowired
	private UserDAOImpl userDAO;
	
	@Autowired
	private UserRepository userRepository;
	
	@GetMapping(path="/users")
	public List<User> retrieveAllUsers(){
		return userRepository.findAll();
	}
	
	@GetMapping("/users/{id}")
	public EntityModel<User> retrieveUser(@PathVariable int id) {
		User user = userDAO.findUserById(id);
		if(user == null) {
			throw new UserNotFoundException("id: " + id);
		}
		EntityModel<User> model = new EntityModel<>(user);
		 
		WebMvcLinkBuilder linkTo = WebMvcLinkBuilder.linkTo(ControllerLinkBuilder.methodOn(this.getClass()).retrieveAllUsers());
	 
		model.add(linkTo.withRel("all-users"));
	 
		return model;
	}
	
	@PostMapping("/users")
	public ResponseEntity<User> createUser(@Valid @RequestBody User user) {
		User createdUser = userDAO.saveUser(user);
		URI location = ServletUriComponentsBuilder
					.fromCurrentRequest()
					.path("/{id}")
					.buildAndExpand(createdUser.getId())
					.toUri();
		return ResponseEntity.created(location).build();
	}
	
	@DeleteMapping("/users/{id}")
	public void deleteUser(@PathVariable int id) {
		User user = deleteById(id);
		if(user == null) {
			throw new UserNotFoundException("id: " + id);
		}
	}
	
	public User deleteById(int id) {
		List<User> users = userDAO.findAll();
		Iterator<User> iterator = users.iterator();
		while(iterator.hasNext()) {
			User user = iterator.next();
			if(user.getId() == id) {
				iterator.remove();
				return user;
			}
		}
		return null;
	}
	
}
