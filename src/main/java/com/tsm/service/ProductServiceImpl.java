package com.tsm.service;

import java.util.Collections;
import java.util.List;

import com.tsm.exception.ResourceNotFoundException;
import com.tsm.model.entity.Product;
import com.tsm.model.entity.repository.ProductRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional(rollbackFor = Exception.class)
public class ProductServiceImpl implements ProductService {

	private ProductRepository productRepository;
	
	private static final Logger log = LoggerFactory.getLogger(ProductServiceImpl.class);

    @Autowired
	public ProductServiceImpl(ProductRepository productRepository) {
		
		this.productRepository = productRepository;
	}

	public List<Product> getProductsByCatalog(String string) {

		return Collections.singletonList(new Product());
	}

	public ProductRepository getProductRepository() {
		return productRepository;	
	}

	public void setProductRepository(ProductRepository productRepository) {
		this.productRepository = productRepository;
	}

	public Product save(Product product) {
		log.info("Saving product: {} -->", product);
		return productRepository.save(product);
	}

	public Product findById(Long id) {
		log.info("Finding product by id {} -->", id);
		Product product= productRepository.findOne(id);
		if (product == null) {
			throw new ResourceNotFoundException();
		}
		return product;
	}

	public List<Product> findAll() {
		log.info("Finding all products {} -->");
		return (List<Product>) productRepository.findAll();
	}

	public void delete(Long id) {
		log.info("Deleting product by id {} -->", id);
		productRepository.delete(id);
	}

	@Override
	public List<Product> findByCatalog(String catalog) {
		log.info("Finding by catalog {} -productRepository.findByCatalog(catalog);->", catalog);
		List<Product> products = productRepository.findByCatalog(catalog);
		if (products == null || products.isEmpty()) {
			throw new ResourceNotFoundException();

		}
		return products;
	}

}
