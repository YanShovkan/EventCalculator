package com.example.eventcalculator.eventBusinessLogic.interfaces;

import com.example.eventcalculator.eventBusinessLogic.models.ExtraModel;

import java.util.List;

public interface IExtraStorage {
    List<ExtraModel> getFullList();
    List<ExtraModel> getFilteredList(ExtraModel model);
    ExtraModel getElement(ExtraModel model);
    void insert(ExtraModel model);
    void update(ExtraModel model);
    void delete(ExtraModel model);
}
