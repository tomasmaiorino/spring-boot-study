package com.tsm.controller;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.tsm.model.entity.Ordercom;
import com.tsm.service.OrderService;
import com.google.common.base.Preconditions;

@RestController
@RequestMapping(value = "/order")
public class OrderController {

	
	private OrderService orderService;

	@Autowired
	public OrderController(OrderService orderService) {
		this.orderService = orderService;
	}

	@RequestMapping(value = "/{id}", method = RequestMethod.GET, produces = { MediaType.APPLICATION_JSON_VALUE })
	public ResponseEntity<Ordercom> findById(@PathVariable Long id) {
		Ordercom order = orderService.findById(id);
		return new ResponseEntity<Ordercom>(order, HttpStatus.OK);
	}
	
	@RequestMapping(value = "/sku/{id}", method = RequestMethod.GET, produces = { MediaType.APPLICATION_JSON_VALUE })
	public ResponseEntity<List<Ordercom>> findBySku(@PathVariable Long id) {
		List<Ordercom> listProduct = orderService.findBySky(id);
		return new ResponseEntity<List<Ordercom>>(listProduct, HttpStatus.OK);
	}

	@RequestMapping(value="/all", method = RequestMethod.GET, produces = { MediaType.APPLICATION_JSON_VALUE })
	public ResponseEntity<List<Ordercom>> findAll() {
		List<Ordercom> listProduct = orderService.findAll();
		return new ResponseEntity<List<Ordercom>>(listProduct, HttpStatus.OK);
	}

	@RequestMapping(method = RequestMethod.POST, consumes = { MediaType.APPLICATION_JSON_VALUE }, produces = { MediaType.APPLICATION_JSON_VALUE })
	public ResponseEntity<Ordercom> save(
			@RequestBody @Valid Ordercom orderRequest) {
		Ordercom order = orderService.save(orderRequest);
		return new ResponseEntity<Ordercom>(order, HttpStatus.OK);
	}

	@RequestMapping(method = RequestMethod.PUT, consumes = { MediaType.APPLICATION_JSON_VALUE }, produces = { MediaType.APPLICATION_JSON_VALUE })
	public ResponseEntity<Ordercom> update(
			@RequestBody @Valid Ordercom orderRequest) {
		Preconditions.checkArgument(
				orderRequest != null && orderRequest.getId() != null,
				"Problems for update the order.");
		Ordercom order =  orderService.save(orderRequest);
	    return new ResponseEntity<Ordercom>(order, HttpStatus.OK);
	}

	
	@RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
	public ResponseEntity<String>  delete(@PathVariable Long id) {
		orderService.delete(id);
	    return new ResponseEntity<String>("REMOVED", HttpStatus.OK);
	}

}
