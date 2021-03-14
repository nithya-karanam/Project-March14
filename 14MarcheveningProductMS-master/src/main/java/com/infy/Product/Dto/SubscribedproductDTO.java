package com.infy.Product.Dto;

import com.infy.Product.entity.Subscribedproduct;

public class SubscribedproductDTO {
	int subid;
	int buyerid;
	int prodid;
	int quantity;
	public SubscribedproductDTO()
	{
	super();	
	}
	public SubscribedproductDTO(int subid,int buyerid,int prodid,int quantity) {
		this();
		this.subid=subid;
		this.buyerid=buyerid;
		this.prodid=prodid;
		this.quantity=quantity;
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
	public static SubscribedproductDTO valueOf(Subscribedproduct subscribedproduct)
	{
		SubscribedproductDTO subscribedproductDTO = new SubscribedproductDTO();
		subscribedproductDTO.setBuyerid(subscribedproduct.getBuyerid());
		subscribedproductDTO.setProdid(subscribedproduct.getProdid());
		subscribedproductDTO.setQuantity(subscribedproduct.getQuantity());
		subscribedproductDTO.setSubid(subscribedproduct.getSubid());
		return subscribedproductDTO;
	}
	
	public Subscribedproduct createEntity()
	{
		Subscribedproduct sp= new Subscribedproduct();
		sp.setBuyerid(this.getBuyerid());
		sp.setProdid(this.getProdid());
		sp.setQuantity(this.getQuantity());
		sp.setSubid(this.getSubid());
		return sp;
	}
}
