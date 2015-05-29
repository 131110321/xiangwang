package org.sp.model;

/**
 * Goods entity. @author MyEclipse Persistence Tools
 */

public class Goods implements java.io.Serializable {

	// Fields

	private Integer goodsId;
	private String goodsName;
	private Float unitPrice;
	private Integer goodsQuantity;

	// Constructors

	/** default constructor */
	public Goods() {
	}

	/** full constructor */
	public Goods(String goodsName, Float unitPrice, Integer goodsQuantity) {
		this.goodsName = goodsName;
		this.unitPrice = unitPrice;
		this.goodsQuantity = goodsQuantity;
	}

	// Property accessors

	public Integer getGoodsId() {
		return this.goodsId;
	}

	public void setGoodsId(Integer goodsId) {
		this.goodsId = goodsId;
	}

	public String getGoodsName() {
		return this.goodsName;
	}

	public void setGoodsName(String goodsName) {
		this.goodsName = goodsName;
	}

	public Float getUnitPrice() {
		return this.unitPrice;
	}

	public void setUnitPrice(Float unitPrice) {
		this.unitPrice = unitPrice;
	}

	public Integer getGoodsQuantity() {
		return this.goodsQuantity;
	}

	public void setGoodsQuantity(Integer goodsQuantity) {
		this.goodsQuantity = goodsQuantity;
	}

}