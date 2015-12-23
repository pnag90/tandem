package tandem.dao;

import java.io.Serializable;
import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.Hibernate;
import org.hibernate.SessionFactory;
import org.hibernate.proxy.HibernateProxy;
import org.springframework.orm.hibernate5.support.HibernateDaoSupport;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import tandem.utils.HibernateUtil;


@Transactional(propagation=Propagation.MANDATORY)
public class GenericDAOImpl<T, PK extends Serializable> extends HibernateDaoSupport implements GenericDAO<T, PK> {

	private Class<T> type;

	public GenericDAOImpl(SessionFactory sessionFactory, Class<T> type) {
		super.setSessionFactory(sessionFactory);
		this.type = type;
	}

	@Override
	@SuppressWarnings("unchecked")
	public PK create(T o) {
		return (PK) this.getSessionFactory().getCurrentSession().save(o);
	}

	@Override
	@SuppressWarnings("unchecked")
	@Transactional(propagation=Propagation.REQUIRED, readOnly=true)
	public T get(PK id) {
		T value = HibernateUtil.getSession().get(type, id);
		if (value == null) {
            return null;
        }

        if (value instanceof HibernateProxy) {
			Hibernate.initialize(value);
	        value = (T) ((HibernateProxy) value).getHibernateLazyInitializer().getImplementation();
        }
        return value;
	}

	@Override
	@SuppressWarnings("unchecked")
	@Transactional(propagation=Propagation.REQUIRED, readOnly=true)
	public List<T> getAll() {
		Criteria crit = HibernateUtil.getSession().createCriteria(type);
		return crit.list();
	}
	
	@Override
	public void createOrUpdate(T o) {
		/*if (o instanceof AbstractPersistentObject) {
			if (((AbstractPersistentObject) o).isCreation()) {
				HibernateUtil.getSession().saveOrUpdate(o);
			} else {
				HibernateUtil.getSession().merge(o);
			}
		} else {
			throw new RuntimeException("this method support only AbstractPersistentObject");
		}*/
	}


	@Override
	public void update(T o) {
		HibernateUtil.getSession().update(o);
	}

	@Override
	public void delete(T o) {
		HibernateUtil.getSession().delete(o);
	}

}