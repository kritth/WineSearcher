package com.kritth.klf.wine.dao;

import java.util.List;

import com.kritth.klf.wine.model.Product;

/**
 * DAO class for accessing the product
 * 
 * @author Kritth
 *
 */
public interface ProductDao {
	/**
	 * Search the db by using the id
	 * 
	 * @param prodID ID of the product
	 * @return Product object from database
	 */
	Product findById(int prodID);
	
	/**
	 * Fetch first N product in the db (Not sure what you meant by first 50 wines)
	 * 
	 * @param amount N number
	 * @return List containing the specified product
	 */
	List<Product> findFirstNProduct(int amount);
	
	/**
	 * Find products by given page
	 * 
	 * @param page Number of page
	 * @return List containing the range of products
	 */
	List<Product> findByPage(int page);
}
