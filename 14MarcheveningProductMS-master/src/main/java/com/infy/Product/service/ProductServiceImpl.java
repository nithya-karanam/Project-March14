package com.infy.Product.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PathVariable;

import com.infy.Product.Dto.ProductDTO;
import com.infy.Product.Dto.StockDTO;
import com.infy.Product.Dto.SubscribedproductDTO;
import com.infy.Product.entity.Product;
import com.infy.Product.entity.Subscribedproduct;
import com.infy.Product.repository.ProductRepository;
import com.infy.Product.repository.SubscribedproductRepository;



@Service
public class ProductServiceImpl implements ProductService{
	Logger logger = LoggerFactory.getLogger(this.getClass());
	
	@Autowired
	ProductRepository productRepository;
	@Autowired
	SubscribedproductRepository subrepo;
	//Get the entire product list
	public List<ProductDTO> getAllProducts() throws ProductMSException{
		System.out.println("In service");
		List<Product>products = productRepository.findAll();
		List<ProductDTO> productDTOs = new ArrayList<>();
		
		for(Product p:products) {
			ProductDTO productDTO = ProductDTO.valueOf(p);
			productDTOs.add(productDTO);
		}
		logger.info("Product details : {}", productDTOs);
		return productDTOs;
	}

	@Override
	public List<ProductDTO> getProductByCategory(@PathVariable String category) throws ProductMSException {
		// TODO Auto-generated method stub
		List<Product> product = productRepository.findByCategory(category);
		List<ProductDTO> productDTOs = new ArrayList<>();
		
		for(Product p:product) {
			productDTOs.add(ProductDTO.valueOf(p));
		}
		logger.info("Product details according to category : {}", productDTOs);
		
		return productDTOs;
	}

	@Override
	public List<ProductDTO> getProductByName(String productname) throws ProductMSException {
		// TODO Auto-generated method stub
		
		List<Product> products = productRepository.findByProductname(productname);
		List<ProductDTO> productDTOs = new ArrayList<>();
		
		for(Product p:products) {
			productDTOs.add(ProductDTO.valueOf(p));
		}
		logger.info("Product details according to product name : {}", productDTOs);
		return productDTOs;
	}
	public ProductDTO getProductById(Integer prodid) {
		// TODO Auto-generated method stub
		logger.info("Product details according to product id : {}", prodid);
		ProductDTO productDTO = null;
		Optional<Product> product = productRepository.findById(prodid);
		
		if(product.isPresent()) {
			Product p = product.get();
			productDTO = ProductDTO.valueOf(p);
		}
		return productDTO;
		
		
		
		
	}
public void addProduct(ProductDTO productDTO) {
		
		Product product=productDTO.createEntity();
		System.out.println("adding product to product list");
		productRepository.save(product);
	}
	
	@Override
	public void  addSubscriptions(SubscribedproductDTO subscribedproductDTO) {
		// TODO Auto-generated method stub
		Subscribedproduct product = subscribedproductDTO.createEntity();
        subrepo.save(product);
	
	}
	
	
	@Override
	public  SubscribedproductDTO getDetailsBysubId(Integer subid) throws ProductMSException {
		// TODO Auto-generated method stub
		logger.info("=====Subscription Details {}======", subid);
		SubscribedproductDTO subscribedproductDTO = null;
		Optional<Subscribedproduct> product = subrepo.findById(subid);
		if(product.isPresent()) {
			Subscribedproduct p = product.get();
			subscribedproductDTO = SubscribedproductDTO.valueOf(p);
		}
		return subscribedproductDTO;
	}
	
	@Override
	public boolean disableProductsUsingSellerId(Integer sellerId) {
		List<Product> optProduct = productRepository.findBySellerid(sellerId);
		if(!optProduct.isEmpty()) {
			for(Product p : optProduct) {
				productRepository.delete(p);
			}
			return true;
		}
		return false;
	}
	
	// check stock before order
	public boolean checkStockBeforeOrder(Integer prodid,Integer quantity) {
		logger.info("Updating Stock after order");
		Optional<Product> product1 = productRepository.findById(prodid);
		Product p=product1.get();
		Boolean value;
		if(p.getStock()>=quantity) {
			p.setStock(p.getStock()-quantity);
			value=true;
		}
		else {
			value=false;
		}
		return value;
	}
	public boolean Stock(StockDTO stockDTO) {
		logger.info("Updating Stock ");
		Optional<Product> product1 = productRepository.findById(stockDTO.getProdid());
		if(stockDTO.getStock()>=10)
		{ 
			Product p=product1.get();
			p.setStock(stockDTO.getStock());
			productRepository.save(p);
			return true;
	}
		else
		{
			return false;
		}
}
}
