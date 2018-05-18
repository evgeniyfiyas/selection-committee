package by.grsu.fiyas.web.page.stats;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;
import java.util.stream.Collectors;

import javax.persistence.PersistenceException;

import org.apache.wicket.extensions.markup.html.form.DateTextField;
import org.apache.wicket.extensions.markup.html.repeater.util.SortableDataProvider;
import org.apache.wicket.extensions.yui.calendar.DatePicker;
import org.apache.wicket.markup.html.basic.Label;
import org.apache.wicket.markup.html.basic.MultiLineLabel;
import org.apache.wicket.markup.html.form.Form;
import org.apache.wicket.markup.html.form.SubmitLink;
import org.apache.wicket.markup.html.form.TextField;
import org.apache.wicket.markup.html.link.Link;
import org.apache.wicket.markup.repeater.Item;
import org.apache.wicket.markup.repeater.data.DataView;
import org.apache.wicket.model.CompoundPropertyModel;
import org.apache.wicket.model.IModel;
import org.apache.wicket.model.Model;
import org.apache.wicket.model.PropertyModel;

import by.grsu.fiyas.datamodel.AcademicSubjectWrapper;
import by.grsu.fiyas.datamodel.Faculty;
import by.grsu.fiyas.datamodel.Student;
import by.grsu.fiyas.service.StudentService;
import by.grsu.fiyas.service.impl.StudentServiceImpl;
import by.grsu.fiyas.web.component.extensions.NotificationPanel;
import by.grsu.fiyas.web.page.AbstractPage;
import by.grsu.fiyas.web.page.student.StudentEditPage;
import by.grsu.fiyas.web.page.student.StudentsPage;

public class StatsPageDetails extends AbstractPage {

	private Student student;
	private Faculty faculty;
	private StudentService studentService;
	private List<Student> students;
	private static String XML_FOLDER = "XMLDatabase";
	
	public StatsPageDetails(Faculty faculty) {
		super();
		this.faculty = faculty;		
		studentService = new StudentServiceImpl(XML_FOLDER);	
		List<Student> allStudents = studentService.getAll();
		students = allStudents.stream().filter(o -> (o.getFaculty().equals(this.faculty))).collect(Collectors.toList());
	}	
		
	@Override
	protected void onInitialize() {
		super.onInitialize();	
		
		add(new Label("facultyInfo", faculty.getName() + ": (" + students.size() + "/" + faculty.getSelectionPlan() + ")"));
		
		int limit = faculty.getSelectionPlan();
		students = students.stream().limit(limit).collect(Collectors.toList());
		Collections.sort(students);
		 
		StudentDataProvider studentDataProvider = new StudentDataProvider();
		
		DataView<Student> studentDataView = new DataView<Student>("rows", studentDataProvider) {
			@Override
			protected void populateItem(Item<Student> item) {
				Student student = item.getModelObject();
				item.add(new Label("name", student.getName()));
				item.add(new MultiLineLabel("marks", studentDataProvider.MarksRenderer(student)));
			}
		};
		add(studentDataView);		
	}
	
	private class StudentDataProvider extends SortableDataProvider<Student, Serializable> {
		@Override
		public Iterator<Student> iterator(long first, long count) {
			return students.iterator();
		}

		@Override
		public long size() {
			return students.size();
		}

		@Override
		public IModel<Student> model(Student object) {
			return new Model(object);
		}		
		
		private String MarksRenderer(Student student) {
			String result = "";
			result += "Certificate: " + student.getCertificate() + "\n";
			result += student.getMarks().get(0).getSubject().getName() + ": " + student.getMarks().get(0).getMark() + "\n";
			result += student.getMarks().get(1).getSubject().getName() + ": " + student.getMarks().get(1).getMark() + "\n";
			result += student.getMarks().get(2).getSubject().getName() + ": " + student.getMarks().get(2).getMark() + "\n";
			return result;
		}
	}
}
	
