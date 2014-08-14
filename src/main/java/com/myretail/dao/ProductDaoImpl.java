package com.myretail.dao;

import java.util.HashSet;
import java.util.Set;

import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.myretail.entity.Product;

/**
 * 
 * @author rnandikolla
 *
 */
@Repository
public class ProductDaoImpl implements ProductDAO {

	@Autowired
	SessionFactory sessionFactory;

	private Session getSession() {
		return (Session) sessionFactory.getCurrentSession();
	}

	@Override
	public Product get(Long id) {
		return (Product) getSession().get(Product.class, id);
	}

	@Override
	public Product create(Product product) {
		getSession().save(product);
		getSession().flush();
		return product;
	}

	@Override
	public void delete(Long productId) {
		Product product = get(productId);
		if (product != null)
			getSession().delete(product);
	}

	@Override
	public void update(Product product) {
		getSession().saveOrUpdate(product);
		getSession().flush();
	}

	@SuppressWarnings("unchecked")
	@Override
	public Set<Product> getAllProducts() {
		Criteria cr = getSession().createCriteria(Product.class);
		Set<Product> products = new HashSet<Product>(cr.list());
		return products;
	}

	@Override
	public Product getProductById(Long productId) {
		return (Product) getSession().get(Product.class, productId);
	}

	@SuppressWarnings("unchecked")
	@Override
	public Set<Product> getProductsByCategory(String category) {
		Criteria cr = getSession().createCriteria(Product.class).add(
				Restrictions.eq("category", category));
		Set<Product> products = new HashSet<Product>(cr.list());
		return products;
	}

	@SuppressWarnings("unchecked")
	@Override
	public Set<Product> getProductsByName(String name) {
		Criteria cr = getSession().createCriteria(Product.class).add(
				Restrictions.like("name", "%" + name + "%"));
		Set<Product> products = new HashSet<Product>(cr.list());
		return products;
	}

	@SuppressWarnings("unchecked")
	@Override
	public Set<Product> getProductsByGenericSearch(String searchKey) {
		Criteria cr = getSession().createCriteria(Product.class).add(
				Restrictions.or(
						Restrictions.like("name", "%" + searchKey + "%"),
						Restrictions.like("category", "%" + searchKey + "%")));
		Set<Product> products = new HashSet<Product>(cr.list());
		return products;
	}

}
