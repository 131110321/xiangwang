package org.sp.DAO;

import org.sp.model.Goods;
import org.sp.model.Sale;
import org.sp.model.Stock;

public class Test {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		GoodsDAO gdDao = new GoodsDAO();
		StockDAO stockDAO = new StockDAO();
		SaleDAO saleDAO = new SaleDAO();
		
		//gdDao.save(new Goods("²ÝÄàÂí",(float)15,1));
		//stockDAO.save(new Stock(gdDao.findById(1),(float)8,10));
		//saleDAO.delete(saleDAO.findById(1));
		//gdDao.delete(gdDao.findById(1));
		System.out.println(saleDAO.findById(2).getTotalQuantity());
		

	}

}
