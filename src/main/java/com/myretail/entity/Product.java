package com.myretail.entity;

import static javax.persistence.GenerationType.IDENTITY;

import java.util.Date;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import com.fasterxml.jackson.annotation.JsonManagedReference;
/**
 *  
 * @author rnandikolla
 *
 */
@Entity
@Table(name = "PRODUCT")
public class Product implements java.io.Serializable {
 
	private static final long serialVersionUID = -1832354609574387859L;
	
	@Id
	@GeneratedValue(strategy = IDENTITY)
	@Column(name = "ID", unique = true, nullable = false)
	private Long id;
	
	@Column(name = "SKU", unique = true, nullable = false, length = 10)
	private String sku;
	
	@Column(name = "NAME", nullable = false, length = 20)
	private String name;
	
	@Column(name = "CATEGORY", nullable = false, length = 20)
	private String category;
	
	@Temporal(TemporalType.DATE)
	@Column(name = "LAST_UPDATED")
	private Date lastUpdated;
	
	@OneToOne(fetch = FetchType.LAZY, mappedBy = "product", cascade = CascadeType.ALL)
	@JsonManagedReference
	private ProductPrice productPrice;
 
	public Product() {
	}
 
	public Product(String sku, String name, String category, Date lastUpdate) {
		this.sku = sku;
		this.name = name;
		this.category = category;
		this.lastUpdated = lastUpdate;
	}
 
	public Product(String sku, String name, String category, Date lastUpdate, ProductPrice price) {
		this.sku = sku;
		this.name = name;
		this.category = category;
		this.lastUpdated = lastUpdate;
		this.productPrice = price;
	}
 
	public Long getId() {
		return this.id;
	}
 
	public void setId(Long id) {
		this.id = id;
	}
 
	public String getSku() {
		return this.sku;
	}
 
	public void setSku(String sku) {
		this.sku = sku;
	}
 
	public String getName() {
		return this.name;
	}
 
	public void setName(String name) {
		this.name = name;
	}
	
	public String getCategory() {
		return category;
	}

	public Date getLastUpdated() {
		return lastUpdated;
	}

	public void setCategory(String category) {
		this.category = category;
	}

	
	public void setLastUpdated(Date lastUpdated) {
		this.lastUpdated = lastUpdated;
	}
 
	
	public ProductPrice getProductPrice() {
		return this.productPrice;
	}
 
	public void setProductPrice(ProductPrice productPrice) {
		this.productPrice = productPrice;
	}
 
}