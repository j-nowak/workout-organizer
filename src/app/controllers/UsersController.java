package controllers;

import com.google.gson.Gson;
import database.UsersDao;
import models.User;
import play.mvc.Controller;
import play.mvc.Result;

public class UsersController extends Controller {

	public static Result invite(int requestedUserId) {
		try {
			String userIdStr = request().cookie(Application.USER_ID).value();
			int userId = Integer.parseInt(userIdStr);

			UsersDao.get().inviteUser(userId, requestedUserId);
			return ok();
		} catch (Exception e) {
			return badRequest();
		}
	}

	public static Result decline(int requestingUserId) {
		try {
			String userIdStr = request().cookie(Application.USER_ID).value();
			int userId = Integer.parseInt(userIdStr);

			UsersDao.get().removeRequest(userId, requestingUserId);
			return ok();
		} catch (Exception e) {
			return badRequest();
		}
	}

	public static Result showUser(int foreignerId) {
		int userId = Integer.parseInt(session("user_id") == null ? "1" : session("user_id"));
		User user = UsersDao.get().getById(foreignerId);

		if (user != null) {
			user.setIsFriend(UsersDao.get().areFriends(userId, foreignerId));
			return ok(new Gson().toJson(user));
		}
		else {
			return badRequest("User does not exist");
		}
	}
}
