package com.infy.Product.service;



import java.util.List;

import com.infy.Product.Dto.ProductDTO;
import com.infy.Product.Dto.StockDTO;
import com.infy.Product.Dto.SubscribedproductDTO;







public interface ProductService {
	public List<ProductDTO> getAllProducts() throws ProductMSException;
	public List<ProductDTO> getProductByCategory(String category)throws ProductMSException;
	public List<ProductDTO> getProductByName(String productname) throws ProductMSException;
	public void addProduct(ProductDTO productDTO)throws ProductMSException;
	public ProductDTO getProductById(Integer prodid) throws ProductMSException;
	public boolean Stock(StockDTO stockDTO) throws ProductMSException;
	public void addSubscriptions(SubscribedproductDTO subscribedproductDTO) throws ProductMSException;
	public SubscribedproductDTO getDetailsBysubId(Integer subid) throws ProductMSException;
	public boolean disableProductsUsingSellerId(Integer sellerId) ;
	public boolean checkStockBeforeOrder(Integer prodid,Integer quantity) throws ProductMSException;
}

	

