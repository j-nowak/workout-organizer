package controllers;

import java.util.ArrayList;
import java.util.List;

import com.google.gson.Gson;
import models.*;
import play.mvc.Controller;
import play.mvc.Result;
import play.mvc.Security;
import views.html.account;
import views.html.index;
import database.NewsDao;
import database.UsersDao;

//@Security.Authenticated(Secured.class)
public class Application extends Controller {
	
	public static final String USER_ID = "user_id";
	
	public static final String HOME = "/home";
	public static final String LOGIN = "/login";

    public static Result home() {
    	String userId = session(USER_ID);
    	if (userId == null) {
    		return redirect(LOGIN);
    	}
    	else {
    		List<Stranger> strangers = UsersDao.get().getStrangersForUser(Integer.parseInt(userId));
    		List<User> friendshipRequests = UsersDao.get().getFriendshipRequests(Integer.parseInt(userId));
    		List<News> news = NewsDao.get().getNews(Integer.parseInt(userId));
    		return ok(index.render(news, strangers, friendshipRequests));
    	}
    }

	private static int newsId = 0;

	public static Result home_react() throws InterruptedException {
		// TODO: userId
		List<News> news = NewsDao.get().getNews(1);

		List<News> fakeNews = new ArrayList<>();
		for (int i = 0; i < 10; ++i) {
			fakeNews.addAll(news);
		}

		List<NewsWrapper> result = new ArrayList<>();
		for (int i = 0; i < fakeNews.size(); i++) {
			result.add(new NewsWrapper(newsId++, fakeNews.get(i)));
		}

		Thread.sleep(2000);

		response().setHeader(ACCESS_CONTROL_ALLOW_ORIGIN, "*");
		return ok(new Gson().toJson(result));
	}
    
    public static Result editAccountSettings() {
    	try {
	    	User user = UsersDao.get().getById(Integer.parseInt(session(USER_ID))); //TODO change session to userId
	    	return ok(account.render(user));
    	} catch (Exception e) {
    		e.printStackTrace();
    		return badRequest();
    	}
    }

}
