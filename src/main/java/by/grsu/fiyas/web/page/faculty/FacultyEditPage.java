package by.grsu.fiyas.web.page.faculty;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import org.apache.wicket.authroles.authorization.strategies.role.annotations.AuthorizeInstantiation;
import org.apache.wicket.extensions.markup.html.form.palette.Palette;
import org.apache.wicket.extensions.markup.html.form.palette.theme.DefaultTheme;
import org.apache.wicket.markup.html.form.Check;
import org.apache.wicket.markup.html.form.CheckBox;
import org.apache.wicket.markup.html.form.ChoiceRenderer;
import org.apache.wicket.markup.html.form.Form;
import org.apache.wicket.markup.html.form.SubmitLink;
import org.apache.wicket.markup.html.form.TextField;
import org.apache.wicket.markup.html.link.Link;
import org.apache.wicket.markup.html.panel.Fragment;
import org.apache.wicket.model.CompoundPropertyModel;
import org.apache.wicket.model.IModel;
import org.apache.wicket.model.Model;
import org.apache.wicket.model.PropertyModel;
import org.apache.wicket.model.util.CollectionModel;

import by.grsu.fiyas.datamodel.AcademicSubject;
import by.grsu.fiyas.datamodel.Faculty;
import by.grsu.fiyas.service.AcademicSubjectService;
import by.grsu.fiyas.service.FacultyService;
import by.grsu.fiyas.service.UserService;
import by.grsu.fiyas.service.impl.AcademicSubjectServiceImpl;
import by.grsu.fiyas.service.impl.FacultyServiceImpl;
import by.grsu.fiyas.service.impl.UserServiceImpl;
import by.grsu.fiyas.web.component.extensions.NotificationPanel;
import by.grsu.fiyas.web.page.AbstractPage;

@AuthorizeInstantiation(value = { "admin", })
public class FacultyEditPage extends AbstractPage {

	private FacultyService facultyService;
	private AcademicSubjectService academicSubjectService;
	private Faculty faculty;
	private static String XML_FOLDER = "XMLDatabase";
	
	public FacultyEditPage() {
		super();
		this.faculty = new Faculty();
		facultyService = new FacultyServiceImpl(XML_FOLDER);
		academicSubjectService = new AcademicSubjectServiceImpl(XML_FOLDER);
	}

	public FacultyEditPage(Faculty faculty) {
		super();
		this.faculty = faculty;
		facultyService = new FacultyServiceImpl(XML_FOLDER);
		academicSubjectService = new AcademicSubjectServiceImpl(XML_FOLDER);
		if (faculty.getSubjects() == null)
		{
			faculty.setSubjects(new ArrayList<AcademicSubject>());
		}
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
		
		if (faculty.getName() == null)
		{
			createLabel.setVisible(true);
		}
		else
		{
			updateLabel.setVisible(true);
		}
				
		Form<Faculty> form = new FacultyForm<Faculty>("form", new CompoundPropertyModel<Faculty>(faculty));
		add(form);

		TextField<String> nameField = new TextField<>("name");
		nameField.setRequired(true);
		form.add(nameField);
		
		TextField<Integer> selectionPlanField = new TextField<>("selectionPlan");
		selectionPlanField.setRequired(true);
		form.add(selectionPlanField);
		
		List<AcademicSubject> academicSubjects = academicSubjectService.getAll();
		final Palette<AcademicSubject> palette = new Palette<AcademicSubject>(
				"subjects", 
				Model.ofList(faculty.getSubjects()),
				new CollectionModel<AcademicSubject>(academicSubjects),
				new ChoiceRenderer<AcademicSubject>() {
		        	@Override
		        	public Object getDisplayValue(AcademicSubject object) {
		        		return String.format("%s", object.getName());
		        	}

		        	@Override
		        	public String getIdValue(AcademicSubject object, int index) {
		        		return String.valueOf(object.getId());
		        	}
		        }, 
				10,
				false,
				true				                
		);
		palette.setRequired(true);
		palette.add(new DefaultTheme());
		form.add(palette);
		
		CheckBox checkbox = new CheckBox("active", new PropertyModel<>(faculty, "isEnabled"));
		form.add(checkbox);
		
		add(new NotificationPanel("feedback"));
		
		SubmitLink saveBtn = new SubmitLink("save") {
			@Override
			public void onSubmit() {
				super.onSubmit();	

				Iterator it = palette.getSelectedChoices();
				int count = 0;
				while (it.hasNext()) 
                { 
					it.next();
					count++;
                }
				
				if (count != 3)
				{
					error("You must select exactly 3 subjects.");
					return;
				}
				
				FacultiesPage page = new FacultiesPage();
				if (createLabel.isVisible())
				{
					page.success("Faculty created.");
				}
				else
				{
					page.success("Faculty updated.");
				}
				facultyService.saveOrUpdate(faculty);
				setResponsePage(page);
			}
		};
		form.setDefaultButton(saveBtn);
		form.add(saveBtn);
		
		form.add(new Link("cancel") {
			@Override
			public void onClick() {
				setResponsePage(new FacultiesPage());
			}
		});
	}

	private class FacultyForm<T> extends Form<T> {

		public FacultyForm(String id, IModel<T> model) {
			super(id, model);
		}
	}
}