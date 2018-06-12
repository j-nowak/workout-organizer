package controllers;

import com.google.gson.Gson;
import database.UsersDao;
import models.Stranger;
import models.User;
import play.mvc.Result;

import java.util.List;

public class FriendshipsController extends BaseController {

    public static Result invite(int requestedUserId) {
        try {
            Integer userId = getCurrentUserId();

            UsersDao.get().inviteUser(userId, requestedUserId);
            return ok();
        } catch (Exception e) {
            return badRequest();
        }
    }

    public static Result decline(int requestingUserId) {
        try {
            Integer userId = getCurrentUserId();

            UsersDao.get().removeRequest(userId, requestingUserId);
            return ok();
        } catch (Exception e) {
            return badRequest();
        }
    }

    public static Result strangers() {
        Integer userId = getCurrentUserId();

        List<Stranger> strangers = UsersDao.get().getStrangersForUser(userId);
        return ok(new Gson().toJson(strangers));
    }

    public static Result friendsRequests() {
        Integer userId = getCurrentUserId();

        List<User> friendshipRequests = UsersDao.get().getFriendshipRequests(userId);
        return ok(new Gson().toJson(friendshipRequests));
    }
}
