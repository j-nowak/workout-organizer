package controllers;

import models.User;
import play.Logger;
import play.data.DynamicForm;
import play.data.Form;
import play.mvc.Controller;
import play.mvc.Http;
import play.mvc.Result;
import database.UsersDao;
import com.google.gson.Gson;

public class SignController extends BaseController {

	private static final String FORM_FIRST_NAME = "firstName";
	private static final String FORM_LAST_NAME = "lastName";
	private static final String FORM_LOGIN = "username";
	private static final String FORM_EMAIL = "email";
	private static final String FORM_PASSWORD = "password";
	private static final String FORM_REPEATED_PASSWORD = "passwordConfirm";

	public static Result registerUser() {
		DynamicForm requestData = Form.form().bindFromRequest();
		String firstName = requestData.get(FORM_FIRST_NAME);
		String lastName = requestData.get(FORM_LAST_NAME);
		String login = requestData.get(FORM_LOGIN);
		String email = requestData.get(FORM_EMAIL);
		String password = requestData.get(FORM_PASSWORD);
		String repeatedPassword = requestData.get(FORM_REPEATED_PASSWORD);

		User user = new User(login, email, password, firstName, lastName);
		if (!password.equals(repeatedPassword)) {
			return badRequest("Incorrect password confirmation");
		} else if (!saveUser(user)) {
			return badRequest("Unable to save user");
		}
		else {
			String token = UsersDao.get().createSession(user);
			if (token != null) {
				response().setCookie(Application.SESSION_TOKEN, token, 60 * 60 * 24 * 365);
			}
			return ok(new Gson().toJson(user));
		}
	}

	public static Result loginUser() {
		DynamicForm requestData = Form.form().bindFromRequest();
		String login = requestData.get(FORM_LOGIN);
		String password = requestData.get(FORM_PASSWORD);

		UsersDao dao = UsersDao.get();
		User user = dao.login(login, password);
		if (user != null) {
			String sessionToken = dao.createSession(user);
			Logger.info("Created session: " + sessionToken + " for user: " + user.getId());
			response().setCookie(Application.SESSION_TOKEN, sessionToken, 60 * 60 * 24 * 365);
			return ok(new Gson().toJson(user));
		}
		else {
			return unauthorized("Incorrect login or password");
		}
	}

	public static Result getCurrentUser() {
		Integer userId = getCurrentUserId();

		if (userId != null) {
			User user = UsersDao.get().getById(userId);
			return ok(new Gson().toJson(user));
		}
		else {
			return notFound("Session not found");
		}
	}

	public static Result logout() {
		Http.Cookie cookie = request().cookie(Application.SESSION_TOKEN);
		if (cookie != null) {
			UsersDao.get().logout(cookie.value());
		}
		response().discardCookie(Application.SESSION_TOKEN);
		session().clear();
		return ok(new Gson().toJson(true));
	}

	private static boolean saveUser(User user) {
		return UsersDao.get().insert(user);
	}
}
