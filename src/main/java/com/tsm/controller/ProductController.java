package com.tsm.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.tsm.model.entity.Product;
import com.tsm.service.ProductService;
import com.google.common.base.Preconditions;

@RestController
@RequestMapping(value = "/product")
public class ProductController {

	
	private ProductService productService;

	@Autowired
	public ProductController(ProductService productService) {
		this.productService = productService;
	}

	@RequestMapping(value = "/{id}", method = RequestMethod.GET, produces = { MediaType.APPLICATION_JSON_VALUE })
	public ResponseEntity<Product> findById(@PathVariable Long id) {
		Product product = productService.findById(id);
		return new ResponseEntity<Product>(product, HttpStatus.OK);
	}

	@RequestMapping(value = "/cat/{catalog}", method = RequestMethod.GET, produces = { MediaType.APPLICATION_JSON_VALUE })
	public ResponseEntity<List<Product>> findByCatalog(@PathVariable String catalog) {
		List<Product> products = productService.findByCatalog(catalog);
		return new ResponseEntity<List<Product>>(products, HttpStatus.OK);
	}

	@RequestMapping(method = RequestMethod.POST, consumes = { MediaType.APPLICATION_JSON_VALUE }, produces = { MediaType.APPLICATION_JSON_VALUE })
	public ResponseEntity<Product> save(
			@RequestBody Product productRequest) {
		Product product = productService.save(productRequest);
		return new ResponseEntity<Product>(product, HttpStatus.OK);
	}

	@RequestMapping(method = RequestMethod.PUT, consumes = { MediaType.APPLICATION_JSON_VALUE }, produces = { MediaType.APPLICATION_JSON_VALUE })
	public ResponseEntity<Product> update(
			@RequestBody Product productRequest) {
		Preconditions.checkArgument(
				productRequest != null && productRequest.getId() != null,
				"Problems for update the product.");
		Product product =  productService.save(productRequest);
		
	    return new ResponseEntity<Product>(product, HttpStatus.OK);
	}

}
