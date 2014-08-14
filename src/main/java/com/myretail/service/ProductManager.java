package com.myretail.service;

import java.util.Set;

import com.myretail.entity.Product;

/**
 * This manager provide services to all product related crud operations.
 * 
 * @author rnandikolla
 *
 */
public interface ProductManager {

	/**
	 * Purpose of this method To get the product info based on product Id.
	 * 
	 * @param id
	 *            product unique key.
	 * @return Product @{Product}
	 */
	Product getProductsById(Long id);

	/**
	 * Purpose of this method is to persist a new Product.
	 * 
	 * @param product
	 * @{Product
	 */
	void addProduct(Product product);

	/**
	 * purpose of this method is to retrieve product information based on
	 * provided product Id.
	 * 
	 * @param id
	 *            Product unique key.
	 * @return Product @{Product}
	 */
	Product getProductById(Long id);

	/**
	 * purpose of this method is to retrieve set of products based on category.
	 * 
	 * @param category
	 *            String to search a Product
	 * @return Set of Products based on category.
	 */
	Set<Product> getProductsByCategory(String category);

	/**
	 * 
	 * purpose of this method is to retrieve set of products based on name.
	 * 
	 * @param category
	 *            String to search a Product
	 * @return return Set of Products based on name
	 */
	Set<Product> getProductsByName(String name);

	/**
	 * purpose of this method is to retrieve set of products based on generic
	 * search key.
	 * 
	 * @param searchKey
	 * @return return Set of Products based on name
	 */
	Set<Product> getProductsByGenericSearch(String searchKey);

	/**
	 * Puprose of this method to delete a Product based on ID.
	 * 
	 * @param productId
	 *            @{Long} Unique product Id.
	 */
	void deleteProduct(Long productId);

	/**
	 * Puprose of this method to udpate a product
	 * 
	 * @param product
	 *            @{Product}
	 */
	void updateProduct(Product product);
}
