package com.myretail.dao;

import java.util.Set;

import com.myretail.entity.Product;
/** 
 * The purpose of this class to run all the DML commands on Product entity.
 * @author rnandikolla
 *
 */
public interface ProductDAO {

	/**
	 * 
	 * @param id
	 * @return
	 */
	Product get(Long id);

	/**
	 * 
	 * @param product
	 */
	Product create(Product product);

	/**
	 * 
	 * @return
	 */
	Set<Product> getAllProducts();

	/**
	 * 
	 * @param id
	 * @return
	 */
	Product getProductById(Long id);

	/**
	 * 
	 * @param category
	 * @return
	 */
	Set<Product> getProductsByCategory(String category);

	/**
	 * 
	 * @param name
	 * @return
	 */
	Set<Product> getProductsByName(String name);

	/**
	 * 
	 * @param searchKey
	 * @return
	 */
	Set<Product> getProductsByGenericSearch(String searchKey);

	/**
	 * 
	 * @param productId
	 */
	void delete(Long productId);

	/**
	 * 
	 * @param product
	 */
	void update(Product product);
}