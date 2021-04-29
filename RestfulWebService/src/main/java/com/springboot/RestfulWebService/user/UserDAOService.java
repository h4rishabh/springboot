package com.springboot.RestfulWebService.user;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.springframework.stereotype.Component;

@Component
public class UserDAOService {
	
	private static List<User> userList = new ArrayList<>();
	private static int userCount = 3;
	
	static {
		userList.add(new User(1, "Jack", new Date()));
		userList.add(new User(2, "Jill", new Date()));
		userList.add(new User(3, "John", new Date()));
	}
	
	public List<User> findAllUser(){
		return userList;
	}
	
	public User save(User user) {
		
		if(user.getId() == null) {
			user.setId(++userCount);
		}
		userList.add(user);
		return user;
	}
	
	public User findOne(int id) {
		for(User user: userList) {
			if(user.getId() == id)
				return user;
		}
		return null;
	}

}
