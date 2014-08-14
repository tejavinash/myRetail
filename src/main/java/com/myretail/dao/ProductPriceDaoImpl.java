package com.myretail.dao;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.myretail.entity.ProductPrice;

@Repository
public class ProductPriceDaoImpl implements ProductPriceDAO {

	@Autowired
	private SessionFactory sessionFactory;

	private Session getSession() {
		return this.sessionFactory.getCurrentSession();
	}

	@Override
	public ProductPrice getProductPriceById(Long productId) {
		return (ProductPrice) getSession().get(ProductPrice.class, productId);
	}

	@Override
	public ProductPrice get(Long priceId) {
		return (ProductPrice) getSession().get(ProductPrice.class, priceId);
	}

	@Override
	public void delete(Long priceId) {
		getSession().delete(get(priceId));
		getSession().flush();
	}

	@Override
	public void update(ProductPrice price) {
		getSession().saveOrUpdate(get(price.getId()));
		getSession().flush();
	}

}
