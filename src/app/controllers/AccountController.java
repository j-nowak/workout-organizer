package controllers;

import database.UsersDao;
import play.data.DynamicForm;
import play.data.Form;
import play.mvc.Result;
import play.mvc.Controller;

public class AccountController extends Controller {
	
	public static Result changePassword() {
		DynamicForm requestData = Form.form().bindFromRequest();
		String oldPassword = requestData.get("oldPassword");
		String newPassword = requestData.get("newPassword");
		String repeatedPassword = requestData.get("repeatedPassword");
		
		if (newPassword.equals(repeatedPassword)) {
			if (UsersDao.get().checkPasswordForUser(session().get(Application.USER_ID), oldPassword)) {
				UsersDao.get().changePassword(session().get(Application.USER_ID), newPassword);
				return ok();
			}
			else {
				return badRequest("Old password is incorrect!");
			}
		}
		else {
			return badRequest("New and repeated passwords are different!");
		}
	}
}
