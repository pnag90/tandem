package tandem.dao;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import tandem.model.Authorities;
import tandem.model.AuthoritiesId;

/**
 * Home object for domain model class Authorities.
 * @see .Authorities
 * @author Hibernate Tools
 */
public class AuthoritiesDAO {

	private static final Log log = LogFactory.getLog(AuthoritiesDAO.class);

	@PersistenceContext
	private EntityManager entityManager;

	public void persist(Authorities transientInstance) {
		log.debug("persisting Authorities instance");
		try {
			entityManager.persist(transientInstance);
			log.debug("persist successful");
		} catch (RuntimeException re) {
			log.error("persist failed", re);
			throw re;
		}
	}

	public void remove(Authorities persistentInstance) {
		log.debug("removing Authorities instance");
		try {
			entityManager.remove(persistentInstance);
			log.debug("remove successful");
		} catch (RuntimeException re) {
			log.error("remove failed", re);
			throw re;
		}
	}

	public Authorities merge(Authorities detachedInstance) {
		log.debug("merging Authorities instance");
		try {
			Authorities result = entityManager.merge(detachedInstance);
			log.debug("merge successful");
			return result;
		} catch (RuntimeException re) {
			log.error("merge failed", re);
			throw re;
		}
	}

	public Authorities findById(AuthoritiesId id) {
		log.debug("getting Authorities instance with id: " + id);
		try {
			Authorities instance = entityManager.find(Authorities.class, id);
			log.debug("get successful");
			return instance;
		} catch (RuntimeException re) {
			log.error("get failed", re);
			throw re;
		}
	}
}
