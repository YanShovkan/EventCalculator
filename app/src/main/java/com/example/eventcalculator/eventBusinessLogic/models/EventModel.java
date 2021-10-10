package com.example.eventcalculator.eventBusinessLogic.models;

import java.util.Date;

public class EventModel {
    public int id;
    public String name;

    public int dayCount;
    public int countOfPeople;

    public EventModel(String name, int dayCount, int countOfPeople) {
        this.name = name;
        this.dayCount = dayCount;
        this.countOfPeople = countOfPeople;
    }

    public EventModel() {}

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

    public int getCountOfPeople() {
        return countOfPeople;
    }

    public void setCountOfPeople(int countOfPeople) {
        this.countOfPeople = countOfPeople;
    }

    public int getDayCount() {
        return dayCount;
    }

    public void setDayCount(int dayCount) {
        this.dayCount = dayCount;
    }

}