package by.grsu.fiyas.service.impl;

import java.util.List;

import by.grsu.fiyas.dataaccess.impl.FacultyDao;
import by.grsu.fiyas.dataaccess.impl.StudentDao;
import by.grsu.fiyas.datamodel.Faculty;
import by.grsu.fiyas.datamodel.Student;
import by.grsu.fiyas.service.StudentService;

public class StudentServiceImpl extends StudentDao implements StudentService {

	private StudentDao dao;
	
	public StudentServiceImpl(String rootFolderPath) {
		super(rootFolderPath);
		dao = new StudentDao(rootFolderPath);
	}

	@Override
	public Student get(Long id) {
		return dao.get(id);		
	}

	@Override
	public void update(Student entity) {
		dao.update(entity);
	}
	
	@Override
	public void delete(Long id) {
		dao.delete(id);
	}

	@Override
	public void saveOrUpdate(Student entity) {
		if (entity.getId() == null) {
			dao.saveNew(entity);
		} else {
			dao.update(entity);
		}		
	}

	@Override
	public void saveNew(Student newStudent) {
		dao.saveNew(newStudent);
	}
	
	@Override
	public List<Student> getAll() {
		return dao.getAll();
	}

}
