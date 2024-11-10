package com.myapp.demo.Repository;

import org.springframework.data.mongodb.repository.MongoRepository;

import com.myapp.demo.Model.user;


public interface UserRepository extends MongoRepository<user, String> {
    // Additional query methods (if needed)
	 user findByUsername(String username);
}
