package com.example.eventcalculator.database.Storages;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.example.eventcalculator.database.DatabaseHelper;
import com.example.eventcalculator.database.Models.Product;
import com.example.eventcalculator.eventBusinessLogic.interfaces.IProductStorage;
import com.example.eventcalculator.eventBusinessLogic.models.ProductModel;

import java.util.ArrayList;
import java.util.List;
import java.util.Vector;

public class ProductStorage implements IProductStorage {
    DatabaseHelper sqlHelper;
    SQLiteDatabase db;
    final String TABLE = "product";
    final String COLUMN_ID = "productid";
    final String COLUMN_NAME = "product_name";
    final String COLUMN_PRICE = "product_cost";
    final String COLUMN_COUNTPERPEOPLE = "count_per_person";
    final String COLUMN_EVENTID = "eventid";

    public ProductStorage(Context context) {
        sqlHelper = new DatabaseHelper(context.getApplicationContext());
    }

    public ProductStorage open(){
        db = sqlHelper.getWritableDatabase();
        return this;
    }

    public void close(){
        db.close();
    }

    public List<ProductModel> getFullList() {
        Cursor cursor = db.rawQuery("select * from " + TABLE, null);
        List<ProductModel> list = new ArrayList<>();
        if (!cursor.moveToFirst()) {
            return list;
        }
        cursor.moveToNext();
        do {

            Product obj = new Product();
            obj.id = cursor.getInt((int) cursor.getColumnIndex(COLUMN_ID));
            obj.price = cursor.getInt((int) cursor.getColumnIndex(COLUMN_PRICE));
            obj.countPerPeople = cursor.getInt((int) cursor.getColumnIndex(COLUMN_COUNTPERPEOPLE));
            obj.eventId = cursor.getInt((int) cursor.getColumnIndex(COLUMN_EVENTID));
            obj.name = cursor.getString((int) cursor.getColumnIndex(COLUMN_NAME));

            list.add(createModel(obj));
            cursor.moveToNext();
        } while (!cursor.isLast());
        return list;
    }

    public List<ProductModel> getFilteredList(ProductModel model) {
        Cursor cursor = db.rawQuery("select * from " + TABLE + " where "
                + COLUMN_EVENTID + " = " + model.eventId, null);
        List<ProductModel> list = new Vector<ProductModel>();
        if (!cursor.moveToFirst()) {
            return list;
        }
        do {
            Product obj = new Product();
            obj.id = cursor.getInt((int) cursor.getColumnIndex(COLUMN_ID));
            obj.price = cursor.getInt((int) cursor.getColumnIndex(COLUMN_PRICE));
            obj.countPerPeople = cursor.getInt((int) cursor.getColumnIndex(COLUMN_COUNTPERPEOPLE));
            obj.eventId = cursor.getInt((int) cursor.getColumnIndex(COLUMN_EVENTID));
            obj.name = cursor.getString((int) cursor.getColumnIndex(COLUMN_NAME));

            list.add(createModel(obj));
        } while (cursor.moveToNext());
        return list;
    }

    public ProductModel getElement(ProductModel model) {
        Cursor cursor = db.rawQuery("select * from " + TABLE + " where "
                + COLUMN_ID + " = " + model.id, null);
        Product obj = new Product();
        if (!cursor.moveToFirst()) {
            return null;
        }
        obj.id = cursor.getInt((int) cursor.getColumnIndex(COLUMN_ID));
        obj.price = cursor.getInt((int) cursor.getColumnIndex(COLUMN_PRICE));
        obj.countPerPeople = cursor.getInt((int) cursor.getColumnIndex(COLUMN_COUNTPERPEOPLE));
        obj.eventId = cursor.getInt((int) cursor.getColumnIndex(COLUMN_EVENTID));
        obj.name = cursor.getString((int) cursor.getColumnIndex(COLUMN_NAME));
        return createModel(obj);
    }

    public void insert(ProductModel model) {
        ContentValues content = new ContentValues();
        content.put(COLUMN_PRICE,model.price);
        content.put(COLUMN_COUNTPERPEOPLE,model.countPerPeople);
        content.put(COLUMN_EVENTID,model.eventId);
        content.put(COLUMN_NAME,model.name);
        db.insert(TABLE,null,content);
    }

    public void update(ProductModel model) {
        ContentValues content=new ContentValues();
        content.put(COLUMN_PRICE,model.price);
        content.put(COLUMN_COUNTPERPEOPLE,model.countPerPeople);
        content.put(COLUMN_EVENTID,model.eventId);
        content.put(COLUMN_NAME,model.name);
        String where = COLUMN_ID+" = "+model.id;
        db.update(TABLE,content,where,null);
    }

    public void delete(ProductModel model) {
        String whereClause = COLUMN_ID + " = " + model.getId();
        String[] whereArgs = new String[] { String.valueOf(model.getId()) };
        db.delete(TABLE, whereClause, whereArgs);
    }

    public ProductModel createModel(Product product) {
        ProductModel productModel = new ProductModel();
        productModel.setId(product.id);
        productModel.setPrice(product.price);
        productModel.setCountPerPeople(product.countPerPeople);
        productModel.setEventId(product.eventId);
        productModel.setName(product.name);

        return productModel;
    }
}
