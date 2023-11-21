package com.example.cinemamanagement.model;

import java.util.List;

public class Screening {
    private int id;
    private String startTime;
    private String endTime;
    private String note;

    public Screening(int id, String startTime, String endTime, String note) {
        this.id = id;
        this.startTime = startTime;
        this.endTime = endTime;
        this.note = note;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getStartTime() {
        return startTime;
    }

    public void setStartTime(String startTime) {
        this.startTime = startTime;
    }

    public String getEndTime() {
        return endTime;
    }

    public void setEndTime(String endTime) {
        this.endTime = endTime;
    }

    public String getNote() {
        return note;
    }

    public void setNote(String note) {
        this.note = note;
    }
}
