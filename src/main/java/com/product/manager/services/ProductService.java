package com.product.manager.services;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.servlet.ModelAndView;

import com.product.manager.entities.Product;
import com.product.manager.repositories.ProductRepository;

@Service
@Transactional
public class ProductService {
 
    
    private ProductRepository repo;
     
    
    public ProductService(@Autowired ProductRepository repo) {
		
		this.repo = repo;
	}

	public List<Product> listAll() {
        return repo.findAll();
    }
     
    public void save(Product product) {
        repo.save(product);
    }
     
    public Product get(Integer id) {
        return repo.findById(id).get();
    }
     
    public void delete(Integer id) {
        repo.deleteById(id);
    }

	public ModelAndView getEditMavOfProductById(int id) {
		ModelAndView mav = new ModelAndView("edit_product");
	    
	    Product product = this.get(id);
	    mav.addObject("product", product);
		return mav;
	}
}
