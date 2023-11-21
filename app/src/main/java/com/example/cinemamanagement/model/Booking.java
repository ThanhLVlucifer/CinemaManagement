package com.example.cinemamanagement.model;

import java.util.List;

public class Booking {
    private long id;

    private String idUser;
    private String nameMovie;
    private String releaseDate;
    private String room;
    private String showtime;
    private int ticketQuantity;
    private String selectedSeat;
    private String selectedProduct;
    private int paymentMethod;
    private int totalPayment;

    private int totalTicketSales;

    public Booking() {
    }

    public Booking(long id, String idUser, String nameMovie, String releaseDate, String room, String showtime, int ticketQuantity, String selectedSeat, String selectedProduct, int paymentMethod, int totalPayment, int totalTicketSales) {
        this.id = id;
        this.idUser = idUser;
        this.nameMovie = nameMovie;
        this.releaseDate = releaseDate;
        this.room = room;
        this.showtime = showtime;
        this.ticketQuantity = ticketQuantity;
        this.selectedSeat = selectedSeat;
        this.selectedProduct = selectedProduct;
        this.paymentMethod = paymentMethod;
        this.totalPayment = totalPayment;
        this.totalTicketSales = totalTicketSales;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getIdUser() {
        return idUser;
    }

    public void setIdUser(String idUser) {
        this.idUser = idUser;
    }

    public String getNameMovie() {
        return nameMovie;
    }

    public void setNameMovie(String nameMovie) {
        this.nameMovie = nameMovie;
    }

    public String getReleaseDate() {
        return releaseDate;
    }

    public void setReleaseDate(String releaseDate) {
        this.releaseDate = releaseDate;
    }

    public String getRoom() {
        return room;
    }

    public void setRoom(String room) {
        this.room = room;
    }

    public String getShowtime() {
        return showtime;
    }

    public void setShowtime(String showtime) {
        this.showtime = showtime;
    }

    public int getTicketQuantity() {
        return ticketQuantity;
    }

    public void setTicketQuantity(int ticketQuantity) {
        this.ticketQuantity = ticketQuantity;
    }

    public String getSelectedSeat() {
        return selectedSeat;
    }

    public void setSelectedSeat(String selectedSeat) {
        this.selectedSeat = selectedSeat;
    }

    public String getSelectedProduct() {
        return selectedProduct;
    }

    public void setSelectedProduct(String selectedProduct) {
        this.selectedProduct = selectedProduct;
    }

    public int getPaymentMethod() {
        return paymentMethod;
    }

    public void setPaymentMethod(int paymentMethod) {
        this.paymentMethod = paymentMethod;
    }

    public int getTotalPayment() {
        return totalPayment;
    }

    public void setTotalPayment(int totalPayment) {
        this.totalPayment = totalPayment;
    }

    public int getTotalTicketSales() {
        return totalTicketSales;
    }

    public void setTotalTicketSales(int totalTicketSales) {
        this.totalTicketSales = totalTicketSales;
    }
}
