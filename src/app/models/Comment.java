package models;

public class Comment {
    private int id;
    private String text;
    private String author;

    public Comment(int id, String text, String author) {
        this.id = id;
        this.text = text;
        this.author = author;
    }
}
