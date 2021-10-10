package com.example.eventcalculator.eventBusinessLogic.businessLogic;

import com.example.eventcalculator.eventBusinessLogic.interfaces.IEquipmentStorage;
import com.example.eventcalculator.eventBusinessLogic.models.EquipmentModel;
import com.example.eventcalculator.eventBusinessLogic.models.EventModel;
import com.example.eventcalculator.eventBusinessLogic.models.PersonalModel;

import java.util.LinkedList;
import java.util.List;

public class EquipmentLogic {
    private final IEquipmentStorage equipmentStorage;

    public EquipmentLogic(IEquipmentStorage equipmentStorage) {
        this.equipmentStorage = equipmentStorage;
    }

    public List<EquipmentModel> Read(EquipmentModel model) {
        if (model == null)
        {
            return equipmentStorage.getFullList();
        }
        if (model.getId() > 0)
        {
            List<EquipmentModel> list = new LinkedList<>();
            list.add(equipmentStorage.getElement(model));
            return list;
        }
        return equipmentStorage.getFilteredList(model);
    }

    public void CreateOrUpdate(EquipmentModel model) {
        EquipmentModel EquipmentModel = new EquipmentModel();
        EquipmentModel.setName(model.getName());
        EquipmentModel element = equipmentStorage.getElement(EquipmentModel);
        if (element != null && element.getId() != model.getId())
        {
            try {
                throw new Exception("Уже есть компонент с таким названием");
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        if (model.getId() > 0)
        {
            equipmentStorage.update(model);
        }
        else
        {
            equipmentStorage.insert(model);
        }
    }

    public void Delete(EquipmentModel model) {
        EquipmentModel EquipmentModel = new EquipmentModel();
        EquipmentModel.setId(model.getId());
        EquipmentModel element = equipmentStorage.getElement(EquipmentModel);
        if (element == null)
        {
            try {
                throw new Exception("Элемент не найден");
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        equipmentStorage.delete(model);
    }

    public int countPricePersonal(EventModel model) {
        List<EquipmentModel> equipments = Read(null);
        int price = 0;
        for(EquipmentModel equipment : equipments) {
            if(equipment.getEventId() == model.getId()) {
                price += equipment.cost;
            }
        }
        return price;
    }

}
