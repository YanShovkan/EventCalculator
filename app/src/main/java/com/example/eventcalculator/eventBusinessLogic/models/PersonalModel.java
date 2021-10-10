package com.example.eventcalculator.eventBusinessLogic.models;

public class PersonalModel {
    public int id;
    public String position;
    public String name;
    public int payment;
    public int eventId;

    public PersonalModel(int id, String position, String name, int payment) {
        this.id = id;
        this.position = position;
        this.name = name;
        this.payment = payment;
    }

    public PersonalModel(String position, String name, int payment) {
        this.position = position;
        this.name = name;
        this.payment = payment;
    }

    public PersonalModel() {
    }

    public int getEventId() {
        return eventId;
    }

    public void setEventId(int eventId) {
        this.eventId = eventId;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public int getId() {
        return id;
    }

    public void setPayment(int payment) {
        this.payment = payment;
    }

    public int getPayment() {
        return payment;
    }

    public String getPosition() {
        return position;
    }

    public void setPosition(String position) {
        this.position = position;
    }

}
