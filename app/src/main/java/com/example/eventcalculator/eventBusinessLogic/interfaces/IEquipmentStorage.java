package com.example.eventcalculator.eventBusinessLogic.interfaces;

import com.example.eventcalculator.eventBusinessLogic.models.EquipmentModel;

import java.util.List;

public interface IEquipmentStorage {
    List<EquipmentModel> getFullList();
    List<EquipmentModel> getFilteredList(EquipmentModel model);
    EquipmentModel getElement(EquipmentModel model);
    void insert(EquipmentModel model);
    void update(EquipmentModel model);
    void delete(EquipmentModel model);
}
