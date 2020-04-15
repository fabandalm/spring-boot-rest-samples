package com.falmeida.tech.springrestsample.model;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.springframework.stereotype.Component;

@Component
public class UserDAOImpl implements UserDAO{

	private static List<User> users = new ArrayList<User>();
	
	static {
		users.add(new User(1,"Josh",new Date()));
		users.add(new User(2,"Joe",new Date()));
		users.add(new User(3,"John",new Date()));
	}

	@Override
	public List<User> findAll() {
		
		return users;
	}

	@Override
	public User saveUser(User user) {
		users.add(user);
		return user;
	}

	@Override
	public User findUserById(int id) {
		for (User user : users) {
			if(user.getId() == id) {
				return user;
			}
		}
		return null;
	}
	
	
}
