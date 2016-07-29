package com.tsm.service;

import java.util.List;


import com.tsm.model.entity.Product;

public interface ProductService {
	
	Product save(Product product);

    Product findById(Long id);

    List<Product> findAll();

    void delete(Long id);
    
    List<Product> findByCatalog(String catalog);

}
