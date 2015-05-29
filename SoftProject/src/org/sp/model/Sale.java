package org.sp.model;

import java.util.Iterator;

import org.sp.DAO.SaleDAO;

/**
 * Sale entity. @author MyEclipse Persistence Tools
 */

public class Sale implements java.io.Serializable {

	// Fields

	private Integer saleId;
	private Goods goods;
	private Integer saleQuantity;

	// Constructors

	/** default constructor */
	public Sale() {
	}

	/** full constructor */
	public Sale(Goods goods, Integer saleQuantity) {
		this.goods = goods;
		this.saleQuantity = saleQuantity;
	}

	// Property accessors

	public Integer getSaleId() {
		return this.saleId;
	}

	public void setSaleId(Integer saleId) {
		this.saleId = saleId;
	}

	public Goods getGoods() {
		return this.goods;
	}

	public void setGoods(Goods goods) {
		this.goods = goods;
	}

	public Integer getSaleQuantity() {
		return this.saleQuantity;
	}

	public void setSaleQuantity(Integer saleQuantity) {
		this.saleQuantity = saleQuantity;
	}
	
	//���������ܶ�
	public Float grossSale(){
		SaleDAO saleDAO = new SaleDAO();
		Iterator<Sale> sIterator = saleDAO.findByProperty("goods", goods).iterator();
		
		float total = 0;
		while(sIterator.hasNext()){
			total += sIterator.next().saleQuantity * goods.getUnitPrice();
		}
		return total;
	}
	
	//�������������
	public Integer getTotalQuantity(){
		SaleDAO saleDAO = new SaleDAO();
		Iterator<Sale> sIterator = saleDAO.findByProperty("goods", goods).iterator();
		
		int total = 0;
		while(sIterator.hasNext()){
			total += sIterator.next().saleQuantity;
		}
		return total;
	}

}