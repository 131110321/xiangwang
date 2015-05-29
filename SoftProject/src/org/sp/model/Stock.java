package org.sp.model;

import java.util.Iterator;

import org.sp.DAO.StockDAO;

/**
 * Stock entity. @author MyEclipse Persistence Tools
 */

public class Stock implements java.io.Serializable {

	// Fields

	private Integer inId;
	private Goods goods;
	private Float inPrice;
	private Integer inQuantity;

	// Constructors

	/** default constructor */
	public Stock() {
	}

	/** full constructor */
	public Stock(Goods goods, Float inPrice, Integer inQuantity) {
		this.goods = goods;
		this.inPrice = inPrice;
		this.inQuantity = inQuantity;
	}

	// Property accessors

	public Integer getInId() {
		return this.inId;
	}

	public void setInId(Integer inId) {
		this.inId = inId;
	}

	public Goods getGoods() {
		return this.goods;
	}

	public void setGoods(Goods goods) {
		this.goods = goods;
	}

	public Float getInPrice() {
		return this.inPrice;
	}

	public void setInPrice(Float inPrice) {
		this.inPrice = inPrice;
	}

	public Integer getInQuantity() {
		return this.inQuantity;
	}

	public void setInQuantity(Integer inQuantity) {
		this.inQuantity = inQuantity;
	}

	//获得一次总的进价
	public float totalInCost(){
		StockDAO stockDAO = new StockDAO();
		Iterator<Stock> sIterator = stockDAO.findByProperty("goods", goods).iterator();
		
		float total = 0;
		while(sIterator.hasNext()){
			total += sIterator.next().inPrice * inQuantity;
		}
		return total;
	}
	
	//获得总的进货数量
	public Integer getTotalQuantity(){
		StockDAO stockDAO = new StockDAO();
		Iterator<Stock> sIterator = stockDAO.findByProperty("goods", goods).iterator();
		
		int total = 0;
		while(sIterator.hasNext()){
			total += sIterator.next().getInQuantity();
		}
		return total;
	}
}