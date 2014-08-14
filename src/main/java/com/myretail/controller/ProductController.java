package com.myretail.controller;

import java.util.Set;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.myretail.entity.Product;
import com.myretail.service.ProductManager;
/**
 * The purpose of this class to run all the CRUD calls on Product.
 * @author rnandikolla
 *
 */
@Controller
@RequestMapping("/product")
public class ProductController {
	private static final Logger LOG = Logger.getLogger(ProductController.class);

	@Autowired
	private ProductManager productManager;
	
	@RequestMapping(value = "/get/{id}", method = RequestMethod.GET)
	public @ResponseBody Product getProductsById(
			@PathVariable Long id) {
		LOG.debug("Retrieving Product details by id " + id );
		return productManager.getProductsById(id);
	}

	@RequestMapping(value = "/getByName/{name}", method = RequestMethod.GET)
	public @ResponseBody Set<Product> getProductsByName(
			@PathVariable String name) {
		LOG.debug("Retrieving Product details by name " + name );
		return productManager.getProductsByName(name);
	}

	@RequestMapping(value = "/getByCategory/{category}", method = RequestMethod.GET)
	public @ResponseBody Set<Product> getProductsByCategory(
			@PathVariable String category) {
		LOG.debug("Retrieving Product details by category " + category );
		return productManager.getProductsByCategory(category);
	}

	@RequestMapping(value = "/getByGenericKey/{key}", method = RequestMethod.GET)
	public @ResponseBody Set<Product> getProductsByGenericSearch(
			@PathVariable String key) {
		LOG.debug("Retrieving Product details by key " + key );
		return productManager.getProductsByGenericSearch(key);
	}

	@RequestMapping(value = "/create", method = RequestMethod.POST)
	public @ResponseBody Product createProduct(@RequestBody Product product) {
		LOG.debug("Adding a new Product " );
		productManager.addProduct(product);
		return product;
	}

	@RequestMapping(value = "/{id}", method = RequestMethod.PUT)
	public @ResponseBody Product updateProduct(@RequestBody Product product) {
		LOG.debug("udpating a Product with an Id of " + product.getId() );
		productManager.updateProduct(product);
		return product;
	}

	@RequestMapping(value = "/delete/{productId}", method = RequestMethod.DELETE)
	public String deleteEmplyee(@PathVariable Long productId) {
		LOG.debug("deleting a Product with an Id of " + productId );
		productManager.deleteProduct(productId);
		return "200";
	}

}
