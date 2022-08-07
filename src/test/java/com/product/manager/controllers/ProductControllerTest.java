package com.product.manager.controllers;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.anyList;
import static org.mockito.ArgumentMatchers.anySet;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;

import javax.servlet.http.HttpSession;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.ui.Model;

import com.product.manager.services.ProductService;
import com.product.manager.services.UserService;

public class ProductControllerTest {
	
	ProductController productController;
	
	
	@Mock
	ProductService productService;
	
	@Mock
	UserService userService;
	
	@Mock
	Model model;
	
	@Mock
	HttpSession session;
	
	@BeforeEach
	public void SetUp() {
		MockitoAnnotations.openMocks(this);
		
		productController = new ProductController(productService,userService);
	}
	
	
	@Test
	public void viewHomePageTest() {
		
		String pageName = productController.viewHomePage(model, session);
		
		assertEquals(pageName, "all-products"); //to test the retuen value
		verify(productService,times(1)).listAll(); //to test the mocks' methods
		verify(model,times(1)).addAttribute(eq("listProducts"), anyList());
		
	}

}
