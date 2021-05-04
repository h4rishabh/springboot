package com.springboot.RestfulWebService.user;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

import java.net.URI;
import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.server.mvc.WebMvcLinkBuilder;
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

	// retrieveAllUsers

	@GetMapping("/users")
	public List<User> retrieveAllUsers() {
		return service.findAllUser();
	}

	// retrieve user detail by id
	// HATEOAS - Hypermedia as the engine of application state


	@GetMapping("user/{id}")
	public EntityModel<User> getUserById(@PathVariable int id) {
		User user = service.findOne(id);
		if (user == null)
			throw new UserNotFoundException("id: " + id);

		EntityModel<User> resource = EntityModel.of(user);

		WebMvcLinkBuilder linkTo = linkTo(methodOn(this.getClass()).retrieveAllUsers());

		resource.add(linkTo.withRel("all-users"));

		return resource;
	}

	// input - user details
	// output - CREATED and return the created URI


	@PostMapping("/user")
	public ResponseEntity<Object> createUsers(@Valid @RequestBody User user) {
		User savedUser = service.save(user);

		// status - CREATED
		// users/{id} service.getId - endpoint of new added user

		URI location = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(savedUser.getId())
				.toUri();

		return ResponseEntity.created(location).build();
	}

	@DeleteMapping("/user/{id}")
	public void deleteUserById(@PathVariable int id) {
		User user = service.deleteById(id);
		// status - 200 - OK, if user is found & deleted
		// status - 404 - NOT FOUND, if user not found
		if (user == null) {
			throw new UserNotFoundException("id: " + id);
		}
	}
}
