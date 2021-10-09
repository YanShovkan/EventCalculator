package com.example.eventcalculator.database.Storages;

import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.view.View;

import com.example.eventcalculator.database.DatabaseHelper;
import com.example.eventcalculator.database.Models.Product;

import java.util.List;
import java.util.Vector;

public class ProductStorage {
    DatabaseHelper sqlHelper;
    SQLiteDatabase db;
    final String TABLE = "equipment";
    final String COLUMN_ID = "equipmentid";
    final String COLUMN_NAME = "equipment_name";
    final String COLUMN_PRICE = "equipment_price";
    final String COLUMN_COUNTPERPEOPLE = "equipment_countPerPeople";
    final String COLUMN_EVENTID = "eventid";

    public ProductStorage(View view) {
        sqlHelper = new DatabaseHelper(view.getContext());
        db = sqlHelper.getWritableDatabase();
    }

    public List<Product> GetFullList() {
        Cursor cursor = db.rawQuery("select * from " + TABLE, null);
        List<Product> list;
        list = new Vector<Product>();
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

            list.add(obj);
        } while (cursor.moveToNext());
        return list;
    }

    public List<Product> GetFilteredList(Product model) {
        Cursor cursor = db.rawQuery("select * from " + TABLE + " where "
                + COLUMN_EVENTID + " = " + model.eventId, null);
        List<Product> list;
        list = new Vector<Product>();
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

            list.add(obj);
        } while (cursor.moveToNext());
        return list;
    }

    public Product GetElement(Product model) {
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
        return obj;
    }

    public void Insert(Product model) {
        ContentValues content = new ContentValues();
        content.put(COLUMN_PRICE,model.price);
        content.put(COLUMN_COUNTPERPEOPLE,model.countPerPeople);
        content.put(COLUMN_EVENTID,model.eventId);
        content.put(COLUMN_NAME,model.name);
        db.insert(TABLE,null,content);
    }

    public void Update(Product model) {
        ContentValues content=new ContentValues();
        content.put(COLUMN_PRICE,model.price);
        content.put(COLUMN_COUNTPERPEOPLE,model.countPerPeople);
        content.put(COLUMN_EVENTID,model.eventId);
        content.put(COLUMN_NAME,model.name);
        String where = COLUMN_ID+" = "+model.id;
        db.update(TABLE,content,where,null);
    }

    public void Delete(Product model) {
        String where = COLUMN_ID+" = "+model.id;
        db.delete(TABLE,where,null);
    }
}
