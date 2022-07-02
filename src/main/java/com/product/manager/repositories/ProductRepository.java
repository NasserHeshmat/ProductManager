package com.product.manager.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.product.manager.entities.Product;

@Repository
public interface ProductRepository extends JpaRepository<Product, Integer>{

}
