package com.example.eventcalculator.eventBusinessLogic.businessLogic;

import com.example.eventcalculator.eventBusinessLogic.interfaces.IEventStorage;
import com.example.eventcalculator.eventBusinessLogic.models.EventModel;

import java.util.LinkedList;
import java.util.List;

public class EventLogic {
    private IEventStorage eventStorage;

    public EventLogic(IEventStorage eventStorage) {
        this.eventStorage = eventStorage;
    }

    public List<EventModel> read(EventModel model) {
        if (model == null)
        {
            return eventStorage.getFullList();
        }
        if (model.getId() > 0)
        {
            List<EventModel> list = new LinkedList<>();
            list.add(eventStorage.getElement(model));
            return list;
        }
        return eventStorage.getFilteredList(model);
    }

    public void createOrUpdate(EventModel model) {
        EventModel eventModel = new EventModel();
        eventModel.setName(model.getName());
        EventModel element = eventStorage.getElement(eventModel);
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
            eventStorage.update(model);
        }
        else
        {
            eventStorage.insert(model);
        }
    }

    public void delete(EventModel model) {
        EventModel eventModel = new EventModel();
        eventModel.setId(model.getId());
        EventModel element = eventStorage.getElement(eventModel);
        if (element == null)
        {
            try {
                throw new Exception("Элемент не найден");
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        eventStorage.delete(model);
    }
}