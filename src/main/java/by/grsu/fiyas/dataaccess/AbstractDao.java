package by.grsu.fiyas.dataaccess;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;

import org.apache.commons.io.IOUtils;
import org.apache.commons.lang3.StringUtils;

import com.thoughtworks.xstream.XStream;
import com.thoughtworks.xstream.converters.extended.NamedMapConverter;

import by.grsu.fiyas.datamodel.AcademicSubject;
import by.grsu.fiyas.datamodel.Faculty;
import by.grsu.fiyas.datamodel.Student;
import by.grsu.fiyas.datamodel.AcademicSubjectWrapper;
import by.grsu.fiyas.datamodel.TrainingList;
import by.grsu.fiyas.datamodel.UserCredentials;
import by.grsu.fiyas.datamodel.UserProfile;
import by.grsu.fiyas.datamodel.UserRole;
import by.grsu.fiyas.table.AbstractTable;
import by.grsu.fiyas.table.AcademicSubjectTable;
import by.grsu.fiyas.table.FacultyTable;
import by.grsu.fiyas.table.StudentTable;
import by.grsu.fiyas.table.TrainingListTable;
import by.grsu.fiyas.table.UserCredentialsTable;
import by.grsu.fiyas.table.UserProfileTable;

public abstract class AbstractDao<T extends AbstractTable<E>, E> implements IXmlDao<E> {
	private final XStream xStream;
	private final String rootFolderPath;

	public AbstractDao(final String rootFolderPath) {
		super();
		this.rootFolderPath = rootFolderPath;
		final File rootFolder = new File(rootFolderPath);
		if (!rootFolder.exists()) {
			rootFolder.mkdirs();
		}
		xStream = new XStream();
		xStream.processAnnotations(new Class[] { getTableClass() });

		Class<?>[] classes = new Class[] { 
				AcademicSubject.class, Faculty.class, Student.class, TrainingList.class, 
				UserCredentials.class, UserProfile.class, AcademicSubjectWrapper.class,
				
				AcademicSubjectTable.class, FacultyTable.class, StudentTable.class, TrainingListTable.class,
				UserCredentialsTable.class, UserProfileTable.class,
		};
		XStream.setupDefaultSecurity(xStream);
		xStream.allowTypes(classes);
	}

	/**
	 * Reads file from FS and makes deserialization Xml->Java.
	 * 
	 * @param xml
	 * @return
	 */
	@SuppressWarnings("unchecked")
	protected T deserializeFromXml() {
		final String xml = readFromFile();
		if (StringUtils.isBlank(xml)) {
			try {
				return getTableClass().newInstance();
			} catch (final Exception e) {
				throw new RuntimeException(e);
			}
		}
		return (T) xStream.fromXML(xml);
	}

	/**
	 * Makes serialization Java->Xml and saves to FS.
	 * 
	 * @param table
	 * @return
	 */
	protected void serializeToXml(final T table) {
		final String xml = xStream.toXML(table);
		writeToFile(xml);
	}

	/**
	 * Writes file to the local FS. Uses
	 * by.grsu.pe.dataaccess.AbstractDao.getTableClass() to resolve filename.
	 * 
	 * @param xml
	 *            data to be written in file
	 */
	private void writeToFile(final String xml) {
		try {
			final File file = new File(getFileName());
			if (!file.exists()) {
				file.createNewFile();
			}
			IOUtils.write(xml, new FileOutputStream(file));
		} catch (final Exception e) {
			throw new RuntimeException(e);
		}
	}

	/**
	 * Reads file from the local FS. Uses
	 * by.grsu.pe.dataaccess.AbstractDao.getTableClass() to resolve filename.
	 * 
	 * @return XML data as String
	 */
	private String readFromFile() {
		final ByteArrayOutputStream output = new ByteArrayOutputStream();
		try {
			IOUtils.copy(new FileInputStream(getFileName()), output);
		} catch (final FileNotFoundException e) {
			return null;
		} catch (final IOException e) {
			throw new RuntimeException(e);
		}
		return new String(output.toByteArray());
	}

	private String getFileName() {
		return new File(getRootFolderPath(), getTableClass().getSimpleName() + ".xml").getAbsolutePath();
	}

	public String getRootFolderPath() {
		return rootFolderPath;
	}

	/**
	 * Will be used as ID generator (enough for training project).
	 * 
	 * @return
	 */
	protected Long getNextId() {
		return System.nanoTime();
	}

	protected abstract Class<T> getTableClass();
}
