package by.grsu.fiyas.web.page.academicSubject;

import org.apache.wicket.authorization.Action;
import org.apache.wicket.authroles.authorization.strategies.role.annotations.AuthorizeAction;
import org.apache.wicket.markup.html.link.Link;

import by.grsu.fiyas.datamodel.AcademicSubject;
import by.grsu.fiyas.web.component.extensions.NotificationPanel;
import by.grsu.fiyas.web.page.AbstractPage;
import by.grsu.fiyas.web.page.academicSubject.panel.AcademicSubjectListPanel;

public class AcademicSubjectsPage extends AbstractPage {
	
	public AcademicSubjectsPage() {
		super();
		add(new AcademicSubjectListPanel("subject-list-panel", "XMLDatabase"));

		add(new InvisibleLink("create") {
			@Override
			public void onClick() {
				setResponsePage(new AcademicSubjectEditPage(new AcademicSubject()));
			}
		});

		add(new NotificationPanel("feedback"));
		
	}
	
	@AuthorizeAction(roles = { "admin" }, action = Action.RENDER)
	private class InvisibleLink extends Link {

		public InvisibleLink(String id) {
			super(id);
		}

		@Override
		public void onClick() {
		}
	}
}
