package com.example.eventcalculator.eventBusinessLogic.businessLogic;

import com.example.eventcalculator.eventBusinessLogic.interfaces.IHandoutStorage;
import com.example.eventcalculator.eventBusinessLogic.models.EventModel;
import com.example.eventcalculator.eventBusinessLogic.models.HandoutModel;
import java.util.LinkedList;
import java.util.List;

public class HandoutLogic {
    private final IHandoutStorage handoutStorage;

    public HandoutLogic(IHandoutStorage handoutStorage) {
        this.handoutStorage = handoutStorage;
    }

    public List<HandoutModel> Read(HandoutModel model) {
        if (model == null)
        {
            return handoutStorage.getFullList();
        }
        if (model.getId() > 0)
        {
            List<HandoutModel> list = new LinkedList<>();
            list.add(handoutStorage.getElement(model));
            return list;
        }
        return handoutStorage.getFilteredList(model);
    }

    public void CreateOrUpdate(HandoutModel model) {
        HandoutModel handoutModel = new HandoutModel();
        handoutModel.setName(model.getName());
        HandoutModel element = handoutStorage.getElement(handoutModel);
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
            handoutStorage.update(model);
        }
        else
        {
            handoutStorage.insert(model);
        }
    }

    public void Delete(HandoutModel model) {
        HandoutModel handoutModel = new HandoutModel();
        handoutModel.setId(model.getId());
        HandoutModel element = handoutStorage.getElement(handoutModel);
        if (element == null)
        {
            try {
                throw new Exception("Элемент не найден");
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        handoutStorage.delete(model);
    }

    public int countPriceHandout(EventModel model) {
        List<HandoutModel> handouts = Read(null);
        int price = 0;
        for(HandoutModel handout : handouts) {
            if(handout.getEventId() == model.getId()) {
                price += handout.price * model.getCountOfPeople();
            }
        }
        return price;
    }

}
