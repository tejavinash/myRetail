package com.myretail.service;

import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.myretail.dao.ProductDaoImpl;
import com.myretail.entity.Product;

@Service
@Transactional(readOnly = true)
public class ProductManagerImpl implements ProductManager {

	@Autowired
	private ProductDaoImpl productDAO;

	@Override
	@Transactional(readOnly = false, propagation = Propagation.REQUIRED)
	public void addProduct(Product product) {
		productDAO.create(product);

	}

	@Override
	@Transactional(readOnly = false, propagation = Propagation.REQUIRED)
	public void deleteProduct(Long productId) {
		productDAO.delete(productId);

	}

	@Override
	public Product getProductById(Long productId) {
		return productDAO.getProductById(productId);
	}

	@Override
	public Set<Product> getProductsByCategory(String category) {
		return productDAO.getProductsByCategory(category);
	}

	@Override
	public Set<Product> getProductsByName(String name) {
		return productDAO.getProductsByName(name);
	}

	@Override
	public Set<Product> getProductsByGenericSearch(String searchKey) {
		return productDAO.getProductsByGenericSearch(searchKey);
	}

	@Override
	@Transactional(readOnly = false, propagation = Propagation.REQUIRED)
	public void updateProduct(Product product) {
		productDAO.update(product);

	}

	@Override
	public Product getProductsById(Long id) {
		return productDAO.getProductById(id);
	}

}
