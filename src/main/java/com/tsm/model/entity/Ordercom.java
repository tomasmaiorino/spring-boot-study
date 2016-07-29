package com.tsm.model.entity;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.PrePersist;
import javax.persistence.PreUpdate;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@Entity
public class Ordercom implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 8687916891514637268L;

	public Ordercom() {}
	
	public Ordercom(Set<CommerceItem> comItems, String status, String paymentStatus, BigDecimal totalAmount) {
		this.commerceItems = comItems;
		this.status = status;
		this.paymentStatus = paymentStatus;
		this.totalAmount = totalAmount;
	}
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;

	@OneToMany(fetch=FetchType.LAZY, cascade = CascadeType.ALL, mappedBy="order")
	private Set<CommerceItem> commerceItems;// = new HashSet<CommerceItem>();

	@Column(name="STATUS")
	private String status;

	@Size(min = 5, max = 150, message = "{name.size}")
	@NotNull(message = "{name.notnull}")
	@Column(name = "PAYMENTSTATUS")
	private String paymentStatus;
	
	@Column(name = "TOTALAMOUNT")
	private BigDecimal totalAmount;
	
	@Column(name = "CREATIONDATE")
	private Date creationDate;
	
	@Column(name = "LASTMODIFIEDDATE")
	private Date lastModifiedDate;
	
	@PrePersist
	private void createCreationDate(){
		this.creationDate = new Date();
		this.lastModifiedDate = new Date();
	}
	@PreUpdate
	private void updateLastModifiedDate(){
		this.lastModifiedDate = new Date();
	}
	
	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public String getPaymentStatus() {
		return paymentStatus;
	}

	public void setPaymentStatus(String paymentStatus) {
		this.paymentStatus = paymentStatus;
	}

	public BigDecimal getTotalAmount() {
		return totalAmount;
	}

	public void setTotalAmount(BigDecimal totalAmount) {
		this.totalAmount = totalAmount;
	}

	public Set<CommerceItem> getCommerceItems() {
		return commerceItems;
	}

	public void setCommerceItems(Set<CommerceItem> commerceItems) {
		this.commerceItems = commerceItems;
	}

	public Date getCreationDate() {
		return creationDate;
	}

	public void setCreationDate(Date creationDate) {
		this.creationDate = creationDate;
	}

	public Date getLastModifiedDate() {
		return lastModifiedDate;
	}

	public void setLastModifiedDate(Date lastModifiedDate) {
		this.lastModifiedDate = lastModifiedDate;
	}
}

