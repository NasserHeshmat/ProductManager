package com.product.manager.controllers;

import java.util.List;

import javax.servlet.http.HttpSession;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

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
		
		model.addAttribute("user",new User());
			return "user-form";

	}
	
	@GetMapping("/login")
	public String loginPage(Model model) {

		model.addAttribute("user",new User());
		return "login";
	}
	
	
	@GetMapping("/signout/")
	public String signout(Model model,HttpSession session) {
		
		service.userSignout( model, session);
		
		return "login";
	}
	
	@PostMapping("/users/login")
	public String loginValidation(User user,Model model,HttpSession session) {
		
		
		if (service.validateUser(user, model, session)) {
			return "user-homepage";
		}else {
			return "wrong-password";
				}
	}
	
	
	
	@PostMapping("/users/save")
	public String saveUser(User user) {
//		System.out.println(user);
		service.save(user);
		return "redirect:/users";
	}
}
