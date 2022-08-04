package com.product.manager;


import static org.assertj.core.api.Assertions.assertThatExceptionOfType;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertNull;

import javax.validation.ConstraintViolationException;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;


import com.product.manager.entities.Product;
import com.product.manager.repositories.ProductRepository;

@ActiveProfiles("test")
@SpringBootTest
public class ProductRepositoryTest {
	
	@Autowired
	  private ProductRepository productRepository;

	  @Test
	  public void saveProduct() {
	    Product product = new Product("keka","cake","Egypt",3);
	    Product savedProduct = productRepository.save(product);
//	    assertNotNull(savedProduct);
	    assertEquals("cake", savedProduct.getBrand());
	    }
	  
}
