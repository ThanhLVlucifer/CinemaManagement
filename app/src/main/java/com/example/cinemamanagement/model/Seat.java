package com.example.cinemamanagement.model;

import android.graphics.Color;

public class Seat {
    private int id;
    private String type;
    private String row;
    private int column;
    private int color;

    public Seat() {
    }

    public Seat(int id, String type, String row, int column) {
        this.id = id;
        this.type = type;
        this.row = row;
        this.column = column;
        this.color = Color.WHITE;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getRow() {
        return row;
    }

    public void setRow(String row) {
        this.row = row;
    }

    public int getColumn() {
        return column;
    }

    public void setColumn(int column) {
        this.column = column;
    }

    public int getColor() {
        return color;
    }

    public void setColor(int color) {
        this.color = color;
    }
}
