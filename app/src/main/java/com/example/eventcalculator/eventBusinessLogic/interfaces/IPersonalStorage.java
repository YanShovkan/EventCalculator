package com.example.eventcalculator.eventBusinessLogic.interfaces;

import com.example.eventcalculator.eventBusinessLogic.models.PersonalModel;

import java.util.List;

public interface IPersonalStorage {
    List<PersonalModel> getFullList();
    List<PersonalModel> getFilteredList(PersonalModel model);
    PersonalModel getElement(PersonalModel model);
    void insert(PersonalModel model);
    void update(PersonalModel model);
    void delete(PersonalModel model);
}
