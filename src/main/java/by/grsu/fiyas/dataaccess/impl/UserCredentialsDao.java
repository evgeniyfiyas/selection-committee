package by.grsu.fiyas.dataaccess.impl;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import by.grsu.fiyas.dataaccess.AbstractDao;
import by.grsu.fiyas.datamodel.UserCredentials;
import by.grsu.fiyas.table.UserCredentialsTable;

public class UserCredentialsDao extends AbstractDao<UserCredentialsTable, UserCredentials> implements Serializable {
	public UserCredentialsDao(final String rootFolderPath) {
		super(rootFolderPath);
	}

	@Override
	public void saveNew(UserCredentials newUserCredentials) {
		newUserCredentials.setId(getNextId());
		final UserCredentialsTable userCredentialsTable = deserializeFromXml();
		userCredentialsTable.getRows().add(newUserCredentials);
		serializeToXml(userCredentialsTable);
	}

	@Override
	public void update(UserCredentials entity) {
		final UserCredentialsTable userCredentialsTable = deserializeFromXml();
		for (final UserCredentials row : userCredentialsTable.getRows()) {
			if (row.getId().equals(entity.getId())) {
				row.setEmail(entity.getEmail());
				row.setPassword(entity.getPassword());
				row.setRole(entity.getRole());
				break;
			}
		}
		serializeToXml(userCredentialsTable);
	}

	@Override
	public UserCredentials get(Long id) {
		final UserCredentialsTable userCredentialsTable = deserializeFromXml();
		for (final UserCredentials row : userCredentialsTable.getRows()) {
			if (row.getId().equals(id)) {
				return row;
			}
		}
		return null;
	}

	@Override
	public List<UserCredentials> getAll() {
		final UserCredentialsTable userCredentialsTable = deserializeFromXml();
		return userCredentialsTable.getRows();
	}

	@Override
	public void delete(Long id) {
		final UserCredentialsTable userCredentialsTable = deserializeFromXml();
		UserCredentials toBeDeleted = null;
		for (final UserCredentials row : userCredentialsTable.getRows()) {
			if (row.getId().equals(id)) {
				toBeDeleted = row;
				break;
			}
		}
		userCredentialsTable.getRows().remove(toBeDeleted);
		serializeToXml(userCredentialsTable);
	}

	@Override
	protected Class<UserCredentialsTable> getTableClass() {
		return UserCredentialsTable.class;
	}

	public UserCredentials find(String userName, String password) {
		
		List<UserCredentials> userCredentialsList = getAll();
		List<UserCredentials> allItems = new ArrayList<UserCredentials>();
		
		for (UserCredentials userCredentials : userCredentialsList) {
			boolean isEmailEqual = userCredentials.getEmail().equals(userName);
			boolean isPasswordEqual = userCredentials.getPassword().equals(password);
			if (isEmailEqual && isPasswordEqual) {
				allItems.add(userCredentials);
			}
		}
		if (allItems.isEmpty()) {
			return null;
		} else if (allItems.size() == 1) {
			return allItems.get(0);
		} else {
			throw new IllegalArgumentException("More than 1 user found!");
		}
	}
}
