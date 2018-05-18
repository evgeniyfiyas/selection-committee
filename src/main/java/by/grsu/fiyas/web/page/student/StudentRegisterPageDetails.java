package by.grsu.fiyas.web.page.student;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.apache.wicket.extensions.markup.html.form.DateTextField;
import org.apache.wicket.extensions.yui.calendar.DatePicker;
import org.apache.wicket.markup.html.basic.Label;
import org.apache.wicket.markup.html.form.Form;
import org.apache.wicket.markup.html.form.SubmitLink;
import org.apache.wicket.markup.html.form.TextField;
import org.apache.wicket.markup.html.link.Link;
import org.apache.wicket.markup.html.panel.FeedbackPanel;
import org.apache.wicket.model.CompoundPropertyModel;
import org.apache.wicket.model.IModel;
import org.apache.wicket.model.PropertyModel;

import by.grsu.fiyas.datamodel.AcademicSubject;
import by.grsu.fiyas.datamodel.AcademicSubjectWrapper;
import by.grsu.fiyas.datamodel.Faculty;
import by.grsu.fiyas.datamodel.Student;
import by.grsu.fiyas.service.StudentService;
import by.grsu.fiyas.service.impl.StudentServiceImpl;
import by.grsu.fiyas.web.component.extensions.NotificationPanel;
import by.grsu.fiyas.web.page.AbstractPage;
import by.grsu.fiyas.web.page.faculty.FacultiesPage;

public class StudentRegisterPageDetails extends AbstractPage {

	private Student student;
	private Faculty faculty;
	private StudentService studentService;
	private static String XML_FOLDER = "XMLDatabase";
	
	public StudentRegisterPageDetails(Faculty faculty) {
		super();
		this.faculty = faculty;
		student = new Student();
		
		List<AcademicSubjectWrapper> marks = new ArrayList<AcademicSubjectWrapper>();
		marks.add(new AcademicSubjectWrapper(faculty.getSubjects().get(0), 0));
		marks.add(new AcademicSubjectWrapper(faculty.getSubjects().get(1), 0));
		marks.add(new AcademicSubjectWrapper(faculty.getSubjects().get(2), 0));	
		
		student.setMarks(marks);
		studentService = new StudentServiceImpl(XML_FOLDER);
	}	
		
	@Override
	protected void onInitialize() {
		super.onInitialize();
		
		student.setFaculty(this.faculty);
		
		Label title = new Label("title", faculty.getName());
        add(title);
		
		Form<Student> form = new StudentForm<Student>("form", new CompoundPropertyModel<Student>(student));
        add(form);
        
		TextField<String> name = new TextField<String>("name");
		name.setRequired(true);
		form.add(name);
        
		Label subj1lbl = new Label("subj1lbl", faculty.getSubjects().get(0).getName());
		TextField<Integer> subj1 = new TextField<Integer>("subj1", new PropertyModel<Integer>(student.getMarks().get(0), "mark"));
		subj1.setRequired(true);
		form.add(subj1);
		form.add(subj1lbl);

		Label subj2lbl = new Label("subj2lbl", faculty.getSubjects().get(1).getName());
		TextField<Integer> subj2 = new TextField<Integer>("subj2", new PropertyModel<Integer>(student.getMarks().get(1), "mark"));
		subj2.setRequired(true);
		form.add(subj2);
		form.add(subj2lbl);

		Label subj3lbl = new Label("subj3lbl", faculty.getSubjects().get(2).getName());
		TextField<Integer> subj3 = new TextField<Integer>("subj3", new PropertyModel<Integer>(student.getMarks().get(2), "mark"));
		subj3.setRequired(true);
		form.add(subj3);
		form.add(subj3lbl);        

		TextField<Integer> certificate = new TextField<Integer>("certificate");
		certificate.setRequired(true);
		form.add(certificate);

		DateTextField date = new DateTextField("date", new PropertyModel<>(student, "dateOfEnrollment"));
		date.add(new DatePicker());
		date.setRequired(true);
		form.add(date);
		
		add(new NotificationPanel("feedback"));
		
		SubmitLink saveBtn = new SubmitLink("save") {
			@Override
			public void onSubmit() {
				super.onSubmit();	
				
			    StudentRegisterPage page = new StudentRegisterPage();
				page.success("You've been registered.");
				studentService.saveOrUpdate(student);
				setResponsePage(page);
			}
		};
		form.setDefaultButton(saveBtn);
		form.add(saveBtn);
		
		Link cancelBtn = new Link("cancel") {
			@Override
			public void onClick() {
				setResponsePage(new StudentRegisterPage());
			}
		};
		form.add(cancelBtn);
		
	}
	
	private class StudentForm<T> extends Form<T> {
		public StudentForm(String id, IModel<T> model) {
			super(id, model);
		}
	}
}
