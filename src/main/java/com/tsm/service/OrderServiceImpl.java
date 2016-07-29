package com.tsm.service;

import java.util.Collections;
import java.util.List;

import com.tsm.exception.ResourceNotFoundException;
import com.tsm.model.entity.repository.CommerceItemRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.tsm.model.entity.Ordercom;
import com.tsm.model.entity.repository.OrderEcomRepository;

@Service
@Transactional(rollbackFor = Exception.class)
public class OrderServiceImpl implements OrderService {

	private OrderEcomRepository orderRepository;
	
	private CommerceItemRepository commerceItemRepository;
	
	private static final Logger log = LoggerFactory.getLogger(OrderServiceImpl.class);

    @Autowired
	public OrderServiceImpl(OrderEcomRepository orderRepository, CommerceItemRepository commerceItemRepository) {
		
		this.orderRepository = orderRepository;
		this.commerceItemRepository = commerceItemRepository;
	}

	public List<Ordercom> getOrdersByCatalog(String string) {

		return Collections.singletonList(new Ordercom());
	}

	public Ordercom save(Ordercom order) {
		log.info("Saving order: {} -->", order);
		return orderRepository.save(order);
	}

	public Ordercom findById(Long id) {
		log.info("Finding order by id {} -->", id);
		Ordercom order= orderRepository.findOne(id);
		if (order == null) {
			throw new ResourceNotFoundException();
		}
		return order;
	}

	public List<Ordercom> findAll() {
		log.info("Finding all order {} -->");
		return (List<Ordercom>) orderRepository.findAll();
	}

	public void delete(Long id) {
		log.info("Deleting order by id {} -->", id);
		orderRepository.delete(id);
	}

	@Override
	public List<Ordercom> findBySky(Long skuId) {
		log.info("Deleting order by sku {} -->", skuId );
		List<Ordercom> orders = commerceItemRepository.findBySku(skuId);
		if (orders == null) {
			throw new ResourceNotFoundException();
		}
		return orders;
	}

	public OrderEcomRepository getOrderRepository() {
		return orderRepository;
	}

	public void setOrderRepository(OrderEcomRepository orderRepository) {
		this.orderRepository = orderRepository;
	}

	public CommerceItemRepository getCommerceItemRepository() {
		return commerceItemRepository;
	}

	public void setCommerceItemRepository(CommerceItemRepository commerceItemRepository) {
		this.commerceItemRepository = commerceItemRepository;
	}

}
