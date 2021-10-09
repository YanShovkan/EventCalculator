package com.example.eventcalculator.eventBusinessLogic.interfaces;

import com.example.eventcalculator.eventBusinessLogic.models.EventModel;
import java.util.List;

public interface IEventStorage {
    List<EventModel> getFullList();
    List<EventModel> getFilteredList(EventModel model);
    EventModel getElement(EventModel model);
    void insert(EventModel model);
    void update(EventModel model);
    void delete(EventModel model);
}
