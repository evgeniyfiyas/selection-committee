package by.grsu.fiyas.web.page.student.panel;

import java.io.Serializable;
import java.util.Iterator;

import javax.persistence.PersistenceException;

import org.apache.wicket.authorization.Action;
import org.apache.wicket.authroles.authorization.strategies.role.annotations.AuthorizeAction;
import org.apache.wicket.extensions.markup.html.repeater.util.SortableDataProvider;
import org.apache.wicket.markup.html.basic.Label;
import org.apache.wicket.markup.html.basic.MultiLineLabel;
import org.apache.wicket.markup.html.form.CheckBox;
import org.apache.wicket.markup.html.link.Link;
import org.apache.wicket.markup.html.panel.Panel;
import org.apache.wicket.markup.repeater.Item;
import org.apache.wicket.markup.repeater.data.DataView;
import org.apache.wicket.model.IModel;
import org.apache.wicket.model.Model;
import org.apache.wicket.model.PropertyModel;

import by.grsu.fiyas.datamodel.AcademicSubject;
import by.grsu.fiyas.datamodel.Faculty;
import by.grsu.fiyas.datamodel.Student;
import by.grsu.fiyas.service.FacultyService;
import by.grsu.fiyas.service.StudentService;
import by.grsu.fiyas.service.impl.FacultyServiceImpl;
import by.grsu.fiyas.service.impl.StudentServiceImpl;
import by.grsu.fiyas.web.page.student.StudentEditPage;
import by.grsu.fiyas.web.page.student.StudentsPage;


public class StudentListPanel extends Panel {
	
	private StudentService studentService;
	
	public StudentListPanel(String id, String rootFolderPath) {
		super(id);
		studentService = new StudentServiceImpl(rootFolderPath);
		StudentDataProvider studentDataProvider = new StudentDataProvider();
		
		DataView<Student> studentDataView = new DataView<Student>("rows", studentDataProvider) {
			@Override
			protected void populateItem(Item<Student> item) {
				Student student = item.getModelObject();
				item.add(new InvisibleLabel("id", student.getId()));
				item.add(new Label("name", student.getName()));
				item.add(new Label("dateOfEnrollment", student.getDateOfEnrollment()));
				item.add(new MultiLineLabel("marks", studentDataProvider.MarksRenderer(student)));
				item.add(new Label("faculty", student.getFaculty().getName()));
								
				item.add(new InvisibleLink<Void>("edit-link") {
					@Override
					public void onClick() {
						setResponsePage(new StudentEditPage(student));
					}
				});

				item.add(new InvisibleLink<Void>("delete-link") {
					@Override
					public void onClick() {
						StudentsPage page = new StudentsPage();
						try {
							studentService.delete(student.getId());
							page.success("Student removed.");
						} catch (PersistenceException e) {
							page.error("Error removing student.");
						} finally {
							setResponsePage(page);
						} 
					}
				});
			}
		};
		add(studentDataView);
	}	
	
	
	private class StudentDataProvider extends SortableDataProvider<Student, Serializable> {

		@Override
		public Iterator<Student> iterator(long first, long count) {
			return studentService.getAll().iterator();
		}

		@Override
		public long size() {
			return studentService.getAll().size();
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

	@AuthorizeAction(roles = { "admin" }, action = Action.RENDER)
	private class InvisibleLink<Void> extends Link<Void> {

		public InvisibleLink(String id) {
			super(id);
		}

		@Override
		public void onClick() {
		}
	}

	@AuthorizeAction(roles = { "admin" }, action = Action.RENDER)
	private class InvisibleLabel extends Label {

		public InvisibleLabel(String id, Serializable label) {
			super(id, label);
		}
	}

}
