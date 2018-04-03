package by.grsu.fiyas.table;

import java.util.ArrayList;
import java.util.List;

import by.grsu.fiyas.datamodel.UserProfile;
import by.grsu.fiyas.table.AbstractTable;

public class UserProfileTable extends AbstractTable<UserProfile> {
	
	private List<UserProfile> rows;

	@Override
	public List<UserProfile> getRows() {
		if (rows == null) {
			rows = new ArrayList<UserProfile>();
		}
		return rows;
	}

	@Override
	public void setRows(List<UserProfile> rows) {
		this.rows = rows;
	}
}