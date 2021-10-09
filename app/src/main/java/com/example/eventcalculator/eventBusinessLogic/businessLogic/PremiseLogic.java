package com.example.eventcalculator.eventBusinessLogic.businessLogic;

import com.example.eventcalculator.eventBusinessLogic.interfaces.IPremiseStorage;
import com.example.eventcalculator.eventBusinessLogic.models.PremiseModel;

import java.util.LinkedList;
import java.util.List;

public class PremiseLogic {
    private IPremiseStorage premiseStorage;

    public PremiseLogic(IPremiseStorage premiseStorage) {
        this.premiseStorage = premiseStorage;
    }

    public List<PremiseModel> read(PremiseModel model) {
        if (model == null)
        {
            return premiseStorage.getFullList();
        }
        if (model.getId() > 0)
        {
            List<PremiseModel> list = new LinkedList<>();
            list.add(premiseStorage.getElement(model));
            return list;
        }
        return premiseStorage.getFilteredList(model);
    }

    public void createOrUpdate(PremiseModel model) {
        PremiseModel premiseModel = new PremiseModel();
        premiseModel.setId(model.getId());
        PremiseModel element = premiseStorage.getElement(premiseModel);
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
            premiseStorage.update(model);
        }
        else
        {
            premiseStorage.insert(model);
        }
    }

    public void delete(PremiseModel model) {
        PremiseModel premiseModel = new PremiseModel();
        premiseModel.setId(model.getId());
        PremiseModel element = premiseStorage.getElement(premiseModel);
        if (element == null)
        {
            try {
                throw new Exception("Элемент не найден");
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        premiseStorage.delete(model);
    }
}
