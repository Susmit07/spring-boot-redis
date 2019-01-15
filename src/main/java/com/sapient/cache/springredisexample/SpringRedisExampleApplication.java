package com.sapient.cache.springredisexample;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.data.redis.connection.jedis.JedisConnectionFactory;
import org.springframework.data.redis.core.RedisTemplate;

import com.sapient.cache.springredisexample.model.Users;

@SpringBootApplication
public class SpringRedisExampleApplication {

	public static void main(String[] args) {
		SpringApplication.run(SpringRedisExampleApplication.class, args);
	}
	
	// Default Redis Config.
	@Bean
	JedisConnectionFactory jedisConnectionFactory() {
		return new JedisConnectionFactory();
		/*JedisConnectionFactory jedisConFactory
	      = new JedisConnectionFactory();
	    jedisConFactory.setHostName(hostName);
	    jedisConFactory.setPort(6379);
	    return jedisConFactory;*/
	}
	
	// Helper class that simplifies Redis data access code.
	// Performs automatic serialization/deserialization between the given objects and the underlying binary data in the Redis store. 
	@Bean
	RedisTemplate<String, Users> redisTemplate() {
		RedisTemplate<String, Users> redisTemplate = new RedisTemplate<>();
		redisTemplate.setConnectionFactory(jedisConnectionFactory());
		return redisTemplate;
 	}
}
