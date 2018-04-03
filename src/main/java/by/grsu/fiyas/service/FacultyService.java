package by.grsu.fiyas.service;

import java.util.List;

import by.grsu.fiyas.datamodel.Faculty;

public interface FacultyService {

	void saveNew(Faculty entity);

	void update(Faculty entity);

	Faculty get(Long id);

	List<Faculty> getAll();

	void delete(Long id);

	void saveOrUpdate(Faculty entity);
	
}
