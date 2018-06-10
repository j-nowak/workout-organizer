package controllers;

import com.google.gson.Gson;
import database.GymsDao;
import play.mvc.Controller;
import play.mvc.Http.MultipartFormData;
import play.mvc.Result;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.UUID;

public class ImagesController extends Controller {

    public static Result get(String imageId) throws IOException {
        Path path = Paths.get("public/images/" + imageId);
        byte[] bytes = Files.readAllBytes(path);
        return ok(bytes).as("image/png");
    }

    public static Result upload(int gymId) throws IOException {
        MultipartFormData body = request().body().asMultipartFormData();
        MultipartFormData.FilePart picture = body.getFile("image");
        if (picture != null) {
            String imageId = UUID.randomUUID().toString() + ".png";
            String path = "public/images/" + imageId;
            File file = picture.getFile();
            Files.write(Paths.get(path), Files.readAllBytes(Paths.get(file.getAbsolutePath())));

            GymsDao.get().insertImage(imageId, gymId);

            return ok(new Gson().toJson("ok"));
        } else {
            flash("error", "Missing file");
            return badRequest();
        }
    }

}
