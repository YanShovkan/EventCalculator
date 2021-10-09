package com.example.eventcalculator.eventBusinessLogic.models;

public class ProductModel {
    private String name;
    private double cost;
    private int countPerPerson;
    private int productId;
    private int eventId;

    public String getName() {
        return name;
    }

    public double getCost() {
        return cost;
    }

    public int getCountPerPerson() {
        return countPerPerson;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setCost(double cost) {
        this.cost = cost;
    }

    public void setCountPerPerson(int countPerPerson) {
        this.countPerPerson = countPerPerson;
    }

    public int getProductId() {
        return productId;
    }

    public void setProductId(int productId) {
        this.productId = productId;
    }

    public int getEventId() {
        return eventId;
    }

    public void setEventId(int eventId) {
        this.eventId = eventId;
    }



}
