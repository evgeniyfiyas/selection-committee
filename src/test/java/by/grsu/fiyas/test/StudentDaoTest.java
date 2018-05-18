package by.grsu.fiyas.test;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Test;

import by.grsu.fiyas.dataaccess.impl.StudentDao;
import by.grsu.fiyas.datamodel.AcademicSubject;
import by.grsu.fiyas.datamodel.Student;
import junit.framework.Assert;

public class StudentDaoTest {
	
	private static final String TEST_XML_FOLDER = "XMLDatabaseTest";
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
		newStudent.setCertificate(93);
		Map<AcademicSubject, Integer> marks = new HashMap<AcademicSubject, Integer>();
		
		AcademicSubject s1 = new AcademicSubject();
		s1.setName("Subject1");
		
		AcademicSubject s2 = new AcademicSubject();
		s2.setName("Subject2");
		
		AcademicSubject s3 = new AcademicSubject();
		s3.setName("Subject3");
		
		
		marks.put(s1, 30);
		marks.put(s2, 25);
		marks.put(s3, 11);		
		
		newStudent.setDateOfEnrollment(new Date());
	//	newStudent.setMarks(marks);
		newStudent.setDateOfEnrollment(new Date());
		studentDao.saveNew(newStudent);
		return newStudent;
	}
}
