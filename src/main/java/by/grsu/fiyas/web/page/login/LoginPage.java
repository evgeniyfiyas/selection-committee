package by.grsu.fiyas.web.page.login;

import org.apache.wicket.Application;
import org.apache.wicket.authroles.authentication.AuthenticatedWebSession;
import org.apache.wicket.markup.html.form.Form;
import org.apache.wicket.markup.html.form.PasswordTextField;
import org.apache.wicket.markup.html.form.RequiredTextField;
import org.apache.wicket.markup.html.form.SubmitLink;
import org.apache.wicket.markup.html.panel.FeedbackPanel;
import org.apache.wicket.model.CompoundPropertyModel;
import org.apache.wicket.util.string.Strings;

import by.grsu.fiyas.web.component.extensions.NotificationPanel;
import by.grsu.fiyas.web.page.AbstractPage;

public class LoginPage extends AbstractPage {
	public static final String ID_FORM = "form";

	private String username;
	private String password;

	@Override
	protected void onInitialize() {
		super.onInitialize();

		// If the user is already logged in, then we are redirecting him out
		if (AuthenticatedWebSession.get().isSignedIn()) {
			setResponsePage(Application.get().getHomePage());
		}

		final Form<Void> form = new Form<Void>(ID_FORM);
		form.setDefaultModel(new CompoundPropertyModel<LoginPage>(this));
		form.add(new RequiredTextField<String>("username"));
		PasswordTextField passwordTextField = new PasswordTextField("password");
		form.add(passwordTextField);

		SubmitLink submitButton = new SubmitLink("submit-btn") {
			@Override
			public void onSubmit() {
				super.onSubmit();
				if (Strings.isEmpty(username) || Strings.isEmpty(password)) {
					return;
				}
				final boolean authResult = AuthenticatedWebSession.get().signIn(username, password);
				if (authResult) {
					setResponsePage(Application.get().getHomePage());
				} else {
					error("Username or password is incorrect!");
				}
			}
		};
		form.setDefaultButton(submitButton);
		form.add(submitButton);
		
		add(form);

		add(new NotificationPanel("feedbackpanel"));

	}

}
