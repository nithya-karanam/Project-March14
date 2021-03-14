package com.infy.Product.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="product")
public class Product {
	@Id
	@Column(nullable = false,length=11)
	int prodid;
	@Column(nullable = false,length=255)
	String brand;
	@Column(nullable = false,length=255)
	String category;
	@Column(nullable = false,length=255)
	String description;
	@Column(nullable = false,length=255)
	String image;
	@Column(nullable = false,length=255)
	String productname;
	@Column(nullable = false,length=255)
	String subcategory;
	@Column(nullable = false,length=11)
	int sellerid;
	@Column(nullable = false,length=11)
	int stock;
	@Column(nullable = false,length=11)
	float price;
	@Column(nullable = false,length=11)
	Integer rating;
	public Product()
	{
	super();	
	}
	
	public int getProdid() {
		return prodid;
	}

	public void setProdid(int prodid) {
		this.prodid = prodid;
	}

	public String getBrand() {
		return brand;
	}
	public void setBrand(String brand) {
		this.brand = brand;
	}
	public String getCategory() {
		return category;
	}
	public void setCategory(String category) {
		this.category = category;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	public String getImage() {
		return image;
	}
	public void setImage(String image) {
		this.image = image;
	}
	public String getProductname() {
		return productname;
	}
	public void setProductname(String productname) {
		this.productname = productname;
	}
	public String getSubcategory() {
		return subcategory;
	}
	public void setSubcategory(String subcategory) {
		this.subcategory = subcategory;
	}
	public int getSellerid() {
		return sellerid;
	}
	public void setSellerid(int sellerid) {
		this.sellerid = sellerid;
	}
	public int getStock() {
		return stock;
	}
	public void setStock(int stock) {
		this.stock = stock;
	}
	

	public float getPrice() {
		return price;
	}
	public void setPrice(float price) {
		this.price = price;
	}

	public Integer getRating() {
		return rating;
	}

	public void setRating(Integer rating) {
		this.rating = rating;
	}
	

}


