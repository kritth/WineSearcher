package com.kritth.klf.wine.service;

import java.util.ArrayList;
import java.util.List;

import org.json.JSONException;
import org.json.simple.JSONObject;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.skyscreamer.jsonassert.JSONAssert;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.kritth.klf.wine.dao.ProductDao;
import com.kritth.klf.wine.model.Product;
import com.kritth.klf.wine.variables.JSON_SIZE;

@RunWith(SpringJUnit4ClassRunner.class)
public class ProductServiceTest {
	private static final Logger logger = LoggerFactory.getLogger(ProductServiceTest.class);
	
	@InjectMocks
	private ProductServiceImpl service;
	
	@MockBean
	private ProductDao dao;
	
	@Mock
	private Product obj;
	
	private JSONObject jobj = new JSONObject();
	private List<Product> list = new ArrayList<>();
	
	@Before
	public void setup() {
		MockitoAnnotations.initMocks(this);
	}
	
	@Test
	public void testFindById() throws JSONException {
		logger.info("Running test on regular test on ProductService.findById(...)");
		Mockito.doReturn(obj).when(dao).findById(1);
		Mockito.doReturn(jobj).when(obj).toJSON(JSON_SIZE.SMALL);
		
		JSONAssert.assertEquals("{}", service.findById(1, JSON_SIZE.SMALL).toString(), false);
	}
	
	@Test
	public void testFindByIdFail() throws JSONException {
		logger.info("Running test on fail test on ProductService.findById(...)");
		
		Assert.assertNull(service.findById(-1, JSON_SIZE.SMALL));
	}
	
	@Test
	public void testFindFirstN() {
		logger.info("Running test on regular test on ProductService.findFirstN(...)");
		Mockito.doReturn(list).when(dao).findFirstNProduct(1);
		Mockito.doReturn(jobj).when(obj).toJSON(JSON_SIZE.MEDIUM);
		list.add(obj);
		
		Assert.assertEquals(service.findFirstN(1).size(), 1);
	}
	
	@Test
	public void testFindFirstNFail() {
		logger.info("Running test on fail test on ProductService.findFirstN(...)");
		
		Assert.assertNull(service.findFirstN(-1));
	}
	
	@Test
	public void testFindByPage() {
		logger.info("Running test on regular test on ProductService.findByPage(...)");
		Mockito.doReturn(list).when(dao).findByPage(1);
		Mockito.doReturn(jobj).when(obj).toJSON(JSON_SIZE.SMALL);
		list.add(obj);
		
		Assert.assertEquals(service.findByPage(1).size(), 1);
	}
	
	@Test
	public void testFindByPageFail() {
		logger.info("Running test on fail test on ProductService.findByPage(...)");
		
		Assert.assertNull(service.findByPage(-1));
	}
}