package com.myretail.dao;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

import java.util.Date;
import java.util.Set;

import org.hibernate.exception.ConstraintViolationException;
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
public class ProductDAOTest extends
		AbstractTransactionalJUnit4SpringContextTests {

	@Autowired
	ProductDAO productDAO;

	@Before
	public void setup() {
		Product product = new Product("AEX145", "Stroller", "baby", new Date());
		ProductPrice productPrice = new ProductPrice(product, "199.99");
		product.setProductPrice(productPrice);
		productDAO.create(product);

		product = new Product("IOL124", "Optimus Prime", "toys", new Date());
		productPrice = new ProductPrice(product, "13.37");
		product.setProductPrice(productPrice);
		productDAO.create(product);

		product = new Product("XYZ905", "Sega Genesis", "toys", new Date());
		productPrice = new ProductPrice(product, "149.99");
		product.setProductPrice(productPrice);
		productDAO.create(product);

	}

	@Test
	public void testGetAllProducts() throws Exception {
		Set<Product> products = productDAO.getAllProducts();
		assertNotNull(products);
		assertEquals(3, products.size());
	}

	@Test
	public void testGetProductsByCategory() throws Exception {
		Set<Product> products = productDAO.getProductsByCategory("toys");
		assertNotNull(products);
		assertEquals(2, products.size());
		for (Product product : products){
			ProductPrice pp = product.getProductPrice();
			assertNotNull(pp);
		}
	}

	@Test
	public void testGetProductsByName() throws Exception {
		Set<Product> products = productDAO.getProductsByName("Stroller");
		assertNotNull(products);
		assertEquals(1, products.size());
		for (Product product : products){
			ProductPrice pp = product.getProductPrice();
			assertEquals("199.99", pp.getPrice());
		}
	}
	
	@Ignore
	//@Rollback(false)
	public void testGetProductById() throws Exception {
		Product product = productDAO.getProductById(new Long(1));
		assertNotNull(product);
		assertEquals(new Long(1), product.getId());
	}

	@Test
	public void testGetProductsByGenericSearch() throws Exception {
		Set<Product> products = productDAO.getProductsByGenericSearch("toys");
		assertNotNull(products);
		assertEquals(2, products.size());
		for (Product product : products){
			ProductPrice pp = product.getProductPrice();
			assertNotNull(pp);
		}
	}
	
	@Ignore
	//@Rollback(false)
	public void testUpdate() throws Exception {
		Product product = productDAO.get(new Long(1));
		assertEquals("Stroller", product.getName());
		product.setName("Updated_Name");
		ProductPrice pp = product.getProductPrice();
		assertEquals("199.99", pp.getPrice());
		pp.setPrice("200");
		productDAO.update(product);		
		assertNotNull(product);
		assertEquals("Updated_Name", product.getName());
		assertNotNull(product.getProductPrice());
		assertEquals("200", product.getProductPrice().getPrice());
	}

	@Test
	public void testDelete() throws Exception {
		productDAO.delete(new Long(1));
		//assertEquals(null,productDAO.get(new Long(1)));
	}	
	

	@Ignore
	@Test(expected = IllegalArgumentException.class)
	public void fTest() throws Exception {
		productDAO.delete(new Long(999));
	}
	
	@Test(expected = ConstraintViolationException.class)
	public void testInsertingDuplicates() throws Exception {
		Product product = new Product("AEX145", "Stroller", "baby", new Date());
		productDAO.create(product);
	}

}
