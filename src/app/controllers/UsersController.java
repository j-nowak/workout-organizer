package controllers;

import database.UsersDao;
import play.mvc.Controller;
import play.mvc.Result;

public class UsersController extends Controller {

	public static Result invite(int requestedUserId) {
		try {
			int userId = Integer.parseInt(session("user_id"));
			UsersDao.get().inviteUser(userId, requestedUserId);
			return ok();
		} catch (Exception e) {
			return badRequest();
		}
	}

	public static Result decline(int requesingUserId) {
		try {
			int userId = Integer.parseInt(session("user_id"));
			UsersDao.get().removeRequest(userId, requesingUserId);
			return ok();
		} catch (Exception e) {
			return badRequest();
		}
	}
}
