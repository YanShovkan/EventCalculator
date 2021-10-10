package com.example.eventcalculator.eventBusinessLogic.interfaces;

import com.example.eventcalculator.eventBusinessLogic.models.HandoutModel;

import java.util.List;

public interface IHandoutStorage {
    List<HandoutModel> getFullList();
    List<HandoutModel> getFilteredList(HandoutModel model);
    HandoutModel getElement(HandoutModel model);
    void insert(HandoutModel model);
    void update(HandoutModel model);
    void delete(HandoutModel model);
}
