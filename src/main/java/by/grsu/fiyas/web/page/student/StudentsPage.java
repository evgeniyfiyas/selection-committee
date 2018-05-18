package by.grsu.fiyas.web.page.student;

import org.apache.wicket.authorization.Action;
import org.apache.wicket.authroles.authorization.strategies.role.annotations.AuthorizeAction;
import org.apache.wicket.markup.html.link.Link;

import by.grsu.fiyas.datamodel.Student;
import by.grsu.fiyas.web.component.extensions.NotificationPanel;
import by.grsu.fiyas.web.page.AbstractPage;
import by.grsu.fiyas.web.page.student.panel.StudentListPanel;

public class StudentsPage extends AbstractPage {
	
	public StudentsPage() {
		super();
		add(new StudentListPanel("students-list-panel", "XMLDatabase"));

		add(new InvisibleLink("create") {
			@Override
			public void onClick() {
				setResponsePage(new StudentEditPage());
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
