package controllers;

import java.util.List;

import database.ExerciseDao;
import database.GymsDao;
import database.UsersDao;
import models.Exercise;
import models.Gym;
import models.User;
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
    
    public static Result listAllGyms() {
    	List<Gym> gymsList = GymsDao.get().getAll();
    	return ok(gyms.render(gymsList));
    }
    
    public static Result editAccountSettings() {
    	User user = UsersDao.get().getById(session(USER_EMAIL)); //TODO change session to userId
    	return ok(account.render(user));
    }

}
