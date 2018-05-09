package controllers;

import com.google.gson.Gson;
import database.GymsDao;
import models.Comment;
import models.Gym;
import play.data.DynamicForm;
import play.data.Form;
import play.mvc.Controller;
import play.mvc.Result;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

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

	public static Result gymThumbnails_react(int gymId) {
		List<String> imageIds = GymsDao.get().getImageIds(gymId);
		return ok(new Gson().toJson(imageIds));
	}

	private static Map<Integer, List<Comment>> comments = new HashMap<>();
	private static int commentId = 0;

	public static Result addComment(int imageId) {
		DynamicForm requestData = Form.form().bindFromRequest();
		String text = requestData.get("newComment");
		String author = requestData.get("author");

		Comment newComment = new Comment(commentId++, text, author);

		if (comments.get(imageId) == null) {
			comments.put(imageId, new ArrayList<>());
		}
		comments.get(imageId).add(newComment);

		response().setHeader(ACCESS_CONTROL_ALLOW_ORIGIN, "*");
		return ok(new Gson().toJson(newComment));
	}

	public static Result getComments(int imageId) {
		if (comments.get(imageId) == null) {
			comments.put(imageId, new ArrayList<>());
		}

		response().setHeader(ACCESS_CONTROL_ALLOW_ORIGIN, "*");
		return ok(new Gson().toJson(comments.get(imageId)));
	}
}
