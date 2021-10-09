package com.example.eventcalculator.eventBusinessLogic.interfaces;

import com.example.eventcalculator.eventBusinessLogic.models.PremiseModel;

import java.util.List;

public interface IPremiseStorage {
    List<PremiseModel> getFullList();
    List<PremiseModel> getFilteredList(PremiseModel model);
    PremiseModel getElement(PremiseModel model);
    void insert(PremiseModel model);
    void update(PremiseModel model);
    void delete(PremiseModel model);
}
