package com.kritth.klf.wine.model;

import java.io.Serializable;
import javax.persistence.*;

import org.json.simple.JSONObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.kritth.klf.wine.variables.JSON_SIZE;

import java.util.Date;

/**
 * The persistent class for the Products database table.
 * 
 * This class is auto-generated by JPA tools from eclipse.
 * Changed some of the parameter as it can be null value.
 * 
 * @author Kritth
 */
@Entity
@Table(name="Products")
@NamedQuery(name="Product.findAll", query="SELECT p FROM Product p")
public class Product implements JSONConvertable, Serializable {
	private static final Logger logger = LoggerFactory.getLogger(Product.class);
	private static final long serialVersionUID = 4064409106429094689L;

	@Id
	private int prodID;

	private byte prodAvailable;

	private Double prodBottleCharge;

	private Double prodBottleChargePerson;

	private int prodColorID;

	private String prodComment;

	@Temporal(TemporalType.DATE)
	private Date prodDateBuy;

	private String prodFormat;

	private String prodIDSupplier;

	private Double prodLabelCharge;

	private String prodName;

	private String prodNoRequest;

	private String prodNum;

	private int prodPack;

	private Double prodPriceBuy;

	private int prodQtyBuy;

	private Integer prodRegionID;

	private Double prodSellPrice;

	private byte prodSoldOut;

	public Product() {
	}

	public int getProdID() {
		return this.prodID;
	}

	public void setProdID(int prodID) {
		this.prodID = prodID;
	}

	public byte getProdAvailable() {
		return this.prodAvailable;
	}

	public void setProdAvailable(byte prodAvailable) {
		this.prodAvailable = prodAvailable;
	}

	public double getProdBottleCharge() {
		return this.prodBottleCharge;
	}

	public void setProdBottleCharge(double prodBottleCharge) {
		this.prodBottleCharge = prodBottleCharge;
	}

	public double getProdBottleChargePerson() {
		return this.prodBottleChargePerson;
	}

	public void setProdBottleChargePerson(double prodBottleChargePerson) {
		this.prodBottleChargePerson = prodBottleChargePerson;
	}

	public int getProdColorID() {
		return this.prodColorID;
	}

	public void setProdColorID(int prodColorID) {
		this.prodColorID = prodColorID;
	}

	public String getProdComment() {
		return this.prodComment;
	}

	public void setProdComment(String prodComment) {
		this.prodComment = prodComment;
	}

	public Date getProdDateBuy() {
		return this.prodDateBuy;
	}

	public void setProdDateBuy(Date prodDateBuy) {
		this.prodDateBuy = prodDateBuy;
	}

	public String getProdFormat() {
		return this.prodFormat;
	}

	public void setProdFormat(String prodFormat) {
		this.prodFormat = prodFormat;
	}

	public String getProdIDSupplier() {
		return this.prodIDSupplier;
	}

	public void setProdIDSupplier(String prodIDSupplier) {
		this.prodIDSupplier = prodIDSupplier;
	}

	public double getProdLabelCharge() {
		return this.prodLabelCharge;
	}

	public void setProdLabelCharge(double prodLabelCharge) {
		this.prodLabelCharge = prodLabelCharge;
	}

	public String getProdName() {
		return this.prodName;
	}

	public void setProdName(String prodName) {
		this.prodName = prodName;
	}

	public String getProdNoRequest() {
		return this.prodNoRequest;
	}

	public void setProdNoRequest(String prodNoRequest) {
		this.prodNoRequest = prodNoRequest;
	}

	public String getProdNum() {
		return this.prodNum;
	}

	public void setProdNum(String prodNum) {
		this.prodNum = prodNum;
	}

	public int getProdPack() {
		return this.prodPack;
	}

	public void setProdPack(int prodPack) {
		this.prodPack = prodPack;
	}

	public double getProdPriceBuy() {
		return this.prodPriceBuy;
	}

	public void setProdPriceBuy(double prodPriceBuy) {
		this.prodPriceBuy = prodPriceBuy;
	}

	public int getProdQtyBuy() {
		return this.prodQtyBuy;
	}

	public void setProdQtyBuy(int prodQtyBuy) {
		this.prodQtyBuy = prodQtyBuy;
	}

	public int getProdRegionID() {
		return this.prodRegionID;
	}

	public void setProdRegionID(int prodRegionID) {
		this.prodRegionID = prodRegionID;
	}

	public double getProdSellPrice() {
		return this.prodSellPrice;
	}

	public void setProdSellPrice(double prodSellPrice) {
		this.prodSellPrice = prodSellPrice;
	}

	public byte getProdSoldOut() {
		return this.prodSoldOut;
	}

	public void setProdSoldOut(byte prodSoldOut) {
		this.prodSoldOut = prodSoldOut;
	}

	// JSON field name
	public static final String _JSON_ID = "id"
			, _JSON_NAME = "name"
			, _JSON_PRICE_BUY = "price_buy"
			, _JSON_PRICE_SELL = "price_sell"
			, _JSON_DATE_BUY = "date_buy";
	
	@SuppressWarnings("unchecked")
	@Override
	public JSONObject toJSON(JSON_SIZE size) {
		JSONObject result = new JSONObject();
		
		try {
			switch(size) {
			case LARGE: // For returning 5 properties (3 more to other two)
				result.put(_JSON_PRICE_BUY, this.getProdPriceBuy());
				result.put(_JSON_DATE_BUY, this.getProdDateBuy());
				result.put(_JSON_ID, this.getProdID());
			case MEDIUM: // For returning name and price (1 more to small)
				result.put(_JSON_PRICE_SELL, this.getProdSellPrice());
			case SMALL:	// For returning only name
				result.put(_JSON_NAME, this.getProdName());
				return result;
			}
		} catch (NullPointerException ex) {
			logger.debug("Unacceptable input", ex);
		}
		
		return null;
	}

}