package by.grsu.fiyas.web.page.academicSubject;

import org.apache.wicket.authroles.authorization.strategies.role.annotations.AuthorizeInstantiation;
import org.apache.wicket.markup.html.form.Form;
import org.apache.wicket.markup.html.form.SubmitLink;
import org.apache.wicket.markup.html.form.TextField;
import org.apache.wicket.markup.html.link.Link;
import org.apache.wicket.markup.html.panel.FeedbackPanel;
import org.apache.wicket.model.CompoundPropertyModel;
import org.apache.wicket.model.IModel;

import by.grsu.fiyas.datamodel.AcademicSubject;
import by.grsu.fiyas.service.AcademicSubjectService;
import by.grsu.fiyas.service.UserService;
import by.grsu.fiyas.service.impl.AcademicSubjectServiceImpl;
import by.grsu.fiyas.service.impl.UserServiceImpl;
import by.grsu.fiyas.web.component.extensions.NotificationPanel;
import by.grsu.fiyas.web.page.AbstractPage;

@AuthorizeInstantiation(value = { "admin", })
public class AcademicSubjectEditPage extends AbstractPage {

	private AcademicSubjectService academicSubjectService;
	private UserService userService;
	private AcademicSubject academicSubject;
	private static String XML_FOLDER = "XMLDatabase";
	
	public AcademicSubjectEditPage() {
		super();
		this.academicSubject = new AcademicSubject();
		academicSubjectService = new AcademicSubjectServiceImpl(XML_FOLDER);
		userService = new UserServiceImpl(XML_FOLDER);
	}

	public AcademicSubjectEditPage(AcademicSubject academicSubject) {
		super();
		this.academicSubject = academicSubject;
		academicSubjectService = new AcademicSubjectServiceImpl(XML_FOLDER);
		userService = new UserServiceImpl(XML_FOLDER);
	}

	@Override
	protected void onInitialize() {
		super.onInitialize();

		Form<AcademicSubject> form = new AcademicSubjectForm<AcademicSubject>("form", new CompoundPropertyModel<AcademicSubject>(academicSubject));
		add(form);

		TextField<String> nameField = new TextField<>("name");
		nameField.setRequired(true);
		form.add(nameField);

		SubmitLink saveBtn = new SubmitLink("save") {
			@Override
			public void onSubmit() {
				super.onSubmit();
				AcademicSubjectsPage page = new AcademicSubjectsPage();
				if (academicSubjectService.get(academicSubject.getId()) == null)
				{
					page.success("Subject created.");
				}
				else
				{
					page.success("Subject updated.");
				}
				academicSubjectService.saveOrUpdate(academicSubject);				
				setResponsePage(page);
			}
		};
		form.setDefaultButton(saveBtn);
		form.add(saveBtn);
		
		form.add(new Link("cancel") {
			@Override
			public void onClick() {
				setResponsePage(new AcademicSubjectsPage());
			}
		});
	}

	private class AcademicSubjectForm<T> extends Form<T> {

		public AcademicSubjectForm(String id, IModel<T> model) {
			super(id, model);
		}
	}
}
