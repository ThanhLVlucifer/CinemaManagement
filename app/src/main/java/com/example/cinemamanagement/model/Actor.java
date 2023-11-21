package com.example.cinemamanagement.model;

public class Actor {
    private int id;
    private String name;
    private String dob;
    private String gender;
    private String nationality;
    private String bio;
    private String image;

    public Actor() {
    }

    public Actor(int id, String name, String dob, String gender, String nationality, String bio, String image) {
        this.id = id;
        this.name = name;
        this.dob = dob;
        this.gender = gender;
        this.nationality = nationality;
        this.bio = bio;
        this.image = image;
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

    public String getDob() {
        return dob;
    }

    public void setDob(String dob) {
        this.dob = dob;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public String getNationality() {
        return nationality;
    }

    public void setNationality(String nationality) {
        this.nationality = nationality;
    }

    public String getBio() {
        return bio;
    }

    public void setBio(String bio) {
        this.bio = bio;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }
}
