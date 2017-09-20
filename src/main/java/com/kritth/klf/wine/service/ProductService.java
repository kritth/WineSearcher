package com.kritth.klf.wine.service;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;

import com.kritth.klf.wine.variables.JSON_SIZE;

/**
 * Serve the business logic provided by the assignment specification
 * 
 * @author Kritth
 *
 */
public interface ProductService {
	/**
	 * Find product by id and return 1 property (name)
	 * 
	 * @param prodID
	 * @param size
	 * @return JSON object of produce
	 */
	JSONObject findById(int prodID, JSON_SIZE size);
	
	/**
	 * Find first 50 products and return name and price
	 * 
	 * @return JSON array of products
	 */
	JSONArray findFirstN(int amount);
	
	/**
	 * Find product by page and return names
	 * 
	 * @param page
	 * @return
	 */
	JSONArray findByPage(int page);
}
