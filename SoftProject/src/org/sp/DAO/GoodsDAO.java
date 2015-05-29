package org.sp.DAO;

import java.util.Iterator;
import java.util.List;

import org.hibernate.LockOptions;
import org.hibernate.Query;
import org.hibernate.Transaction;
import org.hibernate.criterion.Example;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.sp.model.Goods;
import org.sp.model.Sale;
import org.sp.model.Stock;

/**
 * A data access object (DAO) providing persistence and search support for Goods
 * entities. Transaction control of the save(), update() and delete() operations
 * can directly support Spring container-managed transactions or they can be
 * augmented to handle user-managed Spring transactions. Each of these methods
 * provides additional information for how to configure it for the desired type
 * of transaction control.
 * 
 * @see org.sp.DAO.Goods
 * @author MyEclipse Persistence Tools
 */
public class GoodsDAO extends BaseHibernateDAO {
	private static final Logger log = LoggerFactory.getLogger(GoodsDAO.class);
	// property constants
	public static final String GOODS_NAME = "goodsName";
	public static final String UNIT_PRICE = "unitPrice";
	public static final String GOODS_QUANTITY = "goodsQuantity";

	public void save(Goods transientInstance) {
		log.debug("saving Goods instance");
		try {
			Transaction tx = getSession().beginTransaction();
			getSession().save(transientInstance);
			tx.commit();
			log.debug("save successful");
		} catch (RuntimeException re) {
			log.error("save failed", re);
			throw re;
		}
	}

	public void delete(Goods persistentInstance) {
		log.debug("deleting Goods instance");
		try {
			Transaction tx = getSession().beginTransaction();
			
			SaleDAO saleDAO = new SaleDAO();
			StockDAO stockDAO = new StockDAO();
			
			Iterator<Sale> iterator = saleDAO.findByProperty("goods", persistentInstance).iterator();
			Iterator<Stock> stockIterator = stockDAO.findByProperty("goods", persistentInstance).iterator();
			
			getSession().delete(persistentInstance);
			tx.commit();
			
			while(iterator.hasNext()){
				saleDAO.delete(iterator.next());
			}
			
			while(stockIterator.hasNext()){
				stockDAO.delete(stockIterator.next());
			}
			
			log.debug("delete successful");
		} catch (RuntimeException re) {
			log.error("delete failed", re);
			throw re;
		}
	}

	public Goods findById(java.lang.Integer id) {
		log.debug("getting Goods instance with id: " + id);
		try {
			Goods instance = (Goods) getSession().get("org.sp.model.Goods", id);
			return instance;
		} catch (RuntimeException re) {
			log.error("get failed", re);
			throw re;
		}
	}

	public List findByExample(Goods instance) {
		log.debug("finding Goods instance by example");
		try {
			List results = getSession().createCriteria("org.sp.model.Goods")
					.add(Example.create(instance)).list();
			log.debug("find by example successful, result size: "
					+ results.size());
			return results;
		} catch (RuntimeException re) {
			log.error("find by example failed", re);
			throw re;
		}
	}

	public List findByProperty(String propertyName, Object value) {
		log.debug("finding Goods instance with property: " + propertyName
				+ ", value: " + value);
		try {
			String queryString = "from Goods as model where model."
					+ propertyName + "= ?";
			Query queryObject = getSession().createQuery(queryString);
			queryObject.setParameter(0, value);
			return queryObject.list();
		} catch (RuntimeException re) {
			log.error("find by property name failed", re);
			throw re;
		}
	}

	public List findByGoodsName(Object goodsName) {
		return findByProperty(GOODS_NAME, goodsName);
	}

	public List findByUnitPrice(Object unitPrice) {
		return findByProperty(UNIT_PRICE, unitPrice);
	}

	public List findByGoodsQuantity(Object goodsQuantity) {
		return findByProperty(GOODS_QUANTITY, goodsQuantity);
	}

	public List findAll() {
		log.debug("finding all Goods instances");
		try {
			String queryString = "from Goods";
			Query queryObject = getSession().createQuery(queryString);
			return queryObject.list();
		} catch (RuntimeException re) {
			log.error("find all failed", re);
			throw re;
		}
	}

	public Goods merge(Goods detachedInstance) {
		log.debug("merging Goods instance");
		try {
			Transaction tx = getSession().beginTransaction();
			Goods result = (Goods) getSession().merge(detachedInstance);
			tx.commit();
			log.debug("merge successful");
			return result;
		} catch (RuntimeException re) {
			log.error("merge failed", re);
			throw re;
		}
	}

	public void attachDirty(Goods instance) {
		log.debug("attaching dirty Goods instance");
		try {
			Transaction tx = getSession().beginTransaction();
			getSession().saveOrUpdate(instance);
			tx.commit();
			log.debug("attach successful");
		} catch (RuntimeException re) {
			log.error("attach failed", re);
			throw re;
		}
	}

	public void attachClean(Goods instance) {
		log.debug("attaching clean Goods instance");
		try {
			getSession().buildLockRequest(LockOptions.NONE).lock(instance);
			log.debug("attach successful");
		} catch (RuntimeException re) {
			log.error("attach failed", re);
			throw re;
		}
	}
}