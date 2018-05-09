package controllers;

import com.google.gson.Gson;
import database.UsersDao;
import models.User;
import play.data.DynamicForm;
import play.data.Form;
import play.mvc.Controller;
import play.mvc.Result;

public class UsersController extends Controller {

	public static Result changePassword() {
		DynamicForm requestData = Form.form().bindFromRequest();
		String oldPassword = requestData.get("oldPassword");
		String newPassword = requestData.get("newPassword");
		String repeatedPassword = requestData.get("repeatedPassword");

		String userIdStr = request().cookie(Application.USER_ID).value();
		int userId = Integer.parseInt(userIdStr);

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

	public static Result showUser(int foreignerId) {
		User user = UsersDao.get().getById(foreignerId);

		if (user != null) {
			return ok(new Gson().toJson(user));
		}
		else {
			return notFound("User does not exist");
		}
	}
}
