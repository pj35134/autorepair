package com.mechanic.autorepair;

public class Model {


    private int id;

    public int getId() {
        return id;
    }

    public Model(int id, String title, String image) {
        this.id = id;
        this.title = title;
        this.image = image;
    }

    public String getTitle() {
        return title;
    }

    public String getImage() {
        return image;
    }

    private String title;
    private String image;
}
