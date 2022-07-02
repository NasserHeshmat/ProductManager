package com.product.manager.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.product.manager.entities.User;
import com.product.manager.repositories.UserRepository;


@Service
public class UserService {

	@Autowired
	private UserRepository repo;
	
	public List<User> listAll(){
		
		return (List<User>) repo.findAll();
		}
	
	public void save(User user) {
	 repo.save(user);	
	}

	public User validateUser(User user) {
		User repoUser;
		repoUser = repo.findByEmail(user.getEmail());
		if(repoUser.getPassword().equals(user.getPassword())) {
			return repoUser;
		}else
			return null;
		
	}
	
	public User getUserById(Integer id) {
		Optional<User> repoUser = repo.findById(id);
		
		return repoUser.get();
	}
}
