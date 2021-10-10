package com.example.eventcalculator.eventBusinessLogic.businessLogic;

import com.example.eventcalculator.eventBusinessLogic.interfaces.IPersonalStorage;
import com.example.eventcalculator.eventBusinessLogic.models.EventModel;
import com.example.eventcalculator.eventBusinessLogic.models.PersonalModel;
import com.example.eventcalculator.eventBusinessLogic.models.PremiseModel;
import com.example.eventcalculator.eventBusinessLogic.models.ProductModel;

import java.util.LinkedList;
import java.util.List;

public class PersonalLogic {

    private final IPersonalStorage personalStorage;

    public PersonalLogic(IPersonalStorage personalStorage) {
        this.personalStorage = personalStorage;
    }

    public List<PersonalModel> Read(PersonalModel model) {
        if (model == null)
        {
            return personalStorage.getFullList();
        }
        if (model.getId() > 0)
        {
            List<PersonalModel> list = new LinkedList<>();
            list.add(personalStorage.getElement(model));
            return list;
        }
        return personalStorage.getFilteredList(model);
    }

    public void CreateOrUpdate(PersonalModel model) {
        PersonalModel productModel = new PersonalModel();
        productModel.setName(model.getName());
        PersonalModel element = personalStorage.getElement(productModel);
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
            personalStorage.update(model);
        }
        else
        {
            personalStorage.insert(model);
        }
    }

    public void Delete(PersonalModel model) {
        PersonalModel personalModel = new PersonalModel();
        personalModel.setId(model.getId());
        PersonalModel element = personalStorage.getElement(personalModel);
        if (element == null)
        {
            try {
                throw new Exception("Элемент не найден");
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        personalStorage.delete(model);
    }

    public int countPricePersonal(EventModel model) {
        List<PersonalModel> persons = Read(null);
        int price = 0;
        for(PersonalModel person : persons) {
            if(person.getEventId() == model.getId()) {
                price += person.payment;
            }
        }
        return price;
    }
}