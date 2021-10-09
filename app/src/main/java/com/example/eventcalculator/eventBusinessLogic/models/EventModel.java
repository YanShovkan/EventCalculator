package com.example.eventcalculator.eventBusinessLogic.models;

import java.util.Date;

public class EventModel {
    public int id;
    public String name;
    public Date dateFrom;
    public Date dateTo;
    public int countOfPeople;

    public EventModel(int id) {
        this.id = id;
    }

    public EventModel() {

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

    public Date getDateFrom() {
        return dateFrom;
    }

    public Date getDateTo() {
        return dateTo;
    }

    public int getCountOfPeople() {
        return countOfPeople;
    }

    public void setCountOfPeople(int countOfPeople) {
        this.countOfPeople = countOfPeople;
    }

    public void setDateFrom(Date dateFrom) {
        this.dateFrom = dateFrom;
    }

    public void setDateTo(Date dateTo) {
        this.dateTo = dateTo;
    }

}