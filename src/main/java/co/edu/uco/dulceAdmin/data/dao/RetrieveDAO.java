package co.edu.uco.dulceAdmin.data.dao;

import java.util.List;

public interface RetrieveDAO<E, ID> {
	
	List<E> findAll();
	
	List<E> findByFilter(E filterEntity);
	
	E findById(ID id);
	
}
