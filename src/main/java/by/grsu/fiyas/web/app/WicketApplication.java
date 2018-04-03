package by.grsu.fiyas.web.app;

import org.apache.wicket.authroles.authentication.AbstractAuthenticatedWebSession;
import org.apache.wicket.authroles.authentication.AuthenticatedWebApplication;
import org.apache.wicket.markup.html.WebPage;
import org.apache.wicket.protocol.http.WebApplication;

import by.grsu.fiyas.web.page.home.HomePage;
import by.grsu.fiyas.web.app.AuthorizedSession;
import by.grsu.fiyas.web.page.login.LoginPage;
import by.grsu.fiyas.web.page.student.StudentRegisterPage;

public class WicketApplication extends AuthenticatedWebApplication {

	@Override
	public void init() {
		super.init();
		mountPage("/register", StudentRegisterPage.class);
		mountPage("/login", LoginPage.class);
	}
	
	@Override
	protected Class<? extends AbstractAuthenticatedWebSession> getWebSessionClass() {
		return AuthorizedSession.class;
	}

	@Override
	protected Class<? extends WebPage> getSignInPageClass() {
		return LoginPage.class;
	}	
	
	@Override
	public Class<? extends WebPage> getHomePage() {
		return HomePage.class;
	}
}
