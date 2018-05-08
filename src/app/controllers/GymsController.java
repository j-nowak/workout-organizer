package controllers;

import com.google.gson.Gson;
import database.GymsDao;
import models.Comment;
import models.Gym;
import models.ImageDescription;
import play.data.DynamicForm;
import play.data.Form;
import play.mvc.Controller;
import play.mvc.Result;

import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

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
		List<String> thumbnails = Arrays.asList(
				"https://pbs.twimg.com/profile_images/630811593882882053/gXk6482G_400x400.jpg",
				"https://pbs.twimg.com/profile_images/3272164622/57e509aa3f4a336544ef591ddbbba459.jpeg",
				"http://recsports.ufl.edu/images/made/uploads/images/IMG_6445_256_256_80_c1.jpg",
				"https://pbs.twimg.com/profile_images/731125654264139776/j0Y2fhVy.jpg",
				"https://pbs.twimg.com/profile_images/630811593882882053/gXk6482G_400x400.jpg",
				"https://pbs.twimg.com/profile_images/3272164622/57e509aa3f4a336544ef591ddbbba459.jpeg",
				"http://recsports.ufl.edu/images/made/uploads/images/IMG_6445_256_256_80_c1.jpg",
				"https://pbs.twimg.com/profile_images/731125654264139776/j0Y2fhVy.jpg"
		);

		List<ImageDescription> images = IntStream
				.range(0, thumbnails.size())
				.mapToObj(id -> new ImageDescription(id, thumbnails.get(id)))
				.collect(Collectors.toList());

		response().setHeader(ACCESS_CONTROL_ALLOW_ORIGIN, "*");
		return ok(new Gson().toJson(images));
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
