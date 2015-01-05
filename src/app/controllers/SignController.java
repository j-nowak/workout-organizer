package controllers;

import models.User;
import play.data.DynamicForm;
import play.data.Form;
import play.mvc.Controller;
import play.mvc.Result;
import sun.reflect.generics.reflectiveObjects.NotImplementedException;
import database.UsersDao;

public class SignController extends Controller {
	
	private static final String FORM_FIRST_NAME = "firstname";
	private static final String FORM_LAST_NAME = "lastname";
	private static final String FORM_LOGIN = "username";
	private static final String FORM_EMAIL = "email";
	private static final String FORM_PASSWORD = "password";
	private static final String FORM_REPEATED_PASSWORD = "repeat-password";
	
	
	public static Result registerUser() {
		DynamicForm requestData = Form.form().bindFromRequest();
		String firstName = requestData.get(FORM_FIRST_NAME);
		String lastName = requestData.get(FORM_LAST_NAME);
		String login = requestData.get(FORM_LOGIN);
		String email = requestData.get(FORM_EMAIL);
		String password = requestData.get(FORM_PASSWORD);
		String repeatedPassword = requestData.get(FORM_REPEATED_PASSWORD);
		
//		//TODO validation (maybe online?)
		
		User user = new User(login, email, password, firstName, lastName);
		if (saveUser(user)) {
			return ok("Registered!");
		}
		else {
			throw new NotImplementedException(); //TODO invalid inserts
		}
	}
	
	private static boolean saveUser(User user) {
		return UsersDao.get().insert(user);
	}
}
