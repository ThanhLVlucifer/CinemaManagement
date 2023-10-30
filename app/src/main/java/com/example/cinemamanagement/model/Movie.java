package com.example.cinemamanagement.model;

import java.io.Serializable;

public class Movie implements Serializable {
    private int id;
    private String name;
    private String description;
    private boolean popular;

    public Movie(int id, String name, String description, boolean popular) {
        this.id = id;
        this.name = name;
        this.description = description;
        this.popular = popular;
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

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public boolean isPopular() {
        return popular;
    }

    public void setPopular(boolean popular) {
        this.popular = popular;
    }
}
