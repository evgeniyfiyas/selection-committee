package by.grsu.fiyas.service;

import java.util.List;

import by.grsu.fiyas.datamodel.Student;

public interface StudentService {

	void saveNew(Student entity);

	void update(Student entity);

	Student get(Long id);

	List<Student> getAll();

	void delete(Long id);

	void saveOrUpdate(Student entity);

}
