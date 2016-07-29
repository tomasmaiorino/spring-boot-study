package com.tsm.model.entity;

import java.math.BigDecimal;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@Entity
public class Sku {

	public Sku() {
	}
	
	
	
	@Id
    @GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;

	@ManyToOne
	@JoinColumn(name = "product_id")
	private Product product;
	
    @Size(min = 5, max = 150, message = "{description.size}")
    @NotNull(message = "{description.notnull}")
    @Column(nullable = false, length = 150)
	private String description;
    
    @Size(min = 5, max = 150, message = "{name.size}")
    @NotNull(message = "{name.notnull}")
    @Column(nullable = false, length = 150)
    private String name;
	
    @NotNull(message = "{price.notnull}")
    @Column(nullable = false, length = 100)
	private BigDecimal price;
    
    private int stock;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public BigDecimal getPrice() {
		return price;
	}

	public void setPrice(BigDecimal price) {
		this.price = price;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public int getStock() {
		return stock;
	}

	public void setStock(int stock) {
		this.stock = stock;
	}
	
}
