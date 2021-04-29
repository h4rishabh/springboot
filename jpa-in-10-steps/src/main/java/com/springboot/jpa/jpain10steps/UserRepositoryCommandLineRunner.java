package com.springboot.jpa.jpain10steps;

import java.util.List;
import java.util.Optional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import com.springboot.jpa.jpain10steps.entity.User;
import com.springboot.jpa.jpain10steps.service.UserRepository;

@Component
public class UserRepositoryCommandLineRunner implements CommandLineRunner{
	
	private static final Logger log = LoggerFactory.getLogger(UserRepositoryCommandLineRunner.class);

	@Autowired
	private UserRepository userRepository;
	
	@Override
	public void run(String... args) throws Exception {
		User user = new User("Jill", "Admin");
		userRepository.save(user);
		log.info("New User is created: "+user);
		
		Optional<User> each = userRepository.findById(1L);
		log.info("User 1L: "+each);
		
		List<User> userList = userRepository.findAll();
		log.info("All user's: "+userList);
		
		long size = userRepository.count();
		log.info("There are total "+size+" Users");
	}

}
