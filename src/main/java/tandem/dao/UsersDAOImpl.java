package tandem.dao;

import java.util.List;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Scope;
import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import tandem.model.Users;
import tandem.utils.HibernateUtil;

@Repository("UsersDAO")
@Scope("singleton")
public class UsersDAOImpl extends GenericDAOImpl<Users, String> implements UsersDAO {

	@Autowired
	public UsersDAOImpl(@Qualifier("sessionFactory") SessionFactory sessionFactory) {
		super(sessionFactory, Users.class);
	}
	
	private static final Logger log = LoggerFactory.getLogger(UsersDAOImpl.class);

	/**
	 * Find the chapter by number.
	 */
	@SuppressWarnings("unchecked")
	@Override
	@Transactional(propagation=Propagation.MANDATORY, readOnly=true)
	public List<Users> findById(String id) throws DataAccessException {
		try {
			StringBuffer hql = new StringBuffer(" from Users u ");
			hql.append(" where u.userId=:number ");
			Session session = this.getSessionFactory().getCurrentSession();
			session.beginTransaction();
			Query query = HibernateUtil.getSession().createQuery(hql.toString());
			//
			query.setString("number", id);
			List<Users> users = query.list();
			return users;
		} catch (Exception ex) {
			log.error("Erro findById", ex);
			throw ex;
		}
	}

	/*private static final Log log = LogFactory.getLog(UsersDAOImpl.class);

	//private final SessionFactory sessionFactory = getSessionFactory();
	//@Autowired
	//private SessionFactory sessionFactory = HibernateUtil.getSessionFactory();

	@Override
	public void persist(Users transientInstance) {
		log.debug("persisting Users instance");
		try {
			sessionFactory.getCurrentSession().persist(transientInstance);
			log.debug("persist successful");
		} catch (RuntimeException re) {
			log.error("persist failed", re);
			throw re;
		}
	}

	@Override
	public void attachDirty(Users instance) {
		log.debug("attaching dirty Users instance");
		try {
			sessionFactory.getCurrentSession().saveOrUpdate(instance);
			log.debug("attach successful");
		} catch (RuntimeException re) {
			log.error("attach failed", re);
			throw re;
		}
	}

	@Override
	public void attachClean(Users instance) {
		log.debug("attaching clean Users instance");
		try {
			sessionFactory.getCurrentSession().lock(instance, LockMode.NONE);
			log.debug("attach successful");
		} catch (RuntimeException re) {
			log.error("attach failed", re);
			throw re;
		}
	}

	@Override
	public void delete(Users persistentInstance) {
		log.debug("deleting Users instance");
		try {
			sessionFactory.getCurrentSession().delete(persistentInstance);
			log.debug("delete successful");
		} catch (RuntimeException re) {
			log.error("delete failed", re);
			throw re;
		}
	}

	@Override
	public Users merge(Users detachedInstance) {
		log.debug("merging Users instance");
		try {
			Users result = (Users) sessionFactory.getCurrentSession().merge(detachedInstance);
			log.debug("merge successful");
			return result;
		} catch (RuntimeException re) {
			log.error("merge failed", re);
			throw re;
		}
	}

	@Override
	public Users findById(java.lang.Integer id) {
		log.debug("getting Users instance with id: " + id);
		try {
			Users instance = (Users) sessionFactory.getCurrentSession().get("Users", id);
			if (instance == null) {
				log.debug("get successful, no instance found");
			} else {
				log.debug("get successful, instance found");
			}
			return instance;
		} catch (RuntimeException re) {
			log.error("get failed", re);
			throw re;
		}
	}

	@Override
	public List<Users> findByExample(Users instance) {
		log.debug("finding Users instance by example");
		try {
			List<Users> results = (List<Users>) sessionFactory.getCurrentSession().createCriteria("Users")
					.add(create(instance)).list();
			log.debug("find by example successful, result size: " + results.size());
			return results;
		} catch (RuntimeException re) {
			log.error("find by example failed", re);
			throw re;
		}
	}*/
}
