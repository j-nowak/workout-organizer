package controllers;

import com.google.gson.Gson;
import database.WorkoutDao;
import models.Workout;
import models.WorkoutEntry;
import play.data.Form;
import play.mvc.Controller;
import play.mvc.Result;

import java.sql.Timestamp;
import java.util.List;
import java.util.Map;

public class WorkoutsController extends BaseController {

	public static Result index() {
		String origin = request().getHeader("origin");
		origin = origin == null ? "*" : origin;
		response().setHeader(ACCESS_CONTROL_ALLOW_ORIGIN, origin);
		response().setHeader(ACCESS_CONTROL_ALLOW_CREDENTIALS, "true");
		try {
			Integer userId = getCurrentUserId();
			List<Workout> workouts = WorkoutDao.get().getUserWorkouts(userId);
			return ok(new Gson().toJson(workouts));
		} catch (Exception e) {
			e.printStackTrace();
			return internalServerError();
		}
	}

    public static Result like(int workoutId) {
		Integer userId = getCurrentUserId();

        try {
            int likesCount = WorkoutDao.get().like(workoutId, userId);
            return ok("" + likesCount);
        } catch (Exception e) {
            e.printStackTrace();
            return internalServerError();
        }
    }

	public static Result create() {
		Map<String, String> params = Form.form().bindFromRequest().data();
		try {
			Integer userId = getCurrentUserId();
			String gymId = params.get("gymId");
			String startedAt =  params.get("startedAt");
			String finishedAt = params.get("finishedAt");
			String note = params.get("note");

			startedAt = startedAt.replace("T", " ") + ":00";
			finishedAt = finishedAt.replace("T", " ") + ":00";
			Workout workout = new Workout(userId, Integer.parseInt(gymId), Timestamp.valueOf(startedAt), Timestamp.valueOf(finishedAt));
			workout.setNote(note);

			for (int i = 0; params.get("entries[" + i + "].exerciseId") != null; ++i) {
				WorkoutEntry entry = new WorkoutEntry();
				String prefix = "entries[" + i + "].";
				String exerciseId = params.get(prefix + "exerciseId");
				String setsCount = params.get(prefix + "setsCount");
				String repsPerSet = params.get(prefix + "repsPerSet");
				String weight = params.get(prefix + "weight");

				entry.setExerciseId(Integer.parseInt(exerciseId));
				if (setsCount != null)
					entry.setSetsCount(Integer.parseInt(setsCount));
				if (repsPerSet != null)
					entry.setRepsPerSet(Integer.parseInt(repsPerSet));
				if (weight != null)
					entry.setWeight(Double.parseDouble(weight));

				workout.addWorkoutEntry(entry);
			}

			WorkoutDao.get().create(workout);
			return ok(new Gson().toJson(workout));
		} catch (Exception e) {
			e.printStackTrace();
			return internalServerError("Unable to store workout");
		}
	}
}
