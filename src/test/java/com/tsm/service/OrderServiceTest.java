package com.tsm.service;

import static org.mockito.Mockito.when;

import java.util.Collections;
import java.util.List;

import com.tsm.service.OrderService;
import com.tsm.service.OrderServiceImpl;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import com.tsm.model.entity.Ordercom;
import com.tsm.model.entity.repository.CommerceItemRepository;
import com.tsm.model.entity.repository.OrderEcomRepository;

public class OrderServiceTest {

	private OrderService orderService;

	@Mock
	private OrderEcomRepository orderEcomRepository;

	private CommerceItemRepository commerceItem;

	@Mock
	private Ordercom order;

	@Before
	public void setup() {
		MockitoAnnotations.initMocks(this);
		orderService = new OrderServiceImpl(orderEcomRepository, commerceItem);
		when(orderEcomRepository.findOne(1l)).thenReturn(order);
		when(orderEcomRepository.save(order)).thenReturn(order);
		when(orderEcomRepository.findAll()).thenReturn(Collections.singletonList(order));
		when(order.getId()).thenReturn(1l);
	}
	
	
	@Test
	public void shouldReturnOrder() {
		Ordercom temp = orderService.findById(1l);
		Assert.assertNotNull(temp);
		Assert.assertTrue(temp.getId().equals(1l));
	}
	
	@Test
	public void shouldReturnAllOrders() {
		List<Ordercom> orders = orderService.findAll();
		Assert.assertNotNull(orders);
		Assert.assertTrue(orders.get(0).getId().equals(1l));
	}
	
	public void shouldSave() {
		Ordercom  orders = orderService.save(order);
		Assert.assertNotNull(orders);
		Assert.assertTrue(orders.getId().equals(1l));
	}
	
	
}
