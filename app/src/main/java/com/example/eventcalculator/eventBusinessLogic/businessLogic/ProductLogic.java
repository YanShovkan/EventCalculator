package com.example.eventcalculator.eventBusinessLogic.businessLogic;

import android.app.Application;

import com.example.eventcalculator.eventBusinessLogic.interfaces.IProductStorage;
import com.example.eventcalculator.eventBusinessLogic.models.EventModel;
import com.example.eventcalculator.eventBusinessLogic.models.ProductModel;

import java.io.Serializable;
import java.util.LinkedList;
import java.util.List;

public class ProductLogic extends Application {

    private IProductStorage productStorage;

    public ProductLogic(IProductStorage productStorage) {
        this.productStorage = productStorage;
    }

    public List<ProductModel> read(ProductModel model) {
        if (model == null)
        {
            return productStorage.getFullList();
        }
        if (model.getId() > 0)
        {
            List<ProductModel> list = new LinkedList<>();
            list.add(productStorage.getElement(model));
            return list;
        }
        return productStorage.getFilteredList(model);
    }

    public void createOrUpdate(ProductModel model) {
        ProductModel productModel = new ProductModel();
        productModel.setName(model.getName());
        ProductModel element = productStorage.getElement(productModel);
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
            productStorage.update(model);
        }
        else
        {
            productStorage.insert(model);
        }
    }

    public void delete(ProductModel model) {
        ProductModel productModel = new ProductModel();
        productModel.setId(model.getId());
        ProductModel element = productStorage.getElement(productModel);
        if (element == null)
        {
            try {
                throw new Exception("Элемент не найден");
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        productStorage.delete(model);
    }

    public int countPriceProducts(EventModel model) {
        List<ProductModel> products = read(null);
        int price = 0;
        for(ProductModel product : products) {
            if(product.getEventId() == model.getId()) {
                price += product.countPerPeople * model.getCountOfPeople() * product.price;
            }
        }
        return price;
    }

}