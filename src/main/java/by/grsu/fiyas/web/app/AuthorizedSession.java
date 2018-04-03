package by.grsu.fiyas.web.app;

import org.apache.wicket.Session;
import org.apache.wicket.authroles.authentication.AuthenticatedWebSession;
import org.apache.wicket.authroles.authorization.strategies.role.Roles;
import org.apache.wicket.request.Request;

import by.grsu.fiyas.datamodel.UserCredentials;
import by.grsu.fiyas.service.UserService;
import by.grsu.fiyas.service.impl.UserServiceImpl;
import by.grsu.fiyas.web.app.AuthorizedSession;

public class AuthorizedSession extends AuthenticatedWebSession {
	private UserService userService;
	private UserCredentials loggedUser;
	private Roles roles;

	public AuthorizedSession(Request request) {

		super(request);
		userService = new UserServiceImpl("XMLDatabase");
	}

	public static AuthorizedSession get() {
		return (AuthorizedSession) Session.get();
	}

	@Override
	public boolean authenticate(final String userName, final String password) {
		loggedUser = userService.getByNameAndPassword(userName, password);
		return loggedUser != null;
	}

	@Override
	public Roles getRoles() {
		if (isSignedIn() && (roles == null)) {
			roles = new Roles();
			roles.addAll(userService.resolveRoles(loggedUser.getId()));
		}
		return roles;
	}

	@Override
	public void signOut() {
		super.signOut();
		loggedUser = null;
		roles = null;
	}

	public UserCredentials getLoggedUser() {
		return loggedUser;
	}
}
