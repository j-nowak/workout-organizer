package controllers;

import play.mvc.Controller;
import play.mvc.Result;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

public class ImagesController extends Controller {

    public static Result get(String imageId) throws IOException {
        Path path = Paths.get("public/images/" + imageId);
        byte[] bytes = Files.readAllBytes(path);
        return ok(bytes).as("image/png");
    }
}
