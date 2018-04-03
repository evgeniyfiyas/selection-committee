package by.grsu.fiyas.dataaccess.impl;

import java.io.Serializable;
import java.util.List;

import by.grsu.fiyas.dataaccess.AbstractDao;
import by.grsu.fiyas.datamodel.Faculty;
import by.grsu.fiyas.table.FacultyTable;

public class FacultyDao extends AbstractDao<FacultyTable, Faculty> implements Serializable {

	public FacultyDao(final String rootFolderPath) {
		super(rootFolderPath);
	}

	@Override
	public void saveNew(Faculty newFaculty) {
		newFaculty.setId(getNextId());
		final FacultyTable facultyTable = deserializeFromXml();
		facultyTable.getRows().add(newFaculty);
		serializeToXml(facultyTable);
	}

	@Override
	public void update(Faculty entity) {
		final FacultyTable facultyTable = deserializeFromXml();
		for (final Faculty row : facultyTable.getRows()) {
			if (row.getId().equals(entity.getId())) {
				row.setName(entity.getName());
				row.setSelectionPlan(entity.getSelectionPlan());
				row.setSubjects(entity.getSubjects());
				row.setIsEnabled(entity.getIsEnabled());
				break;
			}
		}
		serializeToXml(facultyTable);
	}

	@Override
	public Faculty get(Long id) {
		final FacultyTable facultyTable = deserializeFromXml();
		for (final Faculty row : facultyTable.getRows()) {
			if (row.getId().equals(id)) {
				return row;
			}
		}
		return null;
	}

	@Override
	public List<Faculty> getAll() {
		final FacultyTable facultyTable = deserializeFromXml();
		return facultyTable.getRows();
	}

	@Override
	public void delete(Long id) {
		final FacultyTable facultyTable = deserializeFromXml();
		Faculty toBeDeleted = null;
		for (final Faculty row : facultyTable.getRows()) {
			if (row.getId().equals(id)) {
				toBeDeleted = row;
				break;
			}
		}
		facultyTable.getRows().remove(toBeDeleted);
		serializeToXml(facultyTable);
	}

	@Override
	protected Class<FacultyTable> getTableClass() {
		return FacultyTable.class;
	}

}
