package by.grsu.fiyas.service.impl;

import java.util.List;

import by.grsu.fiyas.dataaccess.impl.FacultyDao;
import by.grsu.fiyas.datamodel.Faculty;
import by.grsu.fiyas.service.FacultyService;

public class FacultyServiceImpl extends FacultyDao implements FacultyService {

	private FacultyDao dao;
	
	public FacultyServiceImpl(String rootFolderPath) {
		super(rootFolderPath);
		dao = new FacultyDao(rootFolderPath);
	}

	@Override
	public Faculty get(Long id) {
		return dao.get(id);		
	}

	@Override
	public void update(Faculty entity) {
		dao.update(entity);
	}
	
	@Override
	public void delete(Long id) {
		dao.delete(id);
	}

	@Override
	public void saveOrUpdate(Faculty entity) {
		if (entity.getId() == null) {
			dao.saveNew(entity);
		} else {
			dao.update(entity);
		}		
	}

	@Override
	public void saveNew(Faculty newFaculty) {
		dao.saveNew(newFaculty);
	}
	
	@Override
	public List<Faculty> getAll() {
		return dao.getAll();
	}
}
