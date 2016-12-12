package com.example.evan.movieapp;

/**
 * Created by Evan on 12/11/2016.
 */

public class Movie {
    private int id;
    private String title;
    private String description;
    private String thumbnail;
    private String video;
    private int rating;

    public Movie() {}

    public Movie(String title, String desciption, String thumbnail, String video, int rating) {
        this.title = title;
        this.description = desciption;
        this.thumbnail = thumbnail;
        this.video = video;
        this.rating = rating;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getThumbnail() {
        return thumbnail;
    }

    public void setThumbnail(String thumbnail) {
        this.thumbnail = thumbnail;
    }

    public String getVideo() {
        return video;
    }

    public void setVideo(String video) {
        this.video = video;
    }

    public int getRating() {
        return rating;
    }

    public void setRating(int rating) {
        this.rating = rating;
    }
}
