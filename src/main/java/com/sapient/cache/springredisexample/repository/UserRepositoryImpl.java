package com.sapient.cache.springredisexample.repository;

import java.util.Map;

import org.springframework.data.redis.core.HashOperations;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Repository;

import com.sapient.cache.springredisexample.model.Users;

@Repository
public class UserRepositoryImpl implements UserRepository{

	@SuppressWarnings("unused")
	private RedisTemplate< String, Users> redisTemplate;
	@SuppressWarnings("rawtypes")
	private HashOperations hashOperations;
	
	public UserRepositoryImpl(RedisTemplate< String, Users> redisTemplate) {
		this.redisTemplate = redisTemplate;
		hashOperations = redisTemplate.opsForHash();
	}
	
	@SuppressWarnings("unchecked")
	@Override
	public void save(Users user) {
		hashOperations.put("USERS", user.getId(), user);
	}

	@SuppressWarnings("unchecked")
	@Override
	public Map<String, Users> findAll() {
		return hashOperations.entries("USERS");
	}

	@Override
	public void update(Users user) {
		save(user);
		
	}

	@SuppressWarnings("unchecked")
	@Override
	public void delete(String id) {
		hashOperations.delete("USERS", id);
	}

	@SuppressWarnings("unchecked")
	@Override
	public Users findByID(String id) {
		return (Users) hashOperations.get("USERS", id);
	}
	
}
