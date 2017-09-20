package com.kritth.klf.wine.controller;

import java.util.Optional;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.kritth.klf.wine.service.ProductService;
import com.kritth.klf.wine.variables.JSON_SIZE;

/**
 * Controller for getting wine products
 * 
 * @author Kritth
 *
 */
@RestController
@RequestMapping("/wine")
public class ProductController {
	@Autowired
	private ProductService prodService;
	
	/**
	 * Fetch the value from the service by id having size as optional parameter
	 * 
	 * @param prodID
	 * @param size
	 * @return Response to client. BAD_REQUEST if value cannot be processed.
	 */
	@RequestMapping(value= {"/find/{id}", "/find/{id}/{size}"},
			method=RequestMethod.GET,
			produces=MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<JSONObject> getById(
			@PathVariable("id") int prodID, 
			@PathVariable(name="size") Optional<String> size) {
		String toCheck = size.isPresent() ? size.get() : "normal";
		switch (toCheck) {
		case "normal":
			JSONObject obj = prodService.findById(prodID, JSON_SIZE.SMALL);
			return obj != null ? new ResponseEntity<>(obj, HttpStatus.OK) : new ResponseEntity<>(HttpStatus.BAD_REQUEST);
		case "bonus":
			return new ResponseEntity<>(prodService.findById(prodID, JSON_SIZE.LARGE), HttpStatus.OK);
		default:
			return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
		}
	}
	
	/**
	 * Fetch the value from service by the amount to get
	 * 
	 * @param amount
	 * @return Response to client. BAD_REQUEST if value cannot be processed.
	 */
	@RequestMapping(value="/first/{amount}",
			method=RequestMethod.GET,
			produces=MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<JSONArray> getFirstN(@PathVariable("amount") int amount) {
		JSONArray result = prodService.findFirstN(amount);
		return result != null ? new ResponseEntity<>(result, HttpStatus.OK) : new ResponseEntity<>(HttpStatus.BAD_REQUEST);
	}
	
	/**
	 * Fetch the value from service by the page number
	 * For this function, I assume that:
	 * - Page starts from 1
	 * - Page 1 = 0 - 99
	 * - Page 2 = 100 - 199
	 * - Page 3 = 200 - 299
	 * and so on
	 * 
	 * @param page_number
	 * @return Response to client. BAD_REQUEST if value cannot be processed.
	 */
	@RequestMapping(value="/page/{page_number}",
			method=RequestMethod.GET,
			produces=MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<JSONArray> getByPage(@PathVariable("page_number") int page_number) {
		JSONArray result = prodService.findByPage(page_number);
		return result != null ? new ResponseEntity<>(result, HttpStatus.OK) : new ResponseEntity<>(HttpStatus.BAD_REQUEST);
	}
}
