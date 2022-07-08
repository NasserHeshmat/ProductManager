package com.product.manager.repositories;

import java.util.Optional;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.product.manager.entities.User;

@Repository
public interface UserRepository extends CrudRepository<User, Integer> {

	public Optional<User> findByEmail(String email);
	
	
	
	//public User findById(Integer id);

}
