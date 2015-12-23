package tandem.dao;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import tandem.model.OauthProvider;
import tandem.model.OauthProviderId;

/**
 * Home object for domain model class OauthProvider.
 * @see .OauthProvider
 * @author Hibernate Tools
 */
public class OauthProviderDAO {

	private static final Log log = LogFactory.getLog(OauthProviderDAO.class);

	@PersistenceContext
	private EntityManager entityManager;

	public void persist(OauthProvider transientInstance) {
		log.debug("persisting OauthProvider instance");
		try {
			entityManager.persist(transientInstance);
			log.debug("persist successful");
		} catch (RuntimeException re) {
			log.error("persist failed", re);
			throw re;
		}
	}

	public void remove(OauthProvider persistentInstance) {
		log.debug("removing OauthProvider instance");
		try {
			entityManager.remove(persistentInstance);
			log.debug("remove successful");
		} catch (RuntimeException re) {
			log.error("remove failed", re);
			throw re;
		}
	}

	public OauthProvider merge(OauthProvider detachedInstance) {
		log.debug("merging OauthProvider instance");
		try {
			OauthProvider result = entityManager.merge(detachedInstance);
			log.debug("merge successful");
			return result;
		} catch (RuntimeException re) {
			log.error("merge failed", re);
			throw re;
		}
	}

	public OauthProvider findById(OauthProviderId id) {
		log.debug("getting OauthProvider instance with id: " + id);
		try {
			OauthProvider instance = entityManager.find(OauthProvider.class, id);
			log.debug("get successful");
			return instance;
		} catch (RuntimeException re) {
			log.error("get failed", re);
			throw re;
		}
	}
}
