package by.grsu.fiyas.test;

import java.util.Date;

import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Test;

import by.grsu.fiyas.dataaccess.impl.UserCredentialsDao;
import by.grsu.fiyas.dataaccess.impl.UserProfileDao;
import by.grsu.fiyas.datamodel.UserCredentials;
import by.grsu.fiyas.datamodel.UserProfile;
import junit.framework.Assert;

public class UserProfileDaoTest {
	
	private static final String TEST_XML_FOLDER = "XMLDatabaseTest";
	private static UserProfileDao userProfileDao;

	// Declare test variables below
	private static final Date TEST_CREATED_ON = new Date();
	private static final UserCredentials TEST_CREDENTIALS = new UserCredentialsDao(TEST_XML_FOLDER).get(50428338853524L); // Creating profile for specific user ID from database
	private static final String TEST_FIRSTNAME = "John";
	private static final String TEST_LASTNAME = "Doe";
	
	@BeforeClass
	public static void createDao() {
		userProfileDao = new UserProfileDao(TEST_XML_FOLDER);
	}

	@AfterClass
	public static void deleteTestXmlData() {
		// write code to clean up test results from FS

	}

	@Test
	public void testAdd() {
		System.out.println("Start 'save' test for user profile");
		final UserProfile newUserProfile = saveNewUserProfile();
		Assert.assertNotNull(userProfileDao.get(newUserProfile.getId()));
		userProfileDao.delete(newUserProfile.getId());
	}

//	@Test
//	public void testDelete() {
//		System.out.println("Start 'delete' test for profile");
//		final UserProfile newUserProfile = saveNewUserProfile();
//		userProfileDao.delete(newUserProfile.getId());
//		Assert.assertNull(userProfileDao.get(newUserProfile.getId()));
//	}

	@Test
	public void testGetAll() {
		System.out.println("Start 'getAll' test for profile");
		final int initialRowsCount = userProfileDao.getAll().size();
		saveNewUserProfile();
		Assert.assertEquals(userProfileDao.getAll().size(), initialRowsCount + 1);
	}

	private UserProfile saveNewUserProfile() {
		final UserProfile newUserProfile = new UserProfile();
		newUserProfile.setCreated(TEST_CREATED_ON);
		newUserProfile.setCredentials(TEST_CREDENTIALS);
		newUserProfile.setFirstName(TEST_FIRSTNAME);
		newUserProfile.setLastName(TEST_LASTNAME);
		
		userProfileDao.saveNew(newUserProfile);
		return newUserProfile;
	}
}