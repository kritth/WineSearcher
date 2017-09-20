package com.kritth.klf.wine.controller;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.skyscreamer.jsonassert.JSONAssert;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.RequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import com.kritth.klf.wine.service.ProductService;
import com.kritth.klf.wine.variables.JSON_SIZE;

@RunWith(SpringJUnit4ClassRunner.class)
public class ProductControllerTest {
	private static final Logger logger = LoggerFactory.getLogger(ProductControllerTest.class);
	
	@InjectMocks
	private ProductController controller;
	
	@MockBean
	private ProductService service;
	
	private MockMvc mockMvc;
	
	JSONObject mockObj = new JSONObject();
	JSONArray mockAry = new JSONArray();
	
	@Before
	public void setup() {
		MockitoAnnotations.initMocks(this);
		mockMvc = MockMvcBuilders.standaloneSetup(controller).build();
	}
	
	@Test
	public void testGetById() throws Exception {
		logger.info("Running test on regular request '/wine/find/{id}' on ProductController");
		
		Mockito.when(service.findById(1, JSON_SIZE.SMALL)).thenReturn(mockObj);
		RequestBuilder requestBuilder = MockMvcRequestBuilders.get("/wine/find/1").accept(MediaType.APPLICATION_JSON);
		
		MvcResult result = mockMvc.perform(requestBuilder).andReturn();
		String expected = "{}";
		
		JSONAssert.assertEquals(expected, result.getResponse().getContentAsString(), false);
	}
	
	@Test
	public void testGetByIdBonus() throws Exception {
		logger.info("Running test on regular request '/wine/find/{id}/bonus' on ProductController");
		
		Mockito.when(service.findById(1, JSON_SIZE.LARGE)).thenReturn(mockObj);
		RequestBuilder requestBuilder = MockMvcRequestBuilders.get("/wine/find/1/bonus").accept(MediaType.APPLICATION_JSON);
		
		MvcResult result = mockMvc.perform(requestBuilder).andReturn();
		String expected = "{}";
		
		JSONAssert.assertEquals(expected, result.getResponse().getContentAsString(), false);
	}
	
	@Test
	public void testGetByIdNormal() throws Exception {
		logger.info("Running test on regular request '/wine/find/{id}/normal' on ProductController");
		
		Mockito.when(service.findById(1, JSON_SIZE.LARGE)).thenReturn(mockObj);
		RequestBuilder requestBuilder = MockMvcRequestBuilders.get("/wine/find/1/bonus").accept(MediaType.APPLICATION_JSON);
		
		MvcResult result = mockMvc.perform(requestBuilder).andReturn();
		String expected = "{}";
		
		JSONAssert.assertEquals(expected, result.getResponse().getContentAsString(), false);
	}
	
	@Test
	public void testGetByIdFail() throws Exception {
		logger.info("Running test on failed request '/wine/find/{id}' on ProductController");
		
		RequestBuilder requestBuilder = MockMvcRequestBuilders.get("/wine/find/-1").accept(MediaType.APPLICATION_JSON);
		
		MvcResult result = mockMvc.perform(requestBuilder).andReturn();
		int expected_result = HttpStatus.BAD_REQUEST.value();
		
		Assert.assertEquals(expected_result, result.getResponse().getStatus());
	}
	
	@Test
	public void testGetByIdBonusFail() throws Exception {
		logger.info("Running test on failed request '/wine/find/{id}/anything' on ProductController");
		
		RequestBuilder requestBuilder = MockMvcRequestBuilders.get("/wine/find/1/anything").accept(MediaType.APPLICATION_JSON);
		
		MvcResult result = mockMvc.perform(requestBuilder).andReturn();
		int expected_result = HttpStatus.BAD_REQUEST.value();
		
		Assert.assertEquals(expected_result, result.getResponse().getStatus());
	}
	
	@Test
	public void testGetFirstN() throws Exception {
		logger.info("Running test on regular request '/wine/first/1' on ProductController");
		
		Mockito.when(service.findFirstN(1)).thenReturn(mockAry);
		RequestBuilder requestBuilder = MockMvcRequestBuilders.get("/wine/first/1").accept(MediaType.APPLICATION_JSON);
		
		MvcResult result = mockMvc.perform(requestBuilder).andReturn();
		String expected = "[]";
		
		JSONAssert.assertEquals(expected, result.getResponse().getContentAsString(), false);
	}
	
	@Test
	public void testGetFirstNFail() throws Exception {
		logger.info("Running test on fail request '/wine/first/-1' on ProductController");
		
		RequestBuilder requestBuilder = MockMvcRequestBuilders.get("/wine/first/-11").accept(MediaType.APPLICATION_JSON);
		
		MvcResult result = mockMvc.perform(requestBuilder).andReturn();
		int expected_result = HttpStatus.BAD_REQUEST.value();
		
		Assert.assertEquals(expected_result, result.getResponse().getStatus());
	}
	
	@Test
	public void testGetByPage() throws Exception {
		logger.info("Running test on regular request '/wine/page/1' on ProductController");
		
		Mockito.when(service.findByPage(1)).thenReturn(mockAry);
		RequestBuilder requestBuilder = MockMvcRequestBuilders.get("/wine/page/1").accept(MediaType.APPLICATION_JSON);
		
		MvcResult result = mockMvc.perform(requestBuilder).andReturn();
		String expected = "[]";
		
		JSONAssert.assertEquals(expected, result.getResponse().getContentAsString(), false);
	}
	
	@Test
	public void testGetByPageFail() throws Exception {
		logger.info("Running test on fail request '/wine/page/0' on ProductController");
		
		RequestBuilder requestBuilder = MockMvcRequestBuilders.get("/wine/page/0").accept(MediaType.APPLICATION_JSON);
		
		MvcResult result = mockMvc.perform(requestBuilder).andReturn();
		int expected_result = HttpStatus.BAD_REQUEST.value();
		
		Assert.assertEquals(expected_result, result.getResponse().getStatus());
	}
}