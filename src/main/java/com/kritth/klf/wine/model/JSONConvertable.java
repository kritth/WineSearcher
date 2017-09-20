package com.kritth.klf.wine.model;

import org.json.simple.JSONObject;

import com.kritth.klf.wine.variables.JSON_SIZE;

/**
 * This interface defines any model that can be converted
 * into smaller JSON object instead of containing full object.
 * 
 * @author Kritth
 *
 */
public interface JSONConvertable {
	/**
	 * Convert the object to JSON by specified size
	 * 
	 * @param size Size of the JSON Object
	 * @return JSON object created
	 */
	JSONObject toJSON(JSON_SIZE size);
}
