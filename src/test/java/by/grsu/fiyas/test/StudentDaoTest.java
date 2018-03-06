package by.grsu.fiyas.test;

import java.util.Date;

import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Test;

import by.grsu.fiyas.dataaccess.impl.StudentDao;
import by.grsu.fiyas.datamodel.Student;
import junit.framework.Assert;

public class StudentDaoTest {
	
	private static final String TEST_XML_FOLDER = "testXmlFolder";
	private static StudentDao studentDao;

	@BeforeClass
	public static void createDao() {
		studentDao = new StudentDao(TEST_XML_FOLDER);
	}

	@AfterClass
	public static void deleteTestXmlData() {
		// write code to clean up test results from FS

	}

	@Test
	public void testAdd() {
		System.out.println("Start 'save' test for Student");
		final Student newStudent = saveNewStudent();
		Assert.assertNotNull(studentDao.get(newStudent.getId()));
	}

	@Test
	public void testDelete() {
		System.out.println("Start 'delete' test for Student");
		final Student newStudent = saveNewStudent();
		studentDao.delete(newStudent.getId());
		Assert.assertNull(studentDao.get(newStudent.getId()));
	}

	@Test
	public void testGetAll() {
		System.out.println("Start 'getAll' test for Student");
		final int initialRowsCount = studentDao.getAll().size();
		saveNewStudent();
		Assert.assertEquals(studentDao.getAll().size(), initialRowsCount + 1);
	}

	private Student saveNewStudent() {
		final Student newStudent = new Student();
		newStudent.setName("Fiyas Yauheni");
		newStudent.setAverageMark(93);
		newStudent.setDateOfEnrollment(new Date());
		studentDao.saveNew(newStudent);
		return newStudent;
	}
}
