package com.example.eventcalculator.eventBusinessLogic.models;

public class EquipmentModel {
    public int id;
    public String name;
    public int cost;
    public int eventId;

    public EquipmentModel(int id, String name, int cost, int eventId) {
        this.id = id;
        this.name = name;
        this.cost = cost;
        this.eventId = eventId;
    }

    public EquipmentModel() {}

    public int getEventId() {
        return eventId;
    }

    public void setEventId(int eventId) {
        this.eventId = eventId;
    }

    public int getCost() {
        return cost;
    }

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public void setCost(int cost) {
        this.cost = cost;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setName(String name) {
        this.name = name;
    }

}
