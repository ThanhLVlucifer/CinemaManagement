package com.example.cinemamanagement.model;

import java.io.Serializable;
import java.util.List;

public class Movie implements Serializable {
    private int id;
    private String name;
    private String image;
    private String genre;
    private String director;
    private String actor;
    private String releaseDate;
    private String duration;
    private double evaluate;
    private String rating;
    private int ticketPrice;
    private boolean suggest;
    private String description;

    private String trailer;

    public Movie() {
    }

    public Movie(int id, String name, String image, String genre, String director, String actor, String releaseDate, String duration, double evaluate, String rating, int ticketPrice, boolean suggest, String description, String trailer) {
        this.id = id;
        this.name = name;
        this.image = image;
        this.genre = genre;
        this.director = director;
        this.actor = actor;
        this.releaseDate = releaseDate;
        this.duration = duration;
        this.evaluate = evaluate;
        this.rating = rating;
        this.ticketPrice = ticketPrice;
        this.suggest = suggest;
        this.description = description;
        this.trailer = trailer;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public String getGenre() {
        return genre;
    }

    public void setGenre(String genre) {
        this.genre = genre;
    }

    public String getDirector() {
        return director;
    }

    public void setDirector(String director) {
        this.director = director;
    }

    public String getActor() {
        return actor;
    }

    public void setActor(String actor) {
        this.actor = actor;
    }

    public String getReleaseDate() {
        return releaseDate;
    }

    public void setReleaseDate(String releaseDate) {
        this.releaseDate = releaseDate;
    }

    public String getDuration() {
        return duration;
    }

    public void setDuration(String duration) {
        this.duration = duration;
    }

    public double getEvaluate() {
        return evaluate;
    }

    public void setEvaluate(double evaluate) {
        this.evaluate = evaluate;
    }

    public String getRating() {
        return rating;
    }

    public void setRating(String rating) {
        this.rating = rating;
    }

    public int getTicketPrice() {
        return ticketPrice;
    }

    public void setTicketPrice(int ticketPrice) {
        this.ticketPrice = ticketPrice;
    }

    public boolean isSuggest() {
        return suggest;
    }

    public void setSuggest(boolean suggest) {
        this.suggest = suggest;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getTrailer() {
        return trailer;
    }

    public void setTrailer(String trailer) {
        this.trailer = trailer;
    }
}
