package controllers;

import com.google.gson.Gson;
import database.UsersDao;
import models.Stranger;
import models.User;
import play.mvc.Controller;
import play.mvc.Result;

import java.util.List;

public class FriendshipsController extends Controller {

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

    public static Result strangers() {
        String userIdStr = request().cookie(Application.USER_ID).value();
        int userId = Integer.parseInt(userIdStr);

        List<Stranger> strangers = UsersDao.get().getStrangersForUser(userId);
        return ok(new Gson().toJson(strangers));
    }

    public static Result friendsRequests() {
        String userIdStr = request().cookie(Application.USER_ID).value();
        int userId = Integer.parseInt(userIdStr);

        List<User> friendshipRequests = UsersDao.get().getFriendshipRequests(userId);
        return ok(new Gson().toJson(friendshipRequests));
    }
}
