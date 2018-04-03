package by.grsu.fiyas.dataaccess.impl;

import java.io.Serializable;
import java.util.List;

import by.grsu.fiyas.dataaccess.AbstractDao;
import by.grsu.fiyas.table.AcademicSubjectTable;
import by.grsu.fiyas.datamodel.AcademicSubject;

public class AcademicSubjectDao extends AbstractDao<AcademicSubjectTable, AcademicSubject> implements Serializable {

	public AcademicSubjectDao(final String rootFolderPath) {
		super(rootFolderPath);
	}

	@Override
	public void saveNew(AcademicSubject newAcademicSubject) {
		newAcademicSubject.setId(getNextId());
		final AcademicSubjectTable academicSubjectTable = deserializeFromXml();
		academicSubjectTable.getRows().add(newAcademicSubject);
		serializeToXml(academicSubjectTable);
	}

	@Override
	public void update(AcademicSubject entity) {
		final AcademicSubjectTable academicSubjectTable = deserializeFromXml();
		for (final AcademicSubject row : academicSubjectTable.getRows()) {
			if (row.getId().equals(entity.getId())) {
				row.setName(entity.getName());
				break;
			}
		}
		serializeToXml(academicSubjectTable);
	}

	@Override
	public AcademicSubject get(Long id) {
		final AcademicSubjectTable academicSubjectTable = deserializeFromXml();
		for (final AcademicSubject row : academicSubjectTable.getRows()) {
			if (row.getId().equals(id)) {
				return row;
			}
		}
		return null;
	}

	@Override
	public List<AcademicSubject> getAll() {
		final AcademicSubjectTable academicSubjectTable = deserializeFromXml();
		return academicSubjectTable.getRows();
	}

	@Override
	public void delete(Long id) {
		final AcademicSubjectTable academicSubjectTable = deserializeFromXml();
		AcademicSubject toBeDeleted = null;
		for (final AcademicSubject row : academicSubjectTable.getRows()) {
			if (row.getId().equals(id)) {
				toBeDeleted = row;
				break;
			}
		}
		academicSubjectTable.getRows().remove(toBeDeleted);
		serializeToXml(academicSubjectTable);
	}

	@Override
	protected Class<AcademicSubjectTable> getTableClass() {
		return AcademicSubjectTable.class;
	}

}
