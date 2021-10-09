package com.example.eventcalculator.database.Storages;

import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.view.View;

import com.example.eventcalculator.database.DatabaseHelper;
import com.example.eventcalculator.database.Models.Equipment;

import java.util.*;

public class EquipmentStorage {

    DatabaseHelper sqlHelper;
    SQLiteDatabase db;
    final String TABLE = "equipment";
    final String COLUMN_ID = "equipmentid";
    final String COLUMN_NAME = "equipment_name";
    final String COLUMN_COST = "equipment_cost";
    final String COLUMN_EVENTID = "eventid";

    public EquipmentStorage(View view) {
        sqlHelper = new DatabaseHelper(view.getContext());
        db = sqlHelper.getWritableDatabase();
    }

    public List<Equipment> GetFullList() {
        Cursor cursor = db.rawQuery("select * from " + TABLE, null);
        List<Equipment> list;
        list = new Vector<Equipment>();
        if (!cursor.moveToFirst()) {
            return list;
        }
        do {
            Equipment obj = new Equipment();
            obj.id = cursor.getInt((int) cursor.getColumnIndex(COLUMN_ID));
            obj.cost = cursor.getInt((int) cursor.getColumnIndex(COLUMN_COST));
            obj.eventId = cursor.getInt((int) cursor.getColumnIndex(COLUMN_EVENTID));
            obj.name = cursor.getString((int) cursor.getColumnIndex(COLUMN_NAME));
        } while (cursor.moveToNext());
        return list;
    }

    public List<Equipment> GetFilteredList(Equipment model) {
        Cursor cursor = db.rawQuery("select * from " + TABLE + " where "
                + COLUMN_EVENTID + " = " + model.eventId, null);
        List<Equipment> list;
        list = new Vector<Equipment>();
        if (!cursor.moveToFirst()) {
            return list;
        }
        do {
            Equipment obj = new Equipment();
            obj.id = cursor.getInt((int) cursor.getColumnIndex(COLUMN_ID));
            obj.cost = cursor.getInt((int) cursor.getColumnIndex(COLUMN_COST));
            obj.eventId = cursor.getInt((int) cursor.getColumnIndex(COLUMN_EVENTID));
            obj.name = cursor.getString((int) cursor.getColumnIndex(COLUMN_NAME));
        } while (cursor.moveToNext());
        return list;
    }

    public Equipment GetElement(Equipment model) {
        Cursor cursor = db.rawQuery("select * from " + TABLE + " where "
                + COLUMN_ID + " = " + model.id, null);
        Equipment obj = new Equipment();
        if (!cursor.moveToFirst()) {
            return null;
        }
        obj.id = cursor.getInt((int) cursor.getColumnIndex(COLUMN_ID));
        obj.cost = cursor.getInt((int) cursor.getColumnIndex(COLUMN_COST));
        obj.eventId = cursor.getInt((int) cursor.getColumnIndex(COLUMN_EVENTID));
        obj.name = cursor.getString((int) cursor.getColumnIndex(COLUMN_NAME));
        return obj;
    }

    public void Insert(Equipment model) {
        ContentValues content = new ContentValues();
        content.put(COLUMN_COST,model.cost);
        content.put(COLUMN_EVENTID,model.eventId);
        content.put(COLUMN_NAME,model.name);
        db.insert(TABLE,null,content);
    }

    public void Update(Equipment model) {
        ContentValues content=new ContentValues();
        content.put(COLUMN_COST,model.cost);
        content.put(COLUMN_EVENTID,model.eventId);
        content.put(COLUMN_NAME,model.name);
        String where = COLUMN_ID+" = "+model.id;
        db.update(TABLE,content,where,null);
    }

    public void Delete(Equipment model) {
        String where = COLUMN_ID+" = "+model.id;
        db.delete(TABLE,where,null);
    }
}