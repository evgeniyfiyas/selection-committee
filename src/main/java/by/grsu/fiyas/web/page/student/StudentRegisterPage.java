package by.grsu.fiyas.web.page.student;

import java.util.List;
import java.util.stream.Collectors;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.apache.wicket.Application;
import org.apache.wicket.authroles.authentication.AuthenticatedWebSession;
import org.apache.wicket.markup.html.form.Button;
import org.apache.wicket.markup.html.form.ChoiceRenderer;
import org.apache.wicket.markup.html.form.DropDownChoice;
import org.apache.wicket.markup.html.form.Form;
import org.apache.wicket.markup.html.form.PasswordTextField;
import org.apache.wicket.markup.html.form.RequiredTextField;
import org.apache.wicket.markup.html.form.SubmitLink;
import org.apache.wicket.markup.html.link.Link;
import org.apache.wicket.model.CompoundPropertyModel;
import org.apache.wicket.model.Model;
import org.apache.wicket.model.util.ListModel;
import org.apache.wicket.request.component.IRequestablePage;
import org.apache.wicket.request.mapper.parameter.PageParameters;
import org.apache.wicket.util.string.Strings;

import by.grsu.fiyas.datamodel.Faculty;
import by.grsu.fiyas.service.FacultyService;
import by.grsu.fiyas.service.impl.FacultyServiceImpl;
import by.grsu.fiyas.web.component.extensions.NotificationPanel;
import by.grsu.fiyas.web.page.AbstractPage;
import by.grsu.fiyas.web.page.login.LoginPage;

public class StudentRegisterPage extends AbstractPage {	
	
	private FacultyService facultyService;
	private static String XML_FOLDER = "XMLDatabase";
    private static final Logger logger = LogManager.getLogger("log");	
	private Faculty chosenFaculty;
    
	public StudentRegisterPage() {
		super();
        facultyService = new FacultyServiceImpl(XML_FOLDER); 
        
                
        Form form = new Form("form");
		add(form);
        
        
        add(new NotificationPanel("feedback"));
        
        List<Faculty> activeFaculties = facultyService.getAll().stream().filter(faculty -> faculty.getIsEnabled()).collect(Collectors.toList());
        chosenFaculty = activeFaculties.size() > 0 ? activeFaculties.get(0) : null;
        
        ChoiceRenderer<Faculty> facultyChoiceRenderer = new ChoiceRenderer<Faculty>() {
        	@Override
        	public Object getDisplayValue(Faculty object) {
        		return String.format("%s", object.getName());
        	}

        	@Override
        	public String getIdValue(Faculty object, int index) {
        		return String.valueOf(object.getId());
        	}
        };
        
        DropDownChoice<Faculty> facultiesDropDown = new DropDownChoice<Faculty>("facultiesDropDown", Model.ofList(activeFaculties), facultyChoiceRenderer)
        {
        	@Override
        	protected CharSequence getDefaultChoice(String selectedValue) 
        	{
        		return " ";
        	}
        	
//        	@Override
//        	protected boolean wantOnSelectionChangedNotifications()
//        	{
//        		logger.info("WANT ON SELECTION NOTIFY = TRUE");
//        		return true;
//        	}
//        	
//        	@Override
//        	   protected void onSelectionChanged(final Faculty newSelection) {
//                chosenFaculty = newSelection;
//                
//                phoneModelDDC.setChoices(getTerminalsByVendor(phoneVendor));
//               }
        };   
        form.add(facultiesDropDown);   
                
        Link proceedButton = new Link("proceed-btn") {
			@Override
			public void onClick() {
        		logger.info("ONCLICK EVENT");
//				super.onSubmit();
        		chosenFaculty = facultiesDropDown.getModel().getObject();
				logger.info(chosenFaculty == null ? "chosenFaculty IS NULL" : chosenFaculty);
				setResponsePage(new LoginPage());
			}
		};
		form.add(proceedButton); 
	}
	
}