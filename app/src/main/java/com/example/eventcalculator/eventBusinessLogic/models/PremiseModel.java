package com.example.eventcalculator.eventBusinessLogic.models;

public class PremiseModel {
    public int id;
    public String address;
    public int cost;
    public int eventId;

    public PremiseModel(int id, String address, int cost, int eventId) {
        this.id = id;
        this.address = address;
        this.cost = cost;
        this.eventId = eventId;
    }

    public PremiseModel() { }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setEventId(int eventId) {
        this.eventId = eventId;
    }

    public int getEventId() {
        return eventId;
    }

    public int getCost() {
        return cost;
    }

    public void setCost(int cost) {
        this.cost = cost;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

}
