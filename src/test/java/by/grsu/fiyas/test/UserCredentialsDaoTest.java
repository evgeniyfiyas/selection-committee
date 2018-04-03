package by.grsu.fiyas.test;

import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Test;

import by.grsu.fiyas.dataaccess.impl.UserCredentialsDao;
import by.grsu.fiyas.datamodel.UserCredentials;
import by.grsu.fiyas.datamodel.UserRole;
import junit.framework.Assert;

public class UserCredentialsDaoTest {
	
	private static final String TEST_XML_FOLDER = "XMLDatabaseTest";
	private static UserCredentialsDao userCredentialsDao;

	// Declare test variables below
	private static final String TEST_EMAIL = "admin";
	private static final String TEST_PASSWORD = "admin";
	private static final UserRole TEST_ROLE = UserRole.admin;
	
	
	@BeforeClass
	public static void createDao() {
		userCredentialsDao = new UserCredentialsDao(TEST_XML_FOLDER);
	}

	@AfterClass
	public static void deleteTestXmlData() {
		// write code to clean up test results from FS

	}

	@Test
	public void testAdd() {
		System.out.println("Start 'save' test for user credentials");
		final UserCredentials newCredentials = saveNewUserCredentials();
		Assert.assertNotNull(userCredentialsDao.get(newCredentials.getId()));
		userCredentialsDao.delete(newCredentials.getId());
	}

	@Test
	public void testDelete() {
		System.out.println("Start 'delete' test for credentials");
		final UserCredentials newCredentials = saveNewUserCredentials();
		userCredentialsDao.delete(newCredentials.getId());
		Assert.assertNull(userCredentialsDao.get(newCredentials.getId()));
	}

	@Test
	public void testGetAll() {
		System.out.println("Start 'getAll' test for credentials");
		final int initialRowsCount = userCredentialsDao.getAll().size();
		saveNewUserCredentials();
		Assert.assertEquals(userCredentialsDao.getAll().size(), initialRowsCount + 1);
	}

	private UserCredentials saveNewUserCredentials() {
		final UserCredentials newCredentials = new UserCredentials();
		newCredentials.setEmail(TEST_EMAIL);
		newCredentials.setPassword(TEST_PASSWORD);
		newCredentials.setRole(TEST_ROLE);
		
		userCredentialsDao.saveNew(newCredentials);
		return newCredentials;
	}
}
