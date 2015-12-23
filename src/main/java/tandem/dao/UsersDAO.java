package tandem.dao;

import java.util.List;

import org.springframework.dao.DataAccessException;

import tandem.model.Users;

public interface  UsersDAO {

	/*public void persist(Users transientInstance);

	public void attachDirty(Users instance);

	public void attachClean(Users instance);

	public void delete(Users persistentInstance);

	public Users merge(Users detachedInstance);

	public Users findById(java.lang.Integer id);

	public List<Users> findByExample(Users instance);

	@Autowired
	SessionFactory sessionFactory =  HibernateUtil.getSessionFactory();*/
	
	public List<Users> findById(String id) throws DataAccessException;

}
