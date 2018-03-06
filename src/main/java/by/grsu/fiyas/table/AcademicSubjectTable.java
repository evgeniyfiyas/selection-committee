package by.grsu.fiyas.table;

import java.util.ArrayList;
import java.util.List;

import by.grsu.fiyas.datamodel.AcademicSubject;

public class AcademicSubjectTable extends AbstractTable<AcademicSubject> {

	private List<AcademicSubject> rows;

	@Override
	public List<AcademicSubject> getRows() {
		if (rows == null) {
			rows = new ArrayList<AcademicSubject>();
		}
		return rows;
	}

	@Override
	public void setRows(List<AcademicSubject> rows) {
		this.rows = rows;
	}

}