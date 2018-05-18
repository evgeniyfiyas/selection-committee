package by.grsu.fiyas.web.component.navbar;

import org.apache.wicket.markup.html.link.Link;
import org.apache.wicket.markup.html.panel.Fragment;
import org.apache.wicket.markup.html.panel.Panel;

import by.grsu.fiyas.web.page.academicSubject.AcademicSubjectsPage;
import by.grsu.fiyas.web.page.faculty.FacultiesPage;
import by.grsu.fiyas.web.page.home.HomePage;
import by.grsu.fiyas.web.page.login.LoginPage;
import by.grsu.fiyas.web.page.stats.StatsPage;
import by.grsu.fiyas.web.page.student.StudentRegisterPage;
import by.grsu.fiyas.web.page.student.StudentsPage;
import by.grsu.fiyas.web.app.AuthorizedSession;

public class NavigationPanel extends Panel {
	
	public NavigationPanel(String id) {
		super(id);
	}

	@Override
	protected void onInitialize() {
		super.onInitialize();
		
		/* Main menu */
		
		add(new Link("link-home"){
			@Override
			public void onClick() {
				setResponsePage(new HomePage());
			}
		});
		
		add(new Link("link-register") {
			@Override
			public void onClick() {
				setResponsePage(new StudentRegisterPage());
			}
		});
		
		add(new Link("link-stats") {
			@Override
			public void onClick() {
				setResponsePage(new StatsPage());
			}
		});		
		
		/* Main menu end */
		
		
		/* Administration dropdown */
		
		Link linkLogin = new Link("link-login") {
			@Override
			public void onClick() {
				setResponsePage(new LoginPage());
			}
		};
		linkLogin.setVisible(!AuthorizedSession.get().isSignedIn());
		add(linkLogin);
		
		Link linkLogout = new Link("link-logout") {
            @Override
            public void onClick() {
                getSession().invalidate();
                setResponsePage(LoginPage.class);
            }
        };
        linkLogout.setVisible(AuthorizedSession.get().isSignedIn());
        add(linkLogout);
        
        Fragment separator = new Fragment("separator", "separator", this);
        separator.setVisible(AuthorizedSession.get().isSignedIn());
        add(separator);
        
		Link linkEditFaculties = new Link("link-editFaculties") {
			@Override
			public void onClick() {
				setResponsePage(new FacultiesPage());
			}
		};
		linkEditFaculties.setVisible(AuthorizedSession.get().isSignedIn());
		add(linkEditFaculties);
		
		Link linkEditAcademicSubjects = new Link("link-editAcademicSubjects") {
			@Override
			public void onClick() {
				setResponsePage(new AcademicSubjectsPage());
			}
		};
		linkEditAcademicSubjects.setVisible(AuthorizedSession.get().isSignedIn());
		add(linkEditAcademicSubjects);
		
		Link linkEditStudents = new Link("link-editStudents") {
			@Override
			public void onClick() {
				setResponsePage(new StudentsPage());
			}
		};
		linkEditStudents.setVisible(AuthorizedSession.get().isSignedIn());
		add(linkEditStudents);
		
		/* Administration dropdown end */
	}
	
}
