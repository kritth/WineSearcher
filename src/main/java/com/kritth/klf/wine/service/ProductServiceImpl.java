package com.kritth.klf.wine.service;

import java.util.List;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.kritth.klf.wine.dao.ProductDao;
import com.kritth.klf.wine.model.JSONConvertable;
import com.kritth.klf.wine.model.Product;
import com.kritth.klf.wine.variables.JSON_SIZE;

import static com.kritth.klf.wine.variables.JSON_SIZE.*;

/**
 * Implementation of product service
 * 
 * @author Kritth
 *
 */
@Service
@Transactional(readOnly=true)
public class ProductServiceImpl implements ProductService {
	private static final Logger logger = LoggerFactory.getLogger(ProductService.class);
	
	@Autowired
	private ProductDao prodDao;
	
	@Override
	public JSONObject findById(int prodID, JSON_SIZE size) {
		try {
			Product p = prodDao.findById(prodID);
			return p.toJSON(size);
		} catch (NullPointerException ex) {
			logger.debug(ex.toString(), ex);
			return null;
		}
	}
	
	@Override
	public JSONArray findFirstN(int amount) {
		if (amount >= 0) {
			List<Product> productList = prodDao.findFirstNProduct(amount);
			return convertListToJSONArray(productList, MEDIUM);
		} else {
			return null;
		}
	}

	@Override
	public JSONArray findByPage(int page) {
		if (page > 0) {
		List<Product> productList = prodDao.findByPage(page);
			return convertListToJSONArray(productList, SMALL);
		} else {
			return null;
		}
	}
	
	/**
	 * Convert list to json array object
	 * 
	 * @param list
	 * @param size
	 * @return
	 */
	@SuppressWarnings("unchecked")
	private JSONArray convertListToJSONArray(List<Product> list, JSON_SIZE size) {
		JSONArray result = new JSONArray();
		for (JSONConvertable json : list) {
			result.add(json.toJSON(size));
		}
		return result;
	}
}
