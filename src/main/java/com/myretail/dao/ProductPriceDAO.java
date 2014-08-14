package com.myretail.dao;

import com.myretail.entity.ProductPrice;

public interface ProductPriceDAO 
{
	/**
	 * 
	 * @param id
	 * @return
	 */
    ProductPrice getProductPriceById(Long id);
    /**
     * 
     * @param productId
     */
    void delete(Long productId); 
    /**
     * 
     * @param productId
     */
    void update(ProductPrice price); 
    /**
     * 
     * @param id
     * @return
     */
    ProductPrice get(Long id);
}