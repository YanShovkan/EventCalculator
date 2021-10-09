package com.example.eventcalculator.eventBusinessLogic.interfaces;

import com.example.eventcalculator.eventBusinessLogic.models.ProductModel;
import java.util.List;

public interface IProductStorage {
    List<ProductModel> getFullList();
    List<ProductModel> getFilteredList(ProductModel model);
    ProductModel getElement(ProductModel model);
    void insert(ProductModel model);
    void update(ProductModel model);
    void delete(ProductModel model);
}
