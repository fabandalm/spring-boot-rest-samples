package com.falmeida.tech.springrestsample.model;

import java.util.List;

public interface UserDAO {

	public List<User> findAll();
	
	public User saveUser(User user);
	
	public User findUserById(int id);
	
}
