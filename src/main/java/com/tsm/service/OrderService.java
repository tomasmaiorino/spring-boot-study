package com.tsm.service;

import java.util.List;

import com.tsm.model.entity.Ordercom;

public interface OrderService {
	
	Ordercom save(Ordercom order);

    Ordercom findById(Long id);

    List<Ordercom> findAll();

    void delete(Long id);
    
    List<Ordercom> findBySky(Long skuId);

}
