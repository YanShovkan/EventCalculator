package com.example.eventcalculator.eventBusinessLogic.models;

public class ProductModel {
    public int id;
    public String name;
    public int price;
    public int countPerPeople;
    public int eventId;

    public ProductModel(String name, int price, int countPerPeople, int eventId) {
        this.name = name;
        this.price = price;
        this.countPerPeople = countPerPeople;
        this.eventId = eventId;
    }

    public ProductModel() {

    }

    public int getEventId() {
        return eventId;
    }

    public void setEventId(int eventId) {
        this.eventId = eventId;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getCountPerPeople() {
        return countPerPeople;
    }

    public int getPrice() {
        return price;
    }

    public void setCountPerPeople(int countPerPeople) {
        this.countPerPeople = countPerPeople;
    }

    public void setPrice(int price) {
        this.price = price;
    }

}
