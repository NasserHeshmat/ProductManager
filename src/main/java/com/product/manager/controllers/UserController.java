package com.product.manager.controllers;

import java.util.List;

import javax.servlet.http.HttpSession;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.product.manager.entities.User;
import com.product.manager.services.UserService;


@Controller
//@RequestMapping(path="/JSON", produces="application/json")
public class UserController {

	@Autowired
	private UserService service;
	
	@GetMapping("/users")
	@ResponseBody
	public List<User> listAll(Model model) {
		List<User> listUsers = service.listAll();
//		model.addAttribute("listUsers",listUsers);
		return listUsers;
		
	}
		
	@GetMapping("/users/new")
		public String newUser(Model model) {
		User user = new User();
		model.addAttribute("user",user);
			return "user-form";
		
		
		
	}
	
	@GetMapping("/login")
	public String loginPage(Model model) {
		User user = new User();
		model.addAttribute("user",user);
		return "login";
	}
	
	
	@GetMapping("/signout/{id}")
	public String signout(@PathVariable(name = "id")Integer id,Model model,HttpSession session) {
		
		session.removeAttribute("userId");
		User user = new User();
		model.addAttribute("user",user);
		return "login";
	}
	
	@PostMapping("/users/login")
	public String loginValidation(User user,Model model,HttpSession session) {
		User loginUser = service.validateUser(user);
		
		if (loginUser == null)
			return "wrong-password";
		session.setAttribute("userId", loginUser.getId());
		
		model.addAttribute("user", loginUser);
		return "user-homepage";
	}
	
	
	
	@PostMapping("/users/save")
	public String saveUser(User user) {
		System.out.println(user);
		service.save(user);
		return "redirect:/users";
	}
}
