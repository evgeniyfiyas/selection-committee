package by.grsu.fiyas.web.component.footer;

import java.util.Calendar;

import org.apache.wicket.markup.html.basic.Label;
import org.apache.wicket.markup.html.panel.Panel;

public class FooterPanel extends Panel {
	
	public FooterPanel(String id) {
		super(id);
		add(new Label("current-year", Calendar.getInstance().get(Calendar.YEAR)));
	}	
}
