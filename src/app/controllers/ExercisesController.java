package controllers;

import java.util.List;

import database.ExerciseDao;
import database.GymsDao;
import models.Exercise;
import models.Gym;
import play.data.DynamicForm;
import play.data.Form;
import play.mvc.Controller;
import play.mvc.Result;
import views.html.*;

public class ExercisesController extends Controller {
	
    private static final String RATING = "rating";

	public static Result listAllExercises() {
    	List<Exercise> exercisesList = ExerciseDao.get().getAll(); 
		return ok(exercises.render(exercisesList));
	}
    
    public static Result rate(int exerciseId) {
    	String userId = session(Application.USER_ID);
		DynamicForm requestData = Form.form().bindFromRequest();
		String ratingRaw = requestData.get(RATING);
		
    	try {
    		int rating = Integer.parseInt(ratingRaw);
    		if (rating < 0 || rating > 10)
    			return badRequest();

    		ExerciseDao.get().rateExercise(Integer.parseInt(userId), exerciseId, rating);
        	return ok();
    	} catch (Exception e) {
    		e.printStackTrace();
    		return badRequest();
    	}
    }

}
