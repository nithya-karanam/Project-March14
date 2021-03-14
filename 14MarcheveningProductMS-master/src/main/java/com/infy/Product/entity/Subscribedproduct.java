package com.infy.Product.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="subscribedproduct")
public class Subscribedproduct {
@Id
@Column(nullable = false,length=11)
@GeneratedValue(strategy = GenerationType.IDENTITY)
int subid;
@Column(nullable = false,length=11)
int buyerid;
@Column(nullable = false,length=11)
int prodid;
@Column(nullable = false,length=11)
int quantity;
public Subscribedproduct()
{
super();	
}
public int getSubid() {
	return subid;
}
public void setSubid(int subid) {
	this.subid = subid;
}
public int getBuyerid() {
	return buyerid;
}
public void setBuyerid(int buyerid) {
	this.buyerid = buyerid;
}
public int getProdid() {
	return prodid;
}
public void setProdid(int prodid) {
	this.prodid = prodid;
}
public int getQuantity() {
	return quantity;
}
public void setQuantity(int quantity) {
	this.quantity = quantity;
}
}
