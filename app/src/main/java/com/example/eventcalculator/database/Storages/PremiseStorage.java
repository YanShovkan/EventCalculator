package com.example.eventcalculator.database.Storages;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.view.View;

import com.example.eventcalculator.database.DatabaseHelper;
import com.example.eventcalculator.database.Models.Premise;

import java.util.ArrayList;
import java.util.List;

public class PremiseStorage {
    DatabaseHelper sqlHelper;
    SQLiteDatabase db;
    final String TABLE = "premise";
    final String COLUMN_ID = "premiseid";
    final String COLUMN_NAME = "premise_name";
    final String COLUMN_COST = "premise_cost";
    final String COLUMN_ADRESS = "adress_cost";
    final String COLUMN_EVENTID = "eventid";

    public PremiseStorage(Context context) {
        sqlHelper = new DatabaseHelper(context);
        db = sqlHelper.getWritableDatabase();
    }

    public PremiseStorage open(){
        db = sqlHelper.getWritableDatabase();
        return this;
    }

    public void close(){
        db.close();
    }

    public List<com.example.eventcalculator.database.Models.Premise> GetFullList() {
        Cursor cursor = db.rawQuery("select * from " + TABLE, null);
        List<com.example.eventcalculator.database.Models.Premise> list;
        list = new ArrayList<Premise>();
        if (!cursor.moveToFirst()) {
            return list;
        }
        do {
            com.example.eventcalculator.database.Models.Premise obj = new com.example.eventcalculator.database.Models.Premise();
            obj.id = cursor.getInt((int) cursor.getColumnIndex(COLUMN_ID));
            obj.cost = cursor.getInt((int) cursor.getColumnIndex(COLUMN_COST));
            obj.address = cursor.getString((int) cursor.getColumnIndex(COLUMN_ADRESS));
            obj.eventId = cursor.getInt((int) cursor.getColumnIndex(COLUMN_EVENTID));

            list.add(obj);
            cursor.moveToNext();
        } while (!cursor.isLast());
        return list;
    }

    public List<com.example.eventcalculator.database.Models.Premise> GetFilteredList(com.example.eventcalculator.database.Models.Premise model) {
        Cursor cursor = db.rawQuery("select * from " + TABLE + " where "
                + COLUMN_EVENTID + " = " + model.eventId, null);
        List<com.example.eventcalculator.database.Models.Premise> list;
        list = new ArrayList<com.example.eventcalculator.database.Models.Premise>();
        if (!cursor.moveToFirst()) {
            return list;
        }
        do {
            com.example.eventcalculator.database.Models.Premise obj = new com.example.eventcalculator.database.Models.Premise();
            obj.id = cursor.getInt((int) cursor.getColumnIndex(COLUMN_ID));
            obj.cost = cursor.getInt((int) cursor.getColumnIndex(COLUMN_COST));
            obj.eventId = cursor.getInt((int) cursor.getColumnIndex(COLUMN_EVENTID));
            obj.address = cursor.getString((int) cursor.getColumnIndex(COLUMN_ADRESS));

            list.add(obj);
            cursor.moveToNext();
        } while (!cursor.isLast());
        return list;
    }

    public com.example.eventcalculator.database.Models.Premise GetElement(com.example.eventcalculator.database.Models.Premise model) {
        Cursor cursor = db.rawQuery("select * from " + TABLE + " where "
                + COLUMN_ID + " = " + model.id, null);
        com.example.eventcalculator.database.Models.Premise obj = new com.example.eventcalculator.database.Models.Premise();
        if (!cursor.moveToFirst()) {
            return null;
        }
        obj.id = cursor.getInt((int) cursor.getColumnIndex(COLUMN_ID));
        obj.cost = cursor.getInt((int) cursor.getColumnIndex(COLUMN_COST));
        obj.eventId = cursor.getInt((int) cursor.getColumnIndex(COLUMN_EVENTID));
        obj.address = cursor.getString((int) cursor.getColumnIndex(COLUMN_ADRESS));
        return obj;
    }

    public void Insert(com.example.eventcalculator.database.Models.Premise model) {
        ContentValues content = new ContentValues();
        content.put(COLUMN_COST,model.cost);
        content.put(COLUMN_EVENTID,model.eventId);
        content.put(COLUMN_ADRESS,model.address);
        db.insert(TABLE,null,content);
    }

    public void Update(com.example.eventcalculator.database.Models.Premise model) {
        ContentValues content=new ContentValues();
        content.put(COLUMN_COST,model.cost);
        content.put(COLUMN_EVENTID,model.eventId);
        content.put(COLUMN_ADRESS,model.address);
        String where = COLUMN_ID+" = "+model.id;
        db.update(TABLE,content,where,null);
    }

    public void Delete(com.example.eventcalculator.database.Models.Premise model) {
        String where = COLUMN_ID+" = "+model.id;
        db.delete(TABLE,where,null);
    }
}
