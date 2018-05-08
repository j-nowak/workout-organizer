package controllers;

import com.google.gson.Gson;
import database.UsersDao;
import play.data.DynamicForm;
import play.data.Form;
import play.mvc.Controller;
import play.mvc.Result;

public class AccountController extends Controller {

	public static Result changePassword() {
		DynamicForm requestData = Form.form().bindFromRequest();
		String oldPassword = requestData.get("oldPassword");
		String newPassword = requestData.get("newPassword");
		String repeatedPassword = requestData.get("repeatedPassword");

		int userId;
		try {
			userId = Integer.parseInt(requestData.get("userId"));
		} catch (Exception e) {
			e.printStackTrace();
			return badRequest();
		}

		if (newPassword.equals(repeatedPassword)) {
			if (UsersDao.get().checkPasswordForUser(userId, oldPassword)) {
				UsersDao.get().changePassword(userId, newPassword);
				return ok();
			}
			else {
				return badRequest(new Gson().toJson("Old password is incorrect!"));
			}
		}
		else {
			return badRequest(new Gson().toJson("New and repeated passwords are different!"));
		}
	}
}
