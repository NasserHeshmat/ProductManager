package com.product.manager.controllers;

import java.util.List;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import com.product.manager.entities.Product;
import com.product.manager.entities.User;
import com.product.manager.services.ProductService;
import com.product.manager.services.UserService;

@Controller
public class ProductController {
	
	@Autowired
    private ProductService service;
	
	@Autowired
	private UserService userService;
	
	@GetMapping("/new")
	public String showNewProductPage(Model model) {
	    Product product = new Product();
	    model.addAttribute("product", product);
	     
	    return "new_product";
	}
	
	@GetMapping("/")
	public String viewHomePage(Model model,HttpSession session) {
		User currentUser = userService.getUserById((Integer) session.getAttribute("userId"));
		System.out.println(currentUser.getProducts());
	    List<Product> listProducts = service.listAll();
	    model.addAttribute("listProducts", listProducts);
	     
	    return "all-products";
	}
	
	@RequestMapping(value = "/save", method = RequestMethod.POST)
	public String saveProduct(@ModelAttribute("product") Product product) {
	    service.save(product);
	     
	    return "redirect:/";
	}
	
	@RequestMapping("/edit/{id}")
	public ModelAndView showEditProductPage(@PathVariable(name = "id") int id) {
	    ModelAndView mav = new ModelAndView("edit_product");
	    Product product = service.get(id);
	    mav.addObject("product", product);
	     
	    return mav;
	}
	
	
	@RequestMapping("/addToCart/{id}")
	public String addToCart(@PathVariable(name = "id") int id, HttpSession session,Model model) {
		User currentUser = userService.getUserById((Integer) session.getAttribute("userId"));
		currentUser.addProduct(service.get(id));
		
		model.addAttribute("user",currentUser);
		return "user-homepage";
	}
	
	
	
	@RequestMapping("/deleteCartProduct/{id}")
	public String deleteFromCart(@PathVariable(name = "id") int id, HttpSession session,Model model) {
		User currentUser = userService.getUserById((Integer) session.getAttribute("userId"));
		currentUser.removeProduct(service.get(id));
		
		model.addAttribute("user",currentUser);
		return "user-homepage";
	}
	
	@RequestMapping("/delete/{id}")
	public String showDeletePage(@PathVariable(name = "id") int id) {
		
		service.delete(id);
		
		return "redirect:/";
	}

}
