package by.grsu.fiyas.table;

import java.util.ArrayList;
import java.util.List;
import by.grsu.fiyas.datamodel.Faculty;

public class FacultyTable extends AbstractTable<Faculty> {

	private List<Faculty> rows;

	@Override
	public List<Faculty> getRows() {
		if (rows == null) {
			rows = new ArrayList<Faculty>();
		}
		return rows;
	}

	@Override
	public void setRows(List<Faculty> rows) {
		this.rows = rows;
	}

}