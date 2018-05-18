package by.grsu.fiyas.web.page.student;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import org.apache.wicket.extensions.markup.html.form.DateTextField;
import org.apache.wicket.extensions.yui.calendar.DatePicker;
import org.apache.wicket.markup.html.form.DropDownChoice;
import org.apache.wicket.markup.html.form.Form;
import org.apache.wicket.markup.html.form.SubmitLink;
import org.apache.wicket.markup.html.form.TextField;
import org.apache.wicket.markup.html.link.Link;
import org.apache.wicket.markup.html.panel.Fragment;
import org.apache.wicket.model.CompoundPropertyModel;
import org.apache.wicket.model.IModel;
import org.apache.wicket.model.PropertyModel;

import by.grsu.fiyas.datamodel.AcademicSubject;
import by.grsu.fiyas.datamodel.AcademicSubjectWrapper;
import by.grsu.fiyas.datamodel.Faculty;
import by.grsu.fiyas.datamodel.Student;
import by.grsu.fiyas.service.AcademicSubjectService;
import by.grsu.fiyas.service.FacultyService;
import by.grsu.fiyas.service.StudentService;
import by.grsu.fiyas.service.impl.AcademicSubjectServiceImpl;
import by.grsu.fiyas.service.impl.FacultyServiceImpl;
import by.grsu.fiyas.service.impl.StudentServiceImpl;
import by.grsu.fiyas.web.common.renderer.AcademicSubjectChoiceRenderer;
import by.grsu.fiyas.web.common.renderer.FacultyChoiceRenderer;
import by.grsu.fiyas.web.component.extensions.NotificationPanel;
import by.grsu.fiyas.web.page.AbstractPage;

public class StudentEditPage extends AbstractPage {

	private Student student;
	private StudentService studentService;
	private AcademicSubjectService academicSubjectService;
	private FacultyService facultyService;
	private static String XML_FOLDER = "XMLDatabase";
	
	public StudentEditPage() {
		super();
		this.student = new Student();
		studentService = new StudentServiceImpl(XML_FOLDER);
		academicSubjectService = new AcademicSubjectServiceImpl(XML_FOLDER);
		facultyService = new FacultyServiceImpl(XML_FOLDER);
		List<AcademicSubjectWrapper> marks = new ArrayList<AcademicSubjectWrapper>();
		AcademicSubject first = academicSubjectService.getAll().get(0);
		marks.add(new AcademicSubjectWrapper(first, 0));
		marks.add(new AcademicSubjectWrapper(first, 0));
		marks.add(new AcademicSubjectWrapper(first, 0));
		student.setMarks(marks);		
	}

	public StudentEditPage(Student student) {
		super();
		this.student = student;
		academicSubjectService = new AcademicSubjectServiceImpl(XML_FOLDER);
		studentService = new StudentServiceImpl(XML_FOLDER);
		facultyService = new FacultyServiceImpl(XML_FOLDER);
	}

	@Override
	protected void onInitialize() {
		super.onInitialize();

		Fragment updateLabel = new Fragment("update-label", "update-label", this);
		add(updateLabel);
		updateLabel.setVisible(false);
		
		Fragment createLabel = new Fragment("create-label", "create-label", this);
		add(createLabel);
		createLabel.setVisible(false);
		
		if (student.getName() == null)
		{
			createLabel.setVisible(true);
		}
		else
		{
			updateLabel.setVisible(true);
		}
				
		Form<Student> form = new StudentForm<Student>("form", new CompoundPropertyModel<Student>(student));
		add(form);

		TextField<String> nameField = new TextField<>("name");
		nameField.setRequired(true);
		form.add(nameField);
		
		DateTextField date = new DateTextField("date", new PropertyModel<>(student, "dateOfEnrollment"));
		date.add(new DatePicker());
		date.setRequired(true);
		form.add(date);
		
		TextField<Integer> certificate = new TextField<>("certificate");
		certificate.setRequired(true);
		form.add(certificate);
		
		List<AcademicSubject> subjectsList = academicSubjectService.getAll();

		DropDownChoice<AcademicSubject> subject1 = new DropDownChoice<>("subject1", new PropertyModel<>(student.getMarks().get(0), "subject"),subjectsList,AcademicSubjectChoiceRenderer.INSTANCE);
		form.add(subject1);
		DropDownChoice<AcademicSubject> subject2 = new DropDownChoice<>("subject2", new PropertyModel<>(student.getMarks().get(1), "subject"),subjectsList,AcademicSubjectChoiceRenderer.INSTANCE);
		form.add(subject2);
		DropDownChoice<AcademicSubject> subject3 = new DropDownChoice<>("subject3", new PropertyModel<>(student.getMarks().get(2), "subject"),subjectsList,AcademicSubjectChoiceRenderer.INSTANCE);
		form.add(subject3);
		
		TextField<Integer> subject1mark = new TextField<>("subject1mark", new PropertyModel<>(student.getMarks().get(0), "mark"));
		subject1mark.setRequired(true);
		form.add(subject1mark);
		
		TextField<Integer> subject2mark = new TextField<>("subject2mark", new PropertyModel<>(student.getMarks().get(1), "mark"));
		subject2mark.setRequired(true);
		form.add(subject2mark);
		
		TextField<Integer> subject3mark = new TextField<>("subject3mark", new PropertyModel<>(student.getMarks().get(2), "mark"));
		subject3mark.setRequired(true);
		form.add(subject3mark);
		
		List<Faculty> facultiesList = facultyService.getAll();
		
		DropDownChoice<Faculty> faculty = new DropDownChoice<>("faculty", new PropertyModel<>(student, "faculty"),facultiesList,FacultyChoiceRenderer.INSTANCE);
		form.add(faculty);
		
		add(new NotificationPanel("feedback"));
		
		SubmitLink saveBtn = new SubmitLink("save") {
			@Override
			public void onSubmit() {
				super.onSubmit();	
				
				StudentsPage page = new StudentsPage();				
				if (createLabel.isVisible())
				{
					page.success("Student created.");
				}
				else
				{
					page.success("Student updated.");
				}
				studentService.saveOrUpdate(student);
				setResponsePage(page);
			}
		};
		form.setDefaultButton(saveBtn);
		form.add(saveBtn);
		
		form.add(new Link("cancel") {
			@Override
			public void onClick() {
				setResponsePage(new StudentsPage());
			}
		});
	}

	private class StudentForm<T> extends Form<T> {

		public StudentForm(String id, IModel<T> model) {
			super(id, model);
		}
	}
}
