package by.grsu.fiyas.test;

import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Test;

import by.grsu.fiyas.dataaccess.impl.AcademicSubjectDao;
import by.grsu.fiyas.datamodel.AcademicSubject;
import junit.framework.Assert;

public class AcademicSubjectDaoTest {

	private static final String TEST_XML_FOLDER = "testXmlFolder";
	private static AcademicSubjectDao academicSubjectDao;

	@BeforeClass
	public static void createDao() {
		academicSubjectDao = new AcademicSubjectDao(TEST_XML_FOLDER);
	}

	@AfterClass
	public static void deleteTestXmlData() {
		// write code to clean up test results from FS

	}

	@Test
	public void testAdd() {
		System.out.println("Start 'save' test for AcademicSubject");
		final AcademicSubject newAcademicSubject = saveNewAcademicSubject();
		Assert.assertNotNull(academicSubjectDao.get(newAcademicSubject.getId()));
	}

	@Test
	public void testDelete() {
		System.out.println("Start 'delete' test for AcademicSubject");
		final AcademicSubject newAcademicSubject = saveNewAcademicSubject();
		academicSubjectDao.delete(newAcademicSubject.getId());
		Assert.assertNull(academicSubjectDao.get(newAcademicSubject.getId()));
	}

	@Test
	public void testGetAll() {
		System.out.println("Start 'getAll' test for AcademicSubject");
		final int initialRowsCount = academicSubjectDao.getAll().size();
		saveNewAcademicSubject();
		Assert.assertEquals(academicSubjectDao.getAll().size(), initialRowsCount + 1);
	}

	private AcademicSubject saveNewAcademicSubject() {
		final AcademicSubject newAcademicSubject = new AcademicSubject();
		newAcademicSubject.setName("Computer Science");
		academicSubjectDao.saveNew(newAcademicSubject);
		return newAcademicSubject;
	}
}