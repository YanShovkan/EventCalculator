package com.example.eventcalculator.database.Storages;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import com.example.eventcalculator.database.DatabaseHelper;
import com.example.eventcalculator.eventBusinessLogic.interfaces.IProductStorage;
import com.example.eventcalculator.eventBusinessLogic.models.ProductModel;
import java.util.ArrayList;
import java.util.List;

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
        db = sqlHelper.getWritableDatabase();
    }

    public ProductStorage open() {
        db = sqlHelper.getWritableDatabase();
        return this;
    }

    public void close() {
        db.close();
    }

    public List<ProductModel> getFullList() {
        Cursor cursor = db.rawQuery("select * from " + TABLE, null);
        List<ProductModel> list = new ArrayList<>();
        if (!cursor.moveToFirst()) {
            return list;
        }

        do {
            ProductModel obj = new ProductModel();
            obj.id = cursor.getInt((int) cursor.getColumnIndex(COLUMN_ID));
            obj.price = cursor.getInt((int) cursor.getColumnIndex(COLUMN_PRICE));
            obj.countPerPeople = cursor.getInt((int) cursor.getColumnIndex(COLUMN_COUNTPERPEOPLE));
            obj.eventId = cursor.getInt((int) cursor.getColumnIndex(COLUMN_EVENTID));
            obj.name = cursor.getString((int) cursor.getColumnIndex(COLUMN_NAME));

            list.add(obj);
            cursor.moveToNext();
        } while (!cursor.isAfterLast());
        return list;
    }

    public List<ProductModel> getFilteredList(ProductModel model) {
        Cursor cursor = db.rawQuery("select * from " + TABLE + " where "
                + COLUMN_EVENTID + " = " + model.eventId, null);
        List<ProductModel> list = new ArrayList<ProductModel>();
        if (!cursor.moveToFirst()) {
            return list;
        }
        do {
            ProductModel obj = new ProductModel();
            obj.id = cursor.getInt((int) cursor.getColumnIndex(COLUMN_ID));
            obj.price = cursor.getInt((int) cursor.getColumnIndex(COLUMN_PRICE));
            obj.countPerPeople = cursor.getInt((int) cursor.getColumnIndex(COLUMN_COUNTPERPEOPLE));
            obj.eventId = cursor.getInt((int) cursor.getColumnIndex(COLUMN_EVENTID));
            obj.name = cursor.getString((int) cursor.getColumnIndex(COLUMN_NAME));

            list.add(obj);
            cursor.moveToNext();
        } while (!cursor.isAfterLast());
        return list;
    }

    public ProductModel getElement(ProductModel model) {
        Cursor cursor = db.rawQuery("select * from " + TABLE + " where "
                + COLUMN_ID + " = " + model.id, null);
        ProductModel obj = new ProductModel();
        if (!cursor.moveToFirst()) {
            return null;
        }
        obj.id = cursor.getInt((int) cursor.getColumnIndex(COLUMN_ID));
        obj.price = cursor.getInt((int) cursor.getColumnIndex(COLUMN_PRICE));
        obj.countPerPeople = cursor.getInt((int) cursor.getColumnIndex(COLUMN_COUNTPERPEOPLE));
        obj.eventId = cursor.getInt((int) cursor.getColumnIndex(COLUMN_EVENTID));
        obj.name = cursor.getString((int) cursor.getColumnIndex(COLUMN_NAME));
        return obj;
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
        String where = COLUMN_ID+" = "+model.id;
        db.delete(TABLE,where,null);
    }
}
