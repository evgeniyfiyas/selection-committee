package by.grsu.fiyas.web.page;

import java.io.Serializable;

import org.apache.wicket.markup.html.WebPage;

import by.grsu.fiyas.web.component.footer.FooterPanel;
import by.grsu.fiyas.web.component.navbar.NavigationPanel;

public abstract class AbstractPage extends WebPage {
	
	public AbstractPage() {
		super();
	}
	
	@Override
	protected void onInitialize() {
		super.onInitialize();		
		add(new NavigationPanel("navbar"));
		add(new FooterPanel("footer"));		
	}	
}
