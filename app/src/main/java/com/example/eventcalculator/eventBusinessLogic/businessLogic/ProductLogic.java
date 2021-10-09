package com.example.eventcalculator.eventBusinessLogic.businessLogic;

import com.example.eventcalculator.eventBusinessLogic.interfaces.IProductStorage;
import com.example.eventcalculator.eventBusinessLogic.models.ProductModel;
import java.util.LinkedList;
import java.util.List;

public class ProductLogic {

    private final IProductStorage productStorage;

    public ProductLogic(IProductStorage productStorage) {
        this.productStorage = productStorage;
    }

    public List<ProductModel> Read(ProductModel model) {
        if (model == null)
        {
            return productStorage.getFullList();
        }
        if (model.getProductId() > 0)
        {
            List<ProductModel> list = new LinkedList<>();
            list.add(productStorage.getElement(model));
            return list;
        }
        return productStorage.getFilteredList(model);
    }

    public void CreateOrUpdate(ProductModel model) {
        ProductModel productModel = new ProductModel();
        productModel.setName(model.getName());
        ProductModel element = productStorage.getElement(productModel);
        if (element != null && element.getProductId() != model.getProductId())
        {
            try {
                throw new Exception("Уже есть компонент с таким названием");
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        if (model.getProductId() > 0)
        {
            productStorage.update(model);
        }
        else
        {
            productStorage.insert(model);
        }
    }

    public void Delete(ProductModel model) {
        ProductModel productModel = new ProductModel();
        productModel.setProductId(model.getProductId());
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
}