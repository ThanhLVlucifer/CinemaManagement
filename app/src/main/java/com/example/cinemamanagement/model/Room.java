package com.example.cinemamanagement.model;

import java.util.List;

public class Room {
    private int id;
    private String name;
    private int capacity;
    private String location;
    private String projectorModel;
    private String soundSystemModel;
    private String amenities;
    private String status;

    public Room() {
    }

    public Room(int id, String name, int capacity, String location, String projectorModel, String soundSystemModel, String amenities, String status) {
        this.id = id;
        this.name = name;
        this.capacity = capacity;
        this.location = location;
        this.projectorModel = projectorModel;
        this.soundSystemModel = soundSystemModel;
        this.amenities = amenities;
        this.status = status;
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

    public int getCapacity() {
        return capacity;
    }

    public void setCapacity(int capacity) {
        this.capacity = capacity;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public String getProjectorModel() {
        return projectorModel;
    }

    public void setProjectorModel(String projectorModel) {
        this.projectorModel = projectorModel;
    }

    public String getSoundSystemModel() {
        return soundSystemModel;
    }

    public void setSoundSystemModel(String soundSystemModel) {
        this.soundSystemModel = soundSystemModel;
    }

    public String getAmenities() {
        return amenities;
    }

    public void setAmenities(String amenities) {
        this.amenities = amenities;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }
}
