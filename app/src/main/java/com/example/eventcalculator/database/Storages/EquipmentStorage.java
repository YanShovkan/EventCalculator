package com.example.eventcalculator.database.Storages;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import com.example.eventcalculator.database.DatabaseHelper;
import com.example.eventcalculator.eventBusinessLogic.interfaces.IEquipmentStorage;
import com.example.eventcalculator.eventBusinessLogic.models.EquipmentModel;
import java.util.*;

public class EquipmentStorage implements IEquipmentStorage {

    DatabaseHelper sqlHelper;
    SQLiteDatabase db;
    final String TABLE = "equipment";
    final String COLUMN_ID = "equipmentid";
    final String COLUMN_NAME = "equipment_name";
    final String COLUMN_COST = "equipment_cost";
    final String COLUMN_EVENTID = "eventid";

    public EquipmentStorage(Context context) {
        sqlHelper = new DatabaseHelper(context);
        db = sqlHelper.getWritableDatabase();
    }

    public EquipmentStorage open() {
        db = sqlHelper.getWritableDatabase();
        return this;
    }

    public void close() {
        db.close();
    }

    public List<EquipmentModel> getFullList() {
        Cursor cursor = db.rawQuery("select * from " + TABLE, null);
        List<EquipmentModel> list = new ArrayList<>();
        if (!cursor.moveToFirst()) {
            return list;
        }
        do {
            EquipmentModel obj = new EquipmentModel();
            obj.id = cursor.getInt((int) cursor.getColumnIndex(COLUMN_ID));
            obj.cost = cursor.getInt((int) cursor.getColumnIndex(COLUMN_COST));
            obj.eventId = cursor.getInt((int) cursor.getColumnIndex(COLUMN_EVENTID));
            obj.name = cursor.getString((int) cursor.getColumnIndex(COLUMN_NAME));
            cursor.moveToNext();
        } while (!cursor.isLast());
        return list;
    }

    public List<EquipmentModel> getFilteredList(EquipmentModel model) {
        Cursor cursor = db.rawQuery("select * from " + TABLE + " where "
                + COLUMN_EVENTID + " = " + model.eventId, null);
        List<EquipmentModel> list = new ArrayList<>();
        if (!cursor.moveToFirst()) {
            return list;
        }
        do {
            EquipmentModel obj = new EquipmentModel();
            obj.id = cursor.getInt((int) cursor.getColumnIndex(COLUMN_ID));
            obj.cost = cursor.getInt((int) cursor.getColumnIndex(COLUMN_COST));
            obj.eventId = cursor.getInt((int) cursor.getColumnIndex(COLUMN_EVENTID));
            obj.name = cursor.getString((int) cursor.getColumnIndex(COLUMN_NAME));
            cursor.moveToNext();
        } while (!cursor.isLast());
        return list;
    }

    public EquipmentModel getElement(EquipmentModel model) {
        Cursor cursor = db.rawQuery("select * from " + TABLE + " where "
                + COLUMN_ID + " = " + model.id, null);
        EquipmentModel obj = new EquipmentModel();
        if (!cursor.moveToFirst()) {
            return null;
        }
        obj.id = cursor.getInt((int) cursor.getColumnIndex(COLUMN_ID));
        obj.cost = cursor.getInt((int) cursor.getColumnIndex(COLUMN_COST));
        obj.eventId = cursor.getInt((int) cursor.getColumnIndex(COLUMN_EVENTID));
        obj.name = cursor.getString((int) cursor.getColumnIndex(COLUMN_NAME));
        return obj;
    }

    public void insert(EquipmentModel model) {
        ContentValues content = new ContentValues();
        content.put(COLUMN_COST,model.cost);
        content.put(COLUMN_EVENTID,model.eventId);
        content.put(COLUMN_NAME,model.name);
        db.insert(TABLE,null,content);
    }

    public void update(EquipmentModel model) {
        ContentValues content=new ContentValues();
        content.put(COLUMN_COST,model.cost);
        content.put(COLUMN_EVENTID,model.eventId);
        content.put(COLUMN_NAME,model.name);
        String where = COLUMN_ID+" = "+model.id;
        db.update(TABLE,content,where,null);
    }

    public void delete(EquipmentModel model) {
        String where = COLUMN_ID+" = "+model.id;
        db.delete(TABLE,where,null);
    }
}
