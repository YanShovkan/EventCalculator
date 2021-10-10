package com.example.eventcalculator.eventBusinessLogic.businessLogic;

import android.app.Application;

import com.example.eventcalculator.database.Storages.EquipmentStorage;
import com.example.eventcalculator.database.Storages.EventStorage;
import com.example.eventcalculator.database.Storages.ExtraStorage;
import com.example.eventcalculator.database.Storages.PersonalStorage;
import com.example.eventcalculator.database.Storages.PremiseStorage;
import com.example.eventcalculator.database.Storages.ProductStorage;
import com.example.eventcalculator.eventBusinessLogic.interfaces.IEventStorage;
import com.example.eventcalculator.eventBusinessLogic.models.EventModel;
import com.example.eventcalculator.eventBusinessLogic.models.ProductModel;

import java.util.LinkedList;
import java.util.List;

public class EventLogic extends Application {
    private IEventStorage eventStorage;

    ProductStorage productStorage = new ProductStorage(this);
    ProductLogic productLogic = new ProductLogic(productStorage);

    PremiseStorage premiseStorage = new PremiseStorage(this);
    PremiseLogic premiseLogic = new PremiseLogic(premiseStorage);

    EquipmentStorage equipmentStorage = new EquipmentStorage(this);
    EquipmentLogic equipmentLogic = new EquipmentLogic(equipmentStorage);

    PersonalStorage personalStorage = new PersonalStorage(this);
    PersonalLogic personalLogic = new PersonalLogic(personalStorage);

    ExtraStorage extraStorage = new ExtraStorage(this);
    ExtraLogic extraLogic = new ExtraLogic(extraStorage);

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

    public int globalPrice(EventModel eventModel) {
        return productLogic.countPriceProducts(eventModel)
                + equipmentLogic.countPricePersonal(eventModel)
                + extraLogic.countPriceExtra(eventModel)
                + personalLogic.countPricePersonal(eventModel)
                + premiseLogic.countPremiseCost(eventModel);
    }

}
