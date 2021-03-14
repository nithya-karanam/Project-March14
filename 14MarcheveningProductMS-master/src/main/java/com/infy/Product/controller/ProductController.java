package com.infy.Product.controller;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.env.Environment;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.server.ResponseStatusException;

import com.infy.Product.Dto.ProductDTO;
import com.infy.Product.Dto.StockDTO;
import com.infy.Product.Dto.SubscribedproductDTO;
import com.infy.Product.service.ProductMSException;
import com.infy.Product.service.ProductService;



@RestController
@CrossOrigin
public class ProductController {
	Logger logger = LoggerFactory.getLogger(this.getClass());
	
	@Autowired
	ProductService productService;
	
	@Autowired
	Environment environment;
	
	@Value("${user.uri}")
	String userUri;
	
	// Fetches all products
	@GetMapping(value = "/api/products",  produces = MediaType.APPLICATION_JSON_VALUE)
	public List<ProductDTO> getAllProducts() throws ProductMSException{
		logger.info("Fetching all products");
		System.out.println("In controller");
		return productService.getAllProducts();
	}
	//Fetch products according to id
	@GetMapping(value = "/api/products/{prodid}",  produces = MediaType.APPLICATION_JSON_VALUE)
	public ProductDTO getProductsDetails(@PathVariable Integer prodid) throws ProductMSException{
		logger.info("fetching details of Product with product id {}", prodid);
		return productService.getProductById(prodid);
	}
	
	// Fetches products according to category
	@GetMapping(value = "/api/{category}/products",  produces = MediaType.APPLICATION_JSON_VALUE)
	public List<ProductDTO> getProductsByCategory(@PathVariable String category) throws ProductMSException{
		logger.info("Product details for category {}", category);
		return productService.getProductByCategory(category);
	}
	
	// Fetches products according to product name
	@GetMapping(value = "/api/product/{productname}",  produces = MediaType.APPLICATION_JSON_VALUE)
	public List<ProductDTO> getProductsByName(@PathVariable String productname) throws ProductMSException{
		logger.info("Product details for product name {}", productname);
		return productService.getProductByName(productname);
	}
	//add product
		@PostMapping(value = "/api/product/add",  produces = MediaType.APPLICATION_JSON_VALUE)
		public void addProduct(@RequestBody ProductDTO productDTO) throws ProductMSException {
			productService.addProduct(productDTO);
		}

    
	

	
	
	@PostMapping(value= "/api/subscriptions/add/" , consumes = MediaType.APPLICATION_JSON_VALUE)
	//public List<ProductDTO> addProduct(@PathVariable String category) throws ProductMSException{
	public ResponseEntity<String>  addProduct(@RequestBody SubscribedproductDTO subscribedproductDTO)   throws ProductMSException{
	      ResponseEntity<String> response = null;
	   	  try{
	   		boolean flag = new RestTemplate().getForObject(userUri+subscribedproductDTO.getBuyerid(), Boolean.class);
	   		if(flag) {
	   			productService.addSubscriptions(subscribedproductDTO);
		   		String successMessage = environment.getProperty("API.SAVING_SUCCESSFUL");
		   		response = new ResponseEntity<String>(successMessage,HttpStatus.CREATED);
	   		}
	   		else {
	   			throw new ProductMSException("SUBSCRIPTION_FAILED");
	   		}
	   	  }
	   	  catch(Exception e) {
	   		 throw new ResponseStatusException(HttpStatus.OK,environment.getProperty(e.getMessage()),e);
	       }
	   	  return response;
	   	 }
	
    
	@GetMapping(value = "/api/subscriptions/{subid}",  produces = MediaType.APPLICATION_JSON_VALUE)
	public SubscribedproductDTO getSubscriptionsDetails(@PathVariable Integer subid) throws ProductMSException{
		logger.info("fetching details of Product with product id {}", subid);
		return productService.getDetailsBysubId(subid);
	}
	@PostMapping(value = "/api/productsdisable/{sellerId}",produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<String> disableProductsUsingSellerId(@PathVariable Integer sellerId) {
		ResponseEntity<String> response = null;
		boolean flag = productService.disableProductsUsingSellerId(sellerId);
		 if(flag) {
			String successMessage = environment.getProperty("DELETED_PRODUCT");
			response = new ResponseEntity<String>(successMessage,HttpStatus.OK);
		 }else {
			 String failureMessage = environment.getProperty("ALREADY_DELETED_PRODUCT");
		     response = new ResponseEntity<String>(failureMessage,HttpStatus.BAD_REQUEST);
		 }
		 return response;
		
	}
	
	//updating stock
		@PutMapping(value = "/api/stock/{prodid}",  produces = MediaType.APPLICATION_JSON_VALUE)
		public ResponseEntity<String> Stock(@RequestBody StockDTO stockDTO) throws ProductMSException {
			ResponseEntity<String> response = null;
			try {
				boolean flag=productService.Stock(stockDTO);
				if(flag) {
			String msg=environment.getProperty("STOCK_UPDATED");
			 response=new ResponseEntity<String>(msg,HttpStatus.OK);
			}
				else
				{
					logger.info("Update Stock {}",stockDTO.getStock());
					productService.Stock(stockDTO);
					String failureMessage=environment.getProperty("LESS_QUANTITY");
					response=new  ResponseEntity<String>(failureMessage,HttpStatus.BAD_REQUEST);
				}
			}
			catch(Exception e) {
				throw new ResponseStatusException (HttpStatus.OK,environment.getProperty(e.getMessage()),e);
				
			}
			return response;
		}
		// CHECK STOCK BEFORE ORDER
		@PostMapping(value = "/api/checkstock/{prodid}",  produces = MediaType.APPLICATION_JSON_VALUE)
		public ResponseEntity<String> checkStockBeforeOrder(@PathVariable int prodid,@RequestBody Integer Quantity) throws ProductMSException {
			try{
				productService.checkStockBeforeOrder(prodid,Quantity);
				String msg=environment.getProperty("checking Stock before Order");
				ResponseEntity<String> response=new ResponseEntity<String>(msg,HttpStatus.OK);
				return response;
				}
				catch(Exception e) {
					ResponseStatusException exception=new ResponseStatusException(HttpStatus.BAD_REQUEST,environment.getProperty(e.getMessage()),e);
					throw exception;
				}
			}
	
	
}