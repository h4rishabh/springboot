package com.springboot.RestfulWebService.helloworld;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

//controller - mapped using @RestController
@RestController
public class HelloWorldController {
	
	/*
	 * We beed to define two things Request type : GET, PUT, POST etc... and URI to
	 * the request
	 */

	@RequestMapping(method = RequestMethod.GET, path = "/hello-world") // here we are using @RequestMapping
	// we can also use @GetMapping
	public String helloWorld() {
		return "Hello World"; // returning Hard coded
	}

	@GetMapping(path = "/hello") // here we are using @GetMapping
	public String hello() {
		return "Hello";
	}

	@GetMapping(path = "/hello-world-bean") // here we are using @GetMapping
	public HelloWorldBean helloWorldBean() {
		return new HelloWorldBean("Hello World Bean");
	}

	@GetMapping(path = "/hello-world/path-variable/{name}") // here we are using @GetMapping
	public HelloWorldBean helloWorldPathVariable(@PathVariable String name) {
		return new HelloWorldBean(String.format("Hello World, %s", name));
	}
}
