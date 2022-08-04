package com.product.manager.controllers;


import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;


@ControllerAdvice
public class GlobalExceptionController {
	
	@ExceptionHandler(Exception.class)
	public String exceptionHandler(Exception exception,Model model) {
		
		model.addAttribute("errorMsg",exception.getMessage());

		return "error-page";
		
	}

}
