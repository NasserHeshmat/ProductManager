package com.product.manager.controllers;

import java.util.List;
import javax.servlet.http.HttpSession;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import com.product.manager.entities.Product;
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
	    List<Product> listProducts = service.listAll();
	    model.addAttribute("listProducts", listProducts);
	     
	    return "all-products";
	}
	
	@RequestMapping(value = "/save", method = RequestMethod.POST)
	public String saveProduct(@Valid @ModelAttribute("product") Product product, BindingResult error) {
		if(error.hasErrors()) {
			System.out.println("there are error "+ error.getAllErrors());
		}
	    service.save(product);     
	    return "redirect:/";
	}
	
	
	@RequestMapping("/edit/{id}")
	public ModelAndView showEditProductPage(@PathVariable(name = "id") int id) {
	    return service.getEditMavOfProductById(id);
	}
	
	
	@RequestMapping("/addToCart/{id}")
	public String addToCart(@PathVariable(name = "id") int id, HttpSession session,Model model) {
		
		userService.addProductToCart( id,  session, model,service);
		return "user-homepage";
	}
	
	
	
	@RequestMapping("/deleteCartProduct/{id}")
	public String deleteFromCart(@PathVariable(name = "id") int id, HttpSession session,Model model) {	
		userService.deleteCartProduct(id,session, model,service);		
		return "user-homepage";
	}
	
	@RequestMapping("/delete/{id}")
	public String showDeletePage(@PathVariable(name = "id") int id) {
		
		service.delete(id);		
		return "redirect:/";
	}

}
