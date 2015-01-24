package controllers;

import java.util.List;

import database.ExerciseDao;
import database.GymsDao;
import models.Exercise;
import models.Gym;
import play.mvc.Controller;
import play.mvc.Result;
import views.html.*;

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
    		return ok(index.render(userId));
    	}
    }

}
