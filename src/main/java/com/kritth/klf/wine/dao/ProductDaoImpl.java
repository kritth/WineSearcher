package com.kritth.klf.wine.dao;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Repository;

import com.kritth.klf.wine.model.Product;

/**
 * Implementation of ProductDao interface
 * 
 * @author Kritth
 *
 */
@Repository
public class ProductDaoImpl extends HibernateSupport implements ProductDao {
	// Define the maximum page size for this class
	private static int PAGE_SIZE = 100;
	
	@Override
	public Product findById(int prodID) {
		return findById(Product.class, prodID);
	}

	@Override
	public List<Product> findFirstNProduct(int amount) {
		if (amount > 0) {
			return findResultFromTo(Product.class, 0, amount);
		} else {
			return new ArrayList<>();
		}
	}

	@Override
	public List<Product> findByPage(int page) {
		return findResultFromTo(Product.class, (page - 1) * PAGE_SIZE, PAGE_SIZE);
	}

}
