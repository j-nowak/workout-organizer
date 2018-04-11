package models;

public class ImageDescription {

    private int id;
    private String name;

    private String thumbnailUrl;
    private String imageUrl;

    public ImageDescription(int id, String url) {
        this.id = id;
        this.name = "image_" + id;

        this.thumbnailUrl = url;
        this.imageUrl = url;
    }
}
