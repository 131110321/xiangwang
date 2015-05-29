package org.sp.DAO;

import java.util.List;

import org.hibernate.LockOptions;
import org.hibernate.Query;
import org.hibernate.Transaction;
import org.hibernate.criterion.Example;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.sp.model.Goods;
import org.sp.model.Sale;

/**
 * A data access object (DAO) providing persistence and search support for Sale
 * entities. Transaction control of the save(), update() and delete() operations
 * can directly support Spring container-managed transactions or they can be
 * augmented to handle user-managed Spring transactions. Each of these methods
 * provides additional information for how to configure it for the desired type
 * of transaction control.
 * 
 * @see org.sp.DAO.Sale
 * @author MyEclipse Persistence Tools
 */
public class SaleDAO extends BaseHibernateDAO {
	private static final Logger log = LoggerFactory.getLogger(SaleDAO.class);
	// property constants
	public static final String SALE_QUANTITY = "saleQuantity";

	public void save(Sale transientInstance) {
		log.debug("saving Sale instance");
		try {
			Transaction tx = getSession().beginTransaction();
			Goods goods = transientInstance.getGoods();
			goods.setGoodsQuantity(goods.getGoodsQuantity() - transientInstance.getSaleQuantity());
			transientInstance.setGoods(goods);
			getSession().save(transientInstance);
			tx.commit();
			log.debug("save successful");
		} catch (RuntimeException re) {
			log.error("save failed", re);
			throw re;
		}
	}

	public void delete(Sale persistentInstance) {
		log.debug("deleting Sale instance");
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

	public Sale findById(java.lang.Integer id) {
		log.debug("getting Sale instance with id: " + id);
		try {
			Sale instance = (Sale) getSession().get("org.sp.model.Sale", id);
			return instance;
		} catch (RuntimeException re) {
			log.error("get failed", re);
			throw re;
		}
	}

	public List findByExample(Sale instance) {
		log.debug("finding Sale instance by example");
		try {
			List results = getSession().createCriteria("org.sp.model.Sale")
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
		log.debug("finding Sale instance with property: " + propertyName
				+ ", value: " + value);
		try {
			String queryString = "from Sale as model where model."
					+ propertyName + "= ?";
			Query queryObject = getSession().createQuery(queryString);
			queryObject.setParameter(0, value);
			return queryObject.list();
		} catch (RuntimeException re) {
			log.error("find by property name failed", re);
			throw re;
		}
	}

	public List findBySaleQuantity(Object saleQuantity) {
		return findByProperty(SALE_QUANTITY, saleQuantity);
	}

	public List findAll() {
		log.debug("finding all Sale instances");
		try {
			String queryString = "from Sale";
			Query queryObject = getSession().createQuery(queryString);
			return queryObject.list();
		} catch (RuntimeException re) {
			log.error("find all failed", re);
			throw re;
		}
	}

	public Sale merge(Sale detachedInstance) {
		log.debug("merging Sale instance");
		try {
			Transaction tx = getSession().beginTransaction();
			Sale result = (Sale) getSession().merge(detachedInstance);
			tx.commit();
			log.debug("merge successful");
			return result;
		} catch (RuntimeException re) {
			log.error("merge failed", re);
			throw re;
		}
	}

	public void attachDirty(Sale instance) {
		log.debug("attaching dirty Sale instance");
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

	public void attachClean(Sale instance) {
		log.debug("attaching clean Sale instance");
		try {
			getSession().buildLockRequest(LockOptions.NONE).lock(instance);
			log.debug("attach successful");
		} catch (RuntimeException re) {
			log.error("attach failed", re);
			throw re;
		}
	}
}