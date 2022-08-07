package com.product.manager.entities;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.Before;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;


class ProductTest {

	Product product;
	
	@BeforeEach
	public void setUp() {
		product = new Product();
	}
	
	@Test
	public void getName() {
		
		product.setName("Manga");
		assertEquals("Manga", product.getName());
	}
	
	@Test
	public void getBrand() {
		
		product.setBrand("Coca");
		assertEquals("Coca", product.getBrand());
	}
	
	@Test
	public void getPrice() {
		
		product.setPrice(5);
		assertEquals(5, product.getPrice());
	}
	
	

}
