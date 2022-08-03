package com.product.manager.entities;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Table;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;
import javax.persistence.Id;

@Entity
@Table(name = "product")
public class Product {

	//field names are identical to column names of the table product in the database,
	//to minimize the annotations used
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer Id;
	
	@NotBlank(message = "#################### Not balnk error ####################")
	@Pattern(regexp="^[a-zA-Z0]{3}",message = "#################### pattern error ####################")
	@Column(name = "name")
    private String name;
	
	@Column(name = "brand")
    private String brand;
	
	@Column(name = "madein")
    private String madein;
	
	@Column(name = "price")
    private float price;
    
    

	
	
	
	public Product() {
		
	}

	

	public Product(String name, String brand, String madein, float price) {
		super();
		this.name = name;
		this.brand = brand;
		this.madein = madein;
		this.price = price;
	}


	public Integer getId() {
		return Id;
	}

	public void setId(Integer id) {
		Id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getBrand() {
		return brand;
	}
	public void setBrand(String brand) {
		this.brand = brand;
	}
	public String getMadein() {
		return madein;
	}
	public void setMadein(String madein) {
		this.madein = madein;
	}
	public float getPrice() {
		return price;
	}
	public void setPrice(float price) {
		this.price = price;
	}
    
    
}
