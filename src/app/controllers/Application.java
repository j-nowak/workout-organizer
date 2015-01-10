package controllers;

import java.util.List;

import database.ExerciseDao;
import models.Exercise;
import play.mvc.Controller;
import play.mvc.Result;
import views.html.*;

public class Application extends Controller {
	
	public static final String USER_EMAIL = "email";
	
	public static final String HOME = "/home";
	public static final String LOGIN = "/login";

    public static Result home() {
    	String userMail = session(USER_EMAIL);
    	if (userMail == null) {
    		return redirect(LOGIN);
    	}
    	else {
    		return ok(index.render(userMail));
    	}
    }
    
    public static Result listAllExercises() {
    	List<Exercise> exercisesList = ExerciseDao.get().getAll();    	
		return ok(exercises.render(exercisesList));
	}

}
