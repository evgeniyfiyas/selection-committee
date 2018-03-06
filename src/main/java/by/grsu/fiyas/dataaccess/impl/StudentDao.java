package by.grsu.fiyas.dataaccess.impl;

import java.io.Serializable;
import java.util.List;

import by.grsu.fiyas.dataaccess.AbstractDao;
import by.grsu.fiyas.datamodel.Student;
import by.grsu.fiyas.table.StudentTable;

public class StudentDao extends AbstractDao<StudentTable, Student> {

	public StudentDao(final String rootFolderPath) {
		super(rootFolderPath);
	}

	@Override
	public void saveNew(Student newStudent) {
		newStudent.setId(getNextId());
		final StudentTable studentTable = deserializeFromXml();
		studentTable.getRows().add(newStudent);
		serializeToXml(studentTable);
	}

	@Override
	public void update(Student entity) {
		final StudentTable studentTable = deserializeFromXml();
		for (final Student row : studentTable.getRows()) {
			if (row.getId().equals(entity.getId())) {
				row.setName(entity.getName());
				row.setAverageMark(entity.getAverageMark());
				row.setDateOfEnrollment(entity.getDateOfEnrollment());
				row.setFaculty(entity.getFaculty());
				row.setMarks(entity.getMarks());
				break;
			}
		}
		serializeToXml(studentTable);
	}

	@Override
	public Student get(Serializable id) {
		final StudentTable studentTable = deserializeFromXml();
		for (final Student row : studentTable.getRows()) {
			if (row.getId().equals(id)) {
				return row;
			}
		}
		return null;
	}

	@Override
	public List<Student> getAll() {
		final StudentTable studentTable = deserializeFromXml();
		return studentTable.getRows();
	}

	@Override
	public void delete(Serializable id) {
		final StudentTable studentTable = deserializeFromXml();
		Student toBeDeleted = null;
		for (final Student row : studentTable.getRows()) {
			if (row.getId().equals(id)) {
				toBeDeleted = row;
				break;
			}
		}
		studentTable.getRows().remove(toBeDeleted);
		serializeToXml(studentTable);
	}

	@Override
	protected Class<StudentTable> getTableClass() {
		return StudentTable.class;
	}
	
}