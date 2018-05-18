package by.grsu.fiyas.web.common.renderer;

import org.apache.wicket.markup.html.form.ChoiceRenderer;

import by.grsu.fiyas.datamodel.Faculty;

public class FacultyChoiceRenderer extends ChoiceRenderer<Faculty> {

	public static final FacultyChoiceRenderer INSTANCE = new FacultyChoiceRenderer();

	private FacultyChoiceRenderer() {
		super();
	}

	@Override
	public Object getDisplayValue(Faculty object) {
		return String.format("%s (%s, %s, %s)", object.getName(), object.getSubjects().get(0).getName(),object.getSubjects().get(1).getName(),object.getSubjects().get(2).getName());
	}

	@Override
	public String getIdValue(Faculty object, int index) {
		return String.valueOf(object.getId());
	}
	
}