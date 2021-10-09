package com.example.eventcalculator.eventBusinessLogic.models;

public class ProductModel {
    private String name;
    private double price;
    private int countPerPeople;
    private int id;

    public String getName() {
        return name;
    }

    public double getPrice() {
        return price;
    }

    public int getCountPerPeople() {
        return countPerPeople;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public void setCountPerPeople(int countPerPeople) {
        this.countPerPeople = countPerPeople;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

}
