package controllers;

import models.User;
import play.data.DynamicForm;
import play.data.Form;
import play.mvc.Controller;
import play.mvc.Result;
import database.UsersDao;
import com.google.gson.Gson;

public class SignController extends Controller {

	private static final String FORM_FIRST_NAME = "firstname";
	private static final String FORM_LAST_NAME = "lastname";
	private static final String FORM_FIRST_NAME_REACT = "firstName";
	private static final String FORM_LAST_NAME_REACT = "lastName";
	private static final String FORM_LOGIN = "username";
	private static final String FORM_EMAIL = "email";
	private static final String FORM_PASSWORD = "password";
	private static final String FORM_REPEATED_PASSWORD = "repeat-password";
	private static final String FORM_REPEATED_PASSWORD_REACT = "passwordConfirm";


	public static Result registerUser() {
		DynamicForm requestData = Form.form().bindFromRequest();
		String firstName = requestData.get(FORM_FIRST_NAME);
		String lastName = requestData.get(FORM_LAST_NAME);
		String login = requestData.get(FORM_LOGIN);
		String email = requestData.get(FORM_EMAIL);
		String password = requestData.get(FORM_PASSWORD);
		String repeatedPassword = requestData.get(FORM_REPEATED_PASSWORD);

		User user = new User(login, email, password, firstName, lastName);
		if (password.equals(repeatedPassword) && saveUser(user)) {
			session().clear();
			session(Application.USER_ID, "" + user.getId());
			return redirect(Application.HOME);
		}
		else {
			return redirect(Application.LOGIN);
		}
	}

	public static Result registerUser_react() {
		DynamicForm requestData = Form.form().bindFromRequest();
		String firstName = requestData.get(FORM_FIRST_NAME_REACT);
		String lastName = requestData.get(FORM_LAST_NAME_REACT);
		String login = requestData.get(FORM_LOGIN);
		String email = requestData.get(FORM_EMAIL);
		String password = requestData.get(FORM_PASSWORD);
		String repeatedPassword = requestData.get(FORM_REPEATED_PASSWORD_REACT);

		response().setHeader(ACCESS_CONTROL_ALLOW_ORIGIN, "*");

		User user = new User(login, email, password, firstName, lastName);
		if (!password.equals(repeatedPassword)) {
			return badRequest("Incorrect password confirmation");
		} else if (!saveUser(user)) {
			return badRequest("Unable to save user");
		}
		else {
			response().setCookie(Application.USER_ID, "" + user.getId());
			return ok(new Gson().toJson(user));
		}
	}

	public static Result loginUser() {
		DynamicForm requestData = Form.form().bindFromRequest();
		String login = requestData.get(FORM_LOGIN);
		String password = requestData.get(FORM_PASSWORD);

		User user = UsersDao.get().login(login, password);

		if (user != null) {
			session().clear();
			session(Application.USER_ID, "" + user.getId());
			return redirect(Application.HOME);
		}
		else {
			return ok("Incorrect login or password"); //TODO
		}
	}

	public static Result loginUser_react() {
		DynamicForm requestData = Form.form().bindFromRequest();
		String login = requestData.get(FORM_LOGIN);
		String password = requestData.get(FORM_PASSWORD);

		User user = UsersDao.get().login(login, password);

		response().setHeader(ACCESS_CONTROL_ALLOW_ORIGIN, "*");
		if (user != null) {
			response().setCookie(Application.USER_ID, "" + user.getId());
			return ok(new Gson().toJson(user));
		}
		else {
			return unauthorized("Incorrect login or password"); //TODO
		}
	}

	public static Result logout() {
		session().clear();
		return redirect(Application.LOGIN);
	}

	private static boolean saveUser(User user) {
		return UsersDao.get().insert(user);
	}
}
