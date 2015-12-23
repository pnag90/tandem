package tandem.dao;

import java.io.Serializable;
import java.util.List;

public interface GenericDAO<T, PK extends Serializable> {
	PK create(T persistentObject);

	T get(PK id);

	List<T> getAll();

	void update(T persistentObject);
	
	void createOrUpdate(T persistentObject);

	void delete(T persistentObject);
}