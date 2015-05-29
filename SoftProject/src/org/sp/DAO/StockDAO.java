package org.sp.DAO;

import java.util.List;

import org.hibernate.LockOptions;
import org.hibernate.Query;
import org.hibernate.Transaction;
import org.hibernate.criterion.Example;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.sp.model.Goods;
import org.sp.model.Stock;

/**
 * A data access object (DAO) providing persistence and search support for Stock
 * entities. Transaction control of the save(), update() and delete() operations
 * can directly support Spring container-managed transactions or they can be
 * augmented to handle user-managed Spring transactions. Each of these methods
 * provides additional information for how to configure it for the desired type
 * of transaction control.
 * 
 * @see org.sp.DAO.Stock
 * @author MyEclipse Persistence Tools
 */
public class StockDAO extends BaseHibernateDAO {
	private static final Logger log = LoggerFactory.getLogger(StockDAO.class);
	// property constants
	public static final String IN_PRICE = "inPrice";
	public static final String IN_QUANTITY = "inQuantity";

	public void save(Stock transientInstance) {
		log.debug("saving Stock instance");
		try {
			Transaction tx = getSession().beginTransaction();
//			getSession().save(transientInstance);
			Goods goods = transientInstance.getGoods();
			goods.setGoodsQuantity(goods.getGoodsQuantity() + transientInstance.getInQuantity());
			transientInstance.setGoods(goods);
			getSession().save(transientInstance);
			tx.commit();
			log.debug("save successful");
		} catch (RuntimeException re) {
			log.error("save failed", re);
			throw re;
		}
	}

	public void delete(Stock persistentInstance) {
		log.debug("deleting Stock instance");
		try {
			Transaction tx = getSession().beginTransaction();
			getSession().delete(persistentInstance);
			tx.commit();
			log.debug("delete successful");
		} catch (RuntimeException re) {
			log.error("delete failed", re);
			throw re;
		}
	}

	public Stock findById(java.lang.Integer id) {
		log.debug("getting Stock instance with id: " + id);
		try {
			Stock instance = (Stock) getSession().get("org.sp.model.Stock", id);
			return instance;
		} catch (RuntimeException re) {
			log.error("get failed", re);
			throw re;
		}
	}

	public List findByExample(Stock instance) {
		log.debug("finding Stock instance by example");
		try {
			List results = getSession().createCriteria("org.sp.model.Stock")
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
		log.debug("finding Stock instance with property: " + propertyName
				+ ", value: " + value);
		try {
			String queryString = "from Stock as model where model."
					+ propertyName + "= ?";
			Query queryObject = getSession().createQuery(queryString);
			queryObject.setParameter(0, value);
			return queryObject.list();
		} catch (RuntimeException re) {
			log.error("find by property name failed", re);
			throw re;
		}
	}

	public List findByInPrice(Object inPrice) {
		return findByProperty(IN_PRICE, inPrice);
	}

	public List findByInQuantity(Object inQuantity) {
		return findByProperty(IN_QUANTITY, inQuantity);
	}

	public List findAll() {
		log.debug("finding all Stock instances");
		try {
			String queryString = "from Stock";
			Query queryObject = getSession().createQuery(queryString);
			return queryObject.list();
		} catch (RuntimeException re) {
			log.error("find all failed", re);
			throw re;
		}
	}

	public Stock merge(Stock detachedInstance) {
		log.debug("merging Stock instance");
		try {
			Transaction tx = getSession().beginTransaction();
			Stock result = (Stock) getSession().merge(detachedInstance);
			tx.commit();
			log.debug("merge successful");
			return result;
		} catch (RuntimeException re) {
			log.error("merge failed", re);
			throw re;
		}
	}

	public void attachDirty(Stock instance) {
		log.debug("attaching dirty Stock instance");
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

	public void attachClean(Stock instance) {
		log.debug("attaching clean Stock instance");
		try {
			getSession().buildLockRequest(LockOptions.NONE).lock(instance);
			log.debug("attach successful");
		} catch (RuntimeException re) {
			log.error("attach failed", re);
			throw re;
		}
	}

}