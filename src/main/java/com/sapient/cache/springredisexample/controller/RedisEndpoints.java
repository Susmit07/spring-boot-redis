package com.sapient.cache.springredisexample.controller;

import java.util.Map;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.sapient.cache.springredisexample.exceptions.UsersNotFoundException;
import com.sapient.cache.springredisexample.model.Users;
import com.sapient.cache.springredisexample.repository.UserRepository;

@RestController
@RequestMapping("/rest/user")
public class RedisEndpoints {
	
	private UserRepository userRepository;
	
	public RedisEndpoints(UserRepository userRepository) {
		this.userRepository = userRepository;
	}

	@GetMapping("/add/{id}/{name}")
    public Users add(@PathVariable("id") final String id,
                    @PathVariable("name") final String name) {
        userRepository.save(new Users(id, name, 20000L));
        return userRepository.findByID(id);
    }

    @GetMapping("/update/{id}/{name}")
    public Users update(@PathVariable("id") final String id,
                       @PathVariable("name") final String name) {
    	// Commenting it for custom exception checking
       //userRepository.update(new Users(id, name, 1000L));
        if(userRepository.findByID(id) == null) {
        	throw new UsersNotFoundException("User Not Found");
        }
        return userRepository.findByID(id);
    }

    @GetMapping("/delete/{id}")
    public Map<String, Users> delete(@PathVariable("id") final String id) {
        userRepository.delete(id);
        return all();
    }

    @GetMapping("/all")
    public Map<String, Users> all() {
        return userRepository.findAll();
    }
	
}
