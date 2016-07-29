package com.tsm.controller;

import static org.hamcrest.Matchers.equalTo;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

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
import com.tsm.model.entity.Product;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringApplicationConfiguration(classes = EcomApplication.class)
@WebAppConfiguration
@ActiveProfiles("dev")
public class ProductControllerTest {

	@Autowired
	private WebApplicationContext context;
	
	private MockMvc mockMvc;

	@Before
	public void setUp() {
		this.mockMvc = MockMvcBuilders.webAppContextSetup(this.context).build();
	}

	@Autowired
	private WebApplicationContext wac;

	@Test
	public void shouldReturnProduct() throws Exception {

		Product one = new Product();
		one.setId(10l);
		one.setDescription("Desc 10");

		Long id = 1l;
		this.mockMvc.perform(get(String.format("/product/%s", id)).accept(MediaType.APPLICATION_JSON))
				.andExpect(status().isOk()).andExpect(jsonPath("description", equalTo("Pants realy beatyful")))
				.andExpect(jsonPath("name", equalTo("Blue Pants"))).andExpect(jsonPath("catalog", equalTo("Store")))
				.andExpect(jsonPath("id", equalTo(1)));
	}

	@Test
	public void shouldReturnNotFoundFindingProduct() throws Exception {
		Long id = 3l;
		this.mockMvc.perform(get(String.format("/product/%s", id)).accept(MediaType.APPLICATION_JSON))
				.andExpect(status().is(HttpStatus.NOT_FOUND.value()));

	}
	
	@Test
	public void shouldReturnProductsCatalog() throws Exception {
		String catalog = "Store";
		this.mockMvc.perform(get(String.format("/product/cat/%s", catalog)).accept(MediaType.APPLICATION_JSON))
				.andExpect(status().isOk());
	}

	@Test
	public void shouldReturnNotFoundProductsCatalog() throws Exception {
		String catalog = "Store_2";
		this.mockMvc.perform(get(String.format("/product/cat/%s", catalog)).accept(MediaType.APPLICATION_JSON))
		.andExpect(status().is(HttpStatus.NOT_FOUND.value()));
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
