package com.myretail.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.MapsId;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonBackReference;
/**
 * 
 * @author rnandikolla
 *
 */
@Entity
@Table(name = "PRODUCT_PRICE")
public class ProductPrice implements java.io.Serializable {

	private static final long serialVersionUID = 1L;

	@Id
	@Column(name = "ID", unique = true, nullable = false)
	private Long id;
	
	@OneToOne(fetch = FetchType.LAZY)
	@MapsId
	@JoinColumn(name="ID")
	@JsonBackReference
	private Product product;
	
	@Column(name = "PRICE", nullable = false, length = 20)
	private String price;

	public ProductPrice() {
	}
	
	public ProductPrice(String price) {
		this.price = price;
	}

	public ProductPrice(Product product, String price) {
		this.product = product;
		this.price = price;
	}

	public Long getId() {
		return this.id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	
	public Product getProduct() {
		return this.product;
	}

	public void setProduct(Product product) {
		this.product = product;
	}

	
	public String getPrice() {
		return this.price;
	}

	public void setPrice(String price) {
		this.price = price;
	}

}