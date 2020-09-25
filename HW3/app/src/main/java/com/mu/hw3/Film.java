package com.mu.hw3;

import com.google.firebase.firestore.Exclude;

import java.io.Serializable;

class Film implements Serializable {

    @Exclude private String id;

    private String title;
    private String director;
    private String releaseDate;
    private String category;

    public Film() {

    }

    public Film(String title, String director, String releaseDate, String category) {
        this.title = title;
        this.director = director;
        this.releaseDate = releaseDate;
        this.category = category;
    }

    public String getId() { return id; }

    public void setId(String id) { this.id = id; }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDirector() {
        return director;
    }

    public void setDirector(String director) {
        this.director = director;
    }

    public String getReleaseDate() {
        return releaseDate;
    }

    public void setReleaseDate(String releaseDate) {
        this.releaseDate = releaseDate;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }
}
