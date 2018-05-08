package controllers;

import com.google.gson.Gson;
import database.ExerciseDao;
import models.Exercise;
import play.mvc.Controller;
import play.mvc.Result;

import java.util.List;

public class ExercisesController extends Controller {

	public static Result listAllExercises_react() {
		List<Exercise> exercisesList = ExerciseDao.get().getAll();
		return ok(new Gson().toJson(exercisesList));
	}
}
