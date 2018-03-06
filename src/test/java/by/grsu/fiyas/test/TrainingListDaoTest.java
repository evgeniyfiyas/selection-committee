package by.grsu.fiyas.test;

import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Test;

import by.grsu.fiyas.dataaccess.impl.TrainingListDao;
import by.grsu.fiyas.datamodel.TrainingList;
import junit.framework.Assert;

public class TrainingListDaoTest {
	private static final String TEST_XML_FOLDER = "testXmlFolder";
	private static TrainingListDao trainingListDao;

	@BeforeClass
	public static void createDao() {
		trainingListDao = new TrainingListDao(TEST_XML_FOLDER);
	}

	@AfterClass
	public static void deleteTestXmlData() {
		// write code to clean up test results from FS

	}

	@Test
	public void testAdd() {
		System.out.println("Start 'save' test for TrainingList");
		final TrainingList newTrainingList = saveNewTrainingList();
		Assert.assertNotNull(trainingListDao.get(newTrainingList.getId()));
	}

	@Test
	public void testDelete() {
		System.out.println("Start 'delete' test for TrainingList");
		final TrainingList newTrainingList = saveNewTrainingList();
		trainingListDao.delete(newTrainingList.getId());
		Assert.assertNull(trainingListDao.get(newTrainingList.getId()));
	}

	@Test
	public void testGetAll() {
		System.out.println("Start 'getAll' test for TrainingList");
		final int initialRowsCount = trainingListDao.getAll().size();
		saveNewTrainingList();
		Assert.assertEquals(trainingListDao.getAll().size(), initialRowsCount + 1);
	}

	private TrainingList saveNewTrainingList() {
		final TrainingList newTrainingList = new TrainingList();
		trainingListDao.saveNew(newTrainingList);
		return newTrainingList;
	}
}
