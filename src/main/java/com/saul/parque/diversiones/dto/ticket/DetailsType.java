package com.saul.parque.diversiones.dto.ticket;

public class DetailsType {

    private final double price;

    private double amount;

    private long ticketsSold;

    public DetailsType(double price) {
        this.price = price;
        this.amount = 0;
        this.ticketsSold = 0;
    }

    public void sum() {
        ticketsSold++;
    }

    public void amount() {
        this.amount = this.price * this.ticketsSold;
    }

    public double getAmount() {
        return this.amount;
    }
}
