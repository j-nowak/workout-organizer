package controllers;

import com.google.gson.Gson;
import database.NewsDao;
import models.News;
import play.mvc.Result;

import java.util.ArrayList;
import java.util.List;

public class Application extends BaseController {

	public static final String USER_ID = "user_id";
	public static final String SESSION_TOKEN = "session_token";

	public static final String LOGIN = "/login";

	public static Result home() {
		Integer userId = getCurrentUserId();

		if (userId == null) {
			List<News> result = new ArrayList<>();
			return ok(new Gson().toJson(result));
		}

		List<News> news = NewsDao.get().getNews(userId);
		return ok(new Gson().toJson(news));
	}

    public static Result options(String path) {
        response().setHeader(ACCESS_CONTROL_ALLOW_ORIGIN, "http://localhost:3000");
        response().setHeader(ACCESS_CONTROL_ALLOW_METHODS, "GET, POST, PUT, DELETE, OPTIONS");
        response().setHeader(ACCESS_CONTROL_ALLOW_HEADERS, "Accept, Origin, Content-type, X-Json, X-Prototype-Version, X-Requested-With");
        response().setHeader(ACCESS_CONTROL_ALLOW_CREDENTIALS, "true");
        return ok("");
    }

}
