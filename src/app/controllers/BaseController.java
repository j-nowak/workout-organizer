package controllers;

import database.UsersDao;
import play.mvc.Controller;
import play.mvc.Http;

public abstract class BaseController extends Controller {

    protected static Integer getCurrentUserId() {
        Http.Cookie cookie = request().cookie(Application.SESSION_TOKEN);
        if (cookie == null) {
            return null;
        }
        return UsersDao.get().getUserIdFromSession(cookie.value());
    }
}
