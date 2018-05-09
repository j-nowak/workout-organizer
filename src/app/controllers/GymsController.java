package controllers;

import com.google.gson.Gson;
import database.GymsDao;
import models.Gym;
import play.mvc.Controller;
import play.mvc.Result;

import java.util.List;

public class GymsController extends Controller {

	public static Result listAllGyms() {
		List<Gym> gymsList = GymsDao.get().getAll();
		return ok(new Gson().toJson(gymsList));
	}

	public static Result showGym(int id) {
		Gym gym = GymsDao.get().getById(id);
		if (gym != null) {
			return ok(new Gson().toJson(gym));
		} else {
			return notFound("Gym not found");
		}
	}

	public static Result images(int gymId) {
		List<String> imageIds = GymsDao.get().getImageIds(gymId);
		return ok(new Gson().toJson(imageIds));
	}
}
