package com.infy.validator;

import com.infy.Product.Dto.ProductDTO;

//import com.infy.Product.Dto.ProductDTO;

public class Validator {
	public static boolean validates(ProductDTO productDTO) throws Exception{
		if(!validateName(productDTO.getProductname())) {
			throw new Exception("Validator.NAME_IS_NOT_VALID");
		}
		if(!validateDescription(productDTO.getDescription())) {
			throw new Exception("Validator.DESCRIPTION_IS_NOT_VALID");
		}
		if(!validatePrice(productDTO.getPrice())) {
			throw new Exception("Validator.PRICE_IS_NOT_VALID");
		}
		if(!validateStock(productDTO.getStock())) {
			throw new Exception("Validator.STOCK_VALUE_NOT_VALID");
		}
		if(!validateImage(productDTO.getImage())) {
			throw new Exception("Validator.IMAGE_FORMAT_IS_NOT_VALID");
		}
		else
			return true;
	}
	
	public static Boolean validateName(String name) {
		String regex = "[A-Za-z]+(\\s[A-Za-z]+){,100}*";
		if(name.matches(regex)) {
			return true;
		}
		return false;
	}
	public static Boolean validateDescription(String description) {
		String regex = "[A-Za-z]{0,500}";
		if(description.matches(regex)) {
			return true;
		}
		return false;
	}
	public static Boolean validatePrice(float price) {
		if(price>= 200) {
			return true;
		}
		return false;
	}
	
	public static Boolean validateStock(Integer stock) {
		
		if(stock>=10) {
			return true;
		}
		return false;
	}
public static Boolean validateImage(String image) {
		
		if(image.endsWith("png") || image.endsWith("jpeg")) {
			return true;
		}
		return false;
	}
	
}

