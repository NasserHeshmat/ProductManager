package com.product.manager.services;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.List;


import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import com.product.manager.entities.Product;
import com.product.manager.repositories.ProductRepository;

public class ProdutServiceTest {

	
	ProductService productService;
	
	
	@Mock
	ProductRepository repo;
	
	@BeforeEach
	public void SetUp() throws Exception{
		
		MockitoAnnotations.openMocks(this);
		
		productService = new ProductService(repo);
	}
	
	@Test
	public void ListAllTest() {
		
		Product product =new Product();
		List<Product> list=new ArrayList<Product>();
		list.add(product);
		
		when(productService.listAll()).thenReturn(list);
		
		List<Product> ProductList = productService.listAll();
		
		assertEquals(ProductList.size(), 1);
		
	}
	
}
