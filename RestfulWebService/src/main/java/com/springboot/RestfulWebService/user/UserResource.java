package com.springboot.RestfulWebService.user;
import java.net.URI;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

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
	
	
	//retrieve user detail by id
	
	@GetMapping("user/{id}")
	public User getUserById(@PathVariable int id) {
		User user = service.findOne(id);
		if(user == null)
			throw new UserNotFoundException("id: "+id);
		return user;
	}
	
	
	// input - user details
	// output - CREATED and return the created URI
	
	@PostMapping("/user")
	public ResponseEntity<Object> createUsers(@RequestBody User user) {
		User savedUser = service.save(user);

		// status - CREATED
		// users/{id} service.getId - endpoint of new added user

		URI location = ServletUriComponentsBuilder
				.fromCurrentRequest().path("/{id}")
				.buildAndExpand(savedUser.getId()).toUri();

		return ResponseEntity.created(location).build();
	}
	
	@DeleteMapping("/user/{id}")
	public void deleteUserById(@PathVariable int id) {
		User user = service.deleteById(id);
		// status - 200 - OK, if user is found & deleted
		// status - 404 - NOT FOUND, if user not found
		if(user == null) {
			throw new UserNotFoundException("id: "+id);
		}
	}
}
