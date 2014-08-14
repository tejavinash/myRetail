package com.myretail.dao;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

import java.util.Date;

import org.junit.Before;
import org.junit.Ignore;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.TestExecutionListeners;
import org.springframework.test.context.junit4.AbstractTransactionalJUnit4SpringContextTests;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.support.DependencyInjectionTestExecutionListener;

import com.myretail.entity.Product;
import com.myretail.entity.ProductPrice;

/**
 * 
 * @author rnandikolla
 *
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = { "classpath*:test-servlet.xml" })
@TestExecutionListeners({ DependencyInjectionTestExecutionListener.class })
public class ProductPriceDAOTest extends
		AbstractTransactionalJUnit4SpringContextTests {

	@Autowired
	ProductPriceDAO productPriceDAO;
	
	@Autowired
	ProductDAO productDAO;

	@Before
	public void setup() {
		Product product = new Product("AEX143", "Stroller", "baby", new Date());
		ProductPrice productPrice = new ProductPrice(product, "199.99");
		product.setProductPrice(productPrice);
		productDAO.create(product);

		product = new Product("IOL123", "Optimus Prime", "toys", new Date());
		productPrice = new ProductPrice(product, "13.37");
		product.setProductPrice(productPrice);
		productDAO.create(product);

		product = new Product("XYZ904", "Sega Genesis", "toys", new Date());
		productPrice = new ProductPrice(product, "149.99");
		product.setProductPrice(productPrice);
		productDAO.create(product);

	}

	@Ignore
	public void testGetProductPriceById() throws Exception {
		ProductPrice price = productPriceDAO.getProductPriceById(new Long(1));
		assertNotNull(price);
		assertEquals(new Long(1), price.getId());
		assertEquals("199.99", price.getPrice());
	}

	@Ignore
	public void testUpdate() throws Exception {
		ProductPrice price = productPriceDAO.get(new Long(1));
		assertEquals("199.99", price.getPrice());
		price.setPrice("999");
		productPriceDAO.update(price);
		assertEquals("999", price.getPrice());
	}

	@Ignore
	public void testDelete() throws Exception {
		productPriceDAO.delete(new Long(1));
		assertEquals(null,productPriceDAO.get(new Long(1)));
	}	
	

	@Test(expected = IllegalArgumentException.class)
	public void testException() throws Exception {
		productPriceDAO.delete(new Long(999));
	}	

}
