package tandem.dao;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import tandem.model.Profile;

/**
 * Home object for domain model class Profile.
 * @see .Profile
 * @author Hibernate Tools
 */
public class ProfileDAO {

	private static final Log log = LogFactory.getLog(ProfileDAO.class);

	@PersistenceContext
	private EntityManager entityManager;

	public void persist(Profile transientInstance) {
		log.debug("persisting Profile instance");
		try {
			entityManager.persist(transientInstance);
			log.debug("persist successful");
		} catch (RuntimeException re) {
			log.error("persist failed", re);
			throw re;
		}
	}

	public void remove(Profile persistentInstance) {
		log.debug("removing Profile instance");
		try {
			entityManager.remove(persistentInstance);
			log.debug("remove successful");
		} catch (RuntimeException re) {
			log.error("remove failed", re);
			throw re;
		}
	}

	public Profile merge(Profile detachedInstance) {
		log.debug("merging Profile instance");
		try {
			Profile result = entityManager.merge(detachedInstance);
			log.debug("merge successful");
			return result;
		} catch (RuntimeException re) {
			log.error("merge failed", re);
			throw re;
		}
	}

	public Profile findById(Long id) {
		log.debug("getting Profile instance with id: " + id);
		try {
			Profile instance = entityManager.find(Profile.class, id);
			log.debug("get successful");
			return instance;
		} catch (RuntimeException re) {
			log.error("get failed", re);
			throw re;
		}
	}
}
