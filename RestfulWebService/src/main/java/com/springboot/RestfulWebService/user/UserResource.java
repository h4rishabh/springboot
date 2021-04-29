package com.springboot.RestfulWebService.user;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class UserResource {

	@Autowired
	private UserDAOService service;
	
	public void setService(UserDAOService service) {
		this.service = service;
	}

	//retrieveAllUsers
	@GetMapping("/users")
	public List<User> retrieveAllUsers(){
		return service.findAllUser();
	}
	
	
	//retrieveUser by Id ()
	@GetMapping("user/{id}")
	public User getUserById(@PathVariable int id) {
		List<User> userList = service.findAllUser();
		
		for(User usr : userList) {
			if(usr.getId() == id)
				return usr;
		}
		return null;
	}
}
