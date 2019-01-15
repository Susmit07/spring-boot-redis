package com.sapient.cache.springredisexample.repository;

import java.util.Map;


import com.sapient.cache.springredisexample.model.Users;

public interface UserRepository {

	void save(Users user);

	Map<String, Users> findAll();

	void update(Users user);

	void delete(String id);
	
	Users findByID(String id);

}
