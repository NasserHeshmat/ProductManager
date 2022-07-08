package com.product.manager.services;

import java.util.List;
import java.util.Optional;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.ui.Model;

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

	public boolean validateUser(User user, Model model, HttpSession session) {
		
		
		
		Optional<User> repoUser = repo.findByEmail(user.getEmail());
		
		if(repoUser.isPresent() && repoUser.get().getPassword().equals(user.getPassword())) {
			session.setAttribute("userId", repoUser.get().getId());			
			model.addAttribute("user", repoUser.get());
			return true;
		}else
			return false;
		
	}
	
	public User getUserById(Integer id) {
		Optional<User> repoUser = repo.findById(id);
		
		return repoUser.get();
	}

	public void deleteCartProduct(int id, HttpSession session,Model model, ProductService service) {
		User currentUser = this.getUserById((Integer) session.getAttribute("userId"));
		currentUser.removeProduct(service.get(id));
		
		model.addAttribute("user",currentUser);
		
	}

	public void addProductToCart(int id, HttpSession session, Model model,ProductService prodService) {
		User currentUser = this.getUserById((Integer) session.getAttribute("userId"));
		currentUser.addProduct(prodService.get(id));
		
		model.addAttribute("user",currentUser);
		
	}

	public void userSignout(Model model, HttpSession session) {
		session.removeAttribute("userId");
		
		model.addAttribute("user",new User());
		
	}
}
