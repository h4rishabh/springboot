package com.springboot.jpa.jpain10steps;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import com.springboot.jpa.jpain10steps.entity.User;
import com.springboot.jpa.jpain10steps.service.UserDAOService;

@Component
public class UserDAOServiceCommandLineRunner implements CommandLineRunner{
	
	private static final Logger log = LoggerFactory.getLogger(UserDAOServiceCommandLineRunner.class);

	@Autowired
	private UserDAOService userDaoService;
	
	@Override
	public void run(String... args) throws Exception {
		User user = new User("Jack", "Admin");
		long inser = userDaoService.insert(user);
		log.info("New User is created: "+user);
	}

}
