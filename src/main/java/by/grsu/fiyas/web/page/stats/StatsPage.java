package by.grsu.fiyas.web.page.stats;

import java.util.List;
import java.util.stream.Collectors;

import org.apache.wicket.markup.html.form.ChoiceRenderer;
import org.apache.wicket.markup.html.form.DropDownChoice;
import org.apache.wicket.markup.html.link.Link;
import org.apache.wicket.model.Model;

import by.grsu.fiyas.datamodel.Faculty;
import by.grsu.fiyas.service.FacultyService;
import by.grsu.fiyas.service.impl.FacultyServiceImpl;
import by.grsu.fiyas.web.component.extensions.NotificationPanel;
import by.grsu.fiyas.web.page.AbstractPage;
import by.grsu.fiyas.web.page.student.StudentRegisterPageDetails;

public class StatsPage extends AbstractPage {
	
	private FacultyService facultyService;
	private static String XML_FOLDER = "XMLDatabase";
	private Faculty chosenFaculty;
	
	public StatsPage() {
		super();
        facultyService = new FacultyServiceImpl(XML_FOLDER);
    }
	
	@Override
	protected void onInitialize() {
		super.onInitialize();
           
        add(new NotificationPanel("feedback"));
        
        List<Faculty> activeFaculties = facultyService.getAll().stream().filter(faculty -> faculty.getIsEnabled()).collect(Collectors.toList());
        Model<Faculty> facultyModel = new Model<>();
		
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
        
        DropDownChoice<Faculty> facultiesDropDown = new DropDownChoice<Faculty>("facultiesDropDown", facultyModel, activeFaculties, facultyChoiceRenderer)
        {        	
        	@Override
        	protected boolean wantOnSelectionChangedNotifications()
        	{
        		return true;
        	}
        };   

        facultiesDropDown.setRequired(true);
        add(facultiesDropDown);
        
        
        Link proceedButton = new Link("proceed-btn") {
			@Override
			public void onClick() {				
				chosenFaculty = facultyModel.getObject();
				setResponsePage(new StatsPageDetails(chosenFaculty));
			}
		};
		
		
		add(proceedButton);
	}
}
