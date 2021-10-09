package com.example.eventcalculator.eventBusinessLogic.businessLogic;

import com.example.eventcalculator.eventBusinessLogic.interfaces.IExtraStorage;
import com.example.eventcalculator.eventBusinessLogic.models.ExtraModel;

import java.util.LinkedList;
import java.util.List;

public class ExtraLogic {

    private final IExtraStorage extraStorage;

    public ExtraLogic(IExtraStorage extraStorage) {
        this.extraStorage = extraStorage;
    }

    public List<ExtraModel> Read(ExtraModel model) {
        if (model == null)
        {
            return extraStorage.getFullList();
        }
        if (model.getId() > 0)
        {
            List<ExtraModel> list = new LinkedList<>();
            list.add(extraStorage.getElement(model));
            return list;
        }
        return extraStorage.getFilteredList(model);
    }

    public void CreateOrUpdate(ExtraModel model) {
        ExtraModel extraModel = new ExtraModel();
        extraModel.setName(model.getName());
        ExtraModel element = extraStorage.getElement(extraModel);
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
            extraStorage.update(model);
        }
        else
        {
            extraStorage.insert(model);
        }
    }

    public void Delete(ExtraModel model) {
        ExtraModel extraModel = new ExtraModel();
        extraModel.setId(model.getId());
        ExtraModel element = extraStorage.getElement(extraModel);
        if (element == null)
        {
            try {
                throw new Exception("Элемент не найден");
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        extraStorage.delete(model);
    }

}
