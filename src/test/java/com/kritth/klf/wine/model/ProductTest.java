package com.kritth.klf.wine.model;

import java.sql.Date;

import org.json.simple.JSONObject;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.kritth.klf.wine.variables.JSON_SIZE;

public class ProductTest {
	private final static Logger logger = LoggerFactory.getLogger(ProductTest.class);
	
	private Product obj;
	
	@Before
	public void setup() {
		obj = new Product();
		obj.setProdID(1);
		obj.setProdDateBuy(new Date(100));
		obj.setProdName("prod name");
		obj.setProdPriceBuy(1.00);
		obj.setProdSellPrice(1.00);
	}
	
	@Test
	public void testToJSONLarge() {
		logger.info("Running test on regular test on Product.toJSON(LARGE)");
		
		JSONObject result = obj.toJSON(JSON_SIZE.LARGE);
		
		Assert.assertEquals(5, result.size());
		Assert.assertNotNull(result.get(Product._JSON_ID));
		Assert.assertNotNull(result.get(Product._JSON_DATE_BUY));
		Assert.assertNotNull(result.get(Product._JSON_PRICE_BUY));
		Assert.assertNotNull(result.get(Product._JSON_PRICE_SELL));
		Assert.assertNotNull(result.get(Product._JSON_NAME));
	}
	
	@Test
	public void testToJSONMedium() {
		logger.info("Running test on regular test on Product.toJSON(MEDIUM)");
		
		JSONObject result = obj.toJSON(JSON_SIZE.MEDIUM);
		
		Assert.assertEquals(2, result.size());
		Assert.assertNull(result.get(Product._JSON_ID));
		Assert.assertNull(result.get(Product._JSON_DATE_BUY));
		Assert.assertNull(result.get(Product._JSON_PRICE_BUY));
		Assert.assertNotNull(result.get(Product._JSON_PRICE_SELL));
		Assert.assertNotNull(result.get(Product._JSON_NAME));
	}
	
	@Test
	public void testToJSONSmall() {
		logger.info("Running test on regular test on Product.toJSON(MEDIUM)");
		
		JSONObject result = obj.toJSON(JSON_SIZE.SMALL);
		
		Assert.assertEquals(1, result.size());
		Assert.assertNull(result.get(Product._JSON_ID));
		Assert.assertNull(result.get(Product._JSON_DATE_BUY));
		Assert.assertNull(result.get(Product._JSON_PRICE_BUY));
		Assert.assertNull(result.get(Product._JSON_PRICE_SELL));
		Assert.assertNotNull(result.get(Product._JSON_NAME));
	}
	
	@Test
	public void testToJSONNull() {
		logger.info("Running test on fail test on Product.toJSON(...)");
		
		JSONObject result = obj.toJSON(null);
		
		Assert.assertNull(result);
	}
}