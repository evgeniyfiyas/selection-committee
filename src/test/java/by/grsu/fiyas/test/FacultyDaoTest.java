package by.grsu.fiyas.test;

import java.awt.List;
import java.util.Arrays;

import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Test;

import by.grsu.fiyas.dataaccess.impl.FacultyDao;
import by.grsu.fiyas.datamodel.AcademicSubject;
import by.grsu.fiyas.datamodel.Faculty;
import junit.framework.Assert;

public class FacultyDaoTest {

	private static final String TEST_XML_FOLDER = "XMLDatabaseTest";
	private static FacultyDao facultyDao;

	@BeforeClass
	public static void createDao() {
		facultyDao = new FacultyDao(TEST_XML_FOLDER);
	}

	@AfterClass
	public static void deleteTestXmlData() {
		// write code to clean up test results from FS

	}

	@Test
	public void testAdd() {
		System.out.println("Start 'save' test for Faculty");
		final Faculty newFaculty = saveNewFaculty();
		Assert.assertNotNull(facultyDao.get(newFaculty.getId()));
	}

	@Test
	public void testDelete() {
		System.out.println("Start 'delete' test for Faculty");
		final Faculty newFaculty = saveNewFaculty();
		facultyDao.delete(newFaculty.getId());
		Assert.assertNull(facultyDao.get(newFaculty.getId()));
	}

	@Test
	public void testGetAll() {
		System.out.println("Start 'getAll' test for Faculty");
		final int initialRowsCount = facultyDao.getAll().size();
		saveNewFaculty();
		Assert.assertEquals(facultyDao.getAll().size(), initialRowsCount + 1);
	}

	private Faculty saveNewFaculty() {
		final Faculty newFaculty = new Faculty();
		newFaculty.setName("Faculty of math and CS");
		newFaculty.setSelectionPlan(23);
		newFaculty.setIsEnabled(true);
		
		AcademicSubject s1 = new AcademicSubject();
		s1.setName("Math");
		
		AcademicSubject s2 = new AcademicSubject();
		s2.setName("Physics");
		
		AcademicSubject s3 = new AcademicSubject();
		s3.setName("Russian language");
		
		newFaculty.setSubjects(Arrays.asList(s1, s2, s3));
		facultyDao.saveNew(newFaculty);
		return newFaculty;
	}

}
