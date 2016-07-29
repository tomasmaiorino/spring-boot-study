package com.tsm.model.entity;

import java.io.Serializable;
import java.math.BigDecimal;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
@Table(name = "COMMERCEITEM")
public class CommerceItem implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -1963746218545248355L;

	public CommerceItem() {
	}
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;

	@ManyToOne
	@JoinColumn(name = "sku_id")
	private Sku sku;

	private int quantity;

	@Column(name = "UNITVALUE")
	private BigDecimal unitValue;

	@ManyToOne(cascade = CascadeType.ALL)
	@JoinColumn(name = "order_id")
	@JsonIgnore
	private Ordercom order;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Sku getSku() {
		return sku;
	}

	public void setSku(Sku sku) {
		this.sku = sku;
	}

	public int getQuantity() {
		return quantity;
	}

	public void setQuantity(int quantity) {
		this.quantity = quantity;
	}

	public BigDecimal getUnitValue() {
		return unitValue;
	}

	public void setUnitValue(BigDecimal unitValue) {
		this.unitValue = unitValue;
	}

	public Ordercom getOrder() {
		return order;
	}

	public void setOrder(Ordercom order) {
		this.order = order;
	}
}
