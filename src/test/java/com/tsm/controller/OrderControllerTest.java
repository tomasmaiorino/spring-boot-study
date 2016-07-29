package com.tsm.controller;

import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.greaterThanOrEqualTo;
import static org.hamcrest.Matchers.hasSize;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.math.BigDecimal;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.SpringApplicationConfiguration;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import com.tsm.config.EcomApplication;
import com.tsm.model.entity.CommerceItem;
import com.tsm.model.entity.Ordercom;
import com.tsm.model.entity.Sku;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringApplicationConfiguration(classes = EcomApplication.class)
@WebAppConfiguration
@ActiveProfiles("dev")
public class OrderControllerTest extends BaseControllerTest {

	@Autowired
	private WebApplicationContext context;

	@Before
	public void setUp() {
		this.mockMvc = MockMvcBuilders.webAppContextSetup(this.context).build();
	}

	private MockMvc mockMvc;

	@Autowired
	private WebApplicationContext wac;

	@Test
	public void shouldReturOrder() throws Exception {
		Long id = 1l;
		this.mockMvc.perform(get(String.format("/order/%s", id)).accept(MediaType.APPLICATION_JSON))
				.andExpect(status().isOk()).andExpect(jsonPath("status", equalTo("SUBMITTED")))
				.andExpect(jsonPath("paymentStatus", equalTo("CREATED")))
				.andExpect(jsonPath("status", equalTo("SUBMITTED"))).andExpect(jsonPath("id", equalTo(1)));

	}

	@Test
	public void shouldReturnNullFoundOrder() throws Exception {
		Long id = 30l;
		this.mockMvc.perform(get(String.format("/order/%s", id)).accept(MediaType.APPLICATION_JSON))
				.andExpect(status().is(HttpStatus.NOT_FOUND.value()));
	}

	@Test
	public void shouldCreateOrder() throws Exception {
		Ordercom order = createOrder();
		this.mockMvc.perform(post("/order").contentType(MediaType.APPLICATION_JSON).content(convertObjectToJsonBytes(order))
				.accept(MediaType.APPLICATION_JSON)).andDo(setContentType("charset=utf-8")).andExpect(status().isOk())
				.andExpect(jsonPath("id", greaterThanOrEqualTo(1)))
				.andExpect(jsonPath("status", equalTo(order.getStatus())));
	}
	
	@Test
	public void shouldUpdateOrder() throws Exception {
		Ordercom order = createOrder();
		//creates an onrder
		this.mockMvc.perform(post("/order").contentType(MediaType.APPLICATION_JSON).content(convertObjectToJsonBytes(order))
				.accept(MediaType.APPLICATION_JSON)).andDo(setContentType("charset=utf-8")).andExpect(status().isOk())
				.andExpect(jsonPath("id", greaterThanOrEqualTo(1)))
				.andExpect(jsonPath("status", equalTo(order.getStatus())));
		order.setId(3l);
		order.setStatus("APPROVED");
		this.mockMvc.perform(put("/order").contentType(MediaType.APPLICATION_JSON).content(convertObjectToJsonBytes(order))
				.accept(MediaType.APPLICATION_JSON)).andDo(setContentType("charset=utf-8")).andExpect(status().isOk())
				.andExpect(jsonPath("id", equalTo(3)))
				.andExpect(jsonPath("status", equalTo("APPROVED")));
	}
	
	@Test
	public void shouldListAllOrder() throws Exception {
		//the orders list size is equal 5 because there were create two orders during the tests
		this.mockMvc.perform(get(String.format("/order/%s", "all")).accept(MediaType.APPLICATION_JSON))
		.andExpect(status().isOk()).andExpect(jsonPath("$.*", hasSize(5)));
	}
	
	@Test
	public void shouldListAllOrderBySky() throws Exception {
		Long skuId = 3l;
		this.mockMvc.perform(get(String.format("/order/sku/%s", skuId)).accept(MediaType.APPLICATION_JSON))
		.andExpect(status().isOk()).andExpect(jsonPath("$.*", hasSize(2)));
	}

	private Ordercom createOrder() {
		Ordercom order = new Ordercom();
		order.setCreationDate(new Date());
		order.setLastModifiedDate(new Date());
		order.setPaymentStatus("CREATED");
		order.setStatus("SUBMITTED");
		order.setTotalAmount(new BigDecimal(36.00));

		Sku sku = new Sku();
		sku.setId(1l);

		CommerceItem com = new CommerceItem();
		com.setSku(sku);
		com.setQuantity(3);
		com.setUnitValue(new BigDecimal(12.0));
		Set<CommerceItem> items = new HashSet<CommerceItem>();
		items.add(com);
		order.setCommerceItems(items);

		return order;
	}

	public MockMvc getMockMvc() {
		return mockMvc;
	}

	public void setMockMvc(MockMvc mockMvc) {
		this.mockMvc = mockMvc;
	}

	public WebApplicationContext getWac() {
		return wac;
	}

	public void setWac(WebApplicationContext wac) {
		this.wac = wac;
	}

}
