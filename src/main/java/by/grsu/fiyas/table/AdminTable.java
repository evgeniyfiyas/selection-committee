package by.grsu.fiyas.table;

import java.util.ArrayList;
import java.util.List;
import by.grsu.fiyas.datamodel.Admin;

public class AdminTable extends AbstractTable<Admin> {

	private List<Admin> rows;

	@Override
	public List<Admin> getRows() {
		if (rows == null) {
			rows = new ArrayList<Admin>();
		}
		return rows;
	}

	@Override
	public void setRows(List<Admin> rows) {
		this.rows = rows;
	}

}