package com.kritth.klf.wine.dao;

import java.util.List;

import javax.transaction.Transactional;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.kritth.klf.wine.AppStart;
import com.kritth.klf.wine.model.Product;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest
@ContextConfiguration(classes=AppStart.class)
public class ProductDaoTest {
	private static Logger logger = LoggerFactory.getLogger(ProductDaoTest.class);
	
	@Autowired
	private ProductDao dao;
	
	@Test
	@Transactional
	@Rollback(true)
	public void testFindById() {
		logger.info("Running test on regular test on ProductDao.findById(...)");
		Product p = dao.findById(1);
		Assert.assertEquals(p.getProdID(), 1);
	}
	
	@Test
	@Transactional
	@Rollback(true)
	public void testFindByIdNotFound() {
		logger.info("Running test on exception test on ProductDao.findById(...)");
		Product p = dao.findById(4);
		Assert.assertNull(p);
	}
	
	@Test
	@Transactional
	@Rollback(true)
	public void testFindFirstNProduct() {
		logger.info("Running test on regular test on ProductDao.findFirstNProduct(...)");
		List<Product> list = dao.findFirstNProduct(10);
		Assert.assertEquals(list.size(), 10);
		
		list = dao.findFirstNProduct(0);
		Assert.assertEquals(list.size(), 0);
	}
	
	@Test
	@Transactional
	@Rollback(true)
	public void testFindByPage() {
		logger.info("Running test on regular test on ProductDao.findByPage(...)");
		List<Product> list = dao.findByPage(1);
		Assert.assertEquals(list.size(), 100);
	}
}
