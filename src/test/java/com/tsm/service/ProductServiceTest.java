package com.tsm.service;

import static org.mockito.Mockito.when;

import java.util.Collections;
import java.util.List;

import com.tsm.service.ProductService;
import com.tsm.service.ProductServiceImpl;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import com.tsm.model.entity.Product;
import com.tsm.model.entity.repository.ProductRepository;

public class ProductServiceTest {
	
	private ProductService productService;

	@Mock
	private ProductRepository productRepository;
	
	@Mock
	private Product product;
	
	@Before
	public void setup() {
        MockitoAnnotations.initMocks(this);
        productService = new ProductServiceImpl(productRepository);
        when(productRepository.findByCatalog("Store")).thenReturn(Collections.singletonList(product));
        when(productRepository.findOne(1l)).thenReturn(product);
        when(product.getId()).thenReturn(1l);
	}
	
	@Test
	public void shouldReturnProduct() {
		Product temp = productService.findById(1l);
		Assert.assertNotNull(temp);
		Assert.assertTrue(temp.getId().equals(1l));
	}
	
	@Test
	public void shouldReturnProductByCatalog() {
		List<Product> products = productService.findByCatalog("Store");
		Assert.assertNotNull(products);
		Assert.assertTrue(products.get(0).getId().equals(1l));
	}

}
