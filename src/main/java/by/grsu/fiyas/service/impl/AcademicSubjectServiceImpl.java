package by.grsu.fiyas.service.impl;

import java.util.List;

import by.grsu.fiyas.dataaccess.impl.AcademicSubjectDao;
import by.grsu.fiyas.datamodel.AcademicSubject;
import by.grsu.fiyas.service.AcademicSubjectService;

public class AcademicSubjectServiceImpl extends AcademicSubjectDao implements AcademicSubjectService {

	private AcademicSubjectDao dao;
	
	public AcademicSubjectServiceImpl(String rootFolderPath) {
		super(rootFolderPath);
		dao = new AcademicSubjectDao(rootFolderPath);
	}

	@Override
	public AcademicSubject get(Long id) {
		return dao.get(id);
	}

	@Override
	public void update(AcademicSubject entity) {
		dao.update(entity);
	}
	
	@Override
	public void delete(Long id) {
		dao.delete(id);
	}

	@Override
	public void saveOrUpdate(AcademicSubject entity) {
		if (entity.getId() == null) {
			dao.saveNew(entity);
		} else {
			dao.update(entity);
		}		
	}

	@Override
	public void saveNew(AcademicSubject newFaculty) {
		dao.saveNew(newFaculty);
	}
	
	@Override
	public List<AcademicSubject> getAll() {
		return dao.getAll();
	}
}