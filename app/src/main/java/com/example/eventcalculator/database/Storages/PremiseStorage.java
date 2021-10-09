package com.example.eventcalculator.database.Storages;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import com.example.eventcalculator.database.DatabaseHelper;
import com.example.eventcalculator.eventBusinessLogic.interfaces.IPremiseStorage;
import com.example.eventcalculator.eventBusinessLogic.models.PremiseModel;

import java.util.ArrayList;
import java.util.List;

public class PremiseStorage implements IPremiseStorage {
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

    public List<PremiseModel> getFullList() {
        Cursor cursor = db.rawQuery("select * from " + TABLE, null);
        List<PremiseModel> list = new ArrayList<>();
        if (!cursor.moveToFirst()) {
            return list;
        }
        do {
            PremiseModel obj = new PremiseModel();
            obj.id = cursor.getInt((int) cursor.getColumnIndex(COLUMN_ID));
            obj.cost = cursor.getInt((int) cursor.getColumnIndex(COLUMN_COST));
            obj.address = cursor.getString((int) cursor.getColumnIndex(COLUMN_ADRESS));
            obj.eventId = cursor.getInt((int) cursor.getColumnIndex(COLUMN_EVENTID));

            list.add(obj);
            cursor.moveToNext();
        } while (!cursor.isLast());
        return list;
    }

    public List<PremiseModel> getFilteredList(PremiseModel model) {
        Cursor cursor = db.rawQuery("select * from " + TABLE + " where "
                + COLUMN_EVENTID + " = " + model.eventId, null);
        List<PremiseModel> list = new ArrayList<>();
        if (!cursor.moveToFirst()) {
            return list;
        }
        do {
            PremiseModel obj = new PremiseModel();
            obj.id = cursor.getInt((int) cursor.getColumnIndex(COLUMN_ID));
            obj.cost = cursor.getInt((int) cursor.getColumnIndex(COLUMN_COST));
            obj.eventId = cursor.getInt((int) cursor.getColumnIndex(COLUMN_EVENTID));
            obj.address = cursor.getString((int) cursor.getColumnIndex(COLUMN_ADRESS));

            list.add(obj);
            cursor.moveToNext();
        } while (!cursor.isLast());
        return list;
    }

    public PremiseModel getElement(PremiseModel model) {
        Cursor cursor = db.rawQuery("select * from " + TABLE + " where "
                + COLUMN_ID + " = " + model.id, null);
        PremiseModel obj = new PremiseModel();
        if (!cursor.moveToFirst()) {
            return null;
        }
        obj.id = cursor.getInt((int) cursor.getColumnIndex(COLUMN_ID));
        obj.cost = cursor.getInt((int) cursor.getColumnIndex(COLUMN_COST));
        obj.eventId = cursor.getInt((int) cursor.getColumnIndex(COLUMN_EVENTID));
        obj.address = cursor.getString((int) cursor.getColumnIndex(COLUMN_ADRESS));
        return obj;
    }

    public void insert(PremiseModel model) {
        ContentValues content = new ContentValues();
        content.put(COLUMN_COST,model.cost);
        content.put(COLUMN_EVENTID,model.eventId);
        content.put(COLUMN_ADRESS,model.address);
        db.insert(TABLE,null,content);
    }

    public void update(PremiseModel model) {
        ContentValues content=new ContentValues();
        content.put(COLUMN_COST,model.cost);
        content.put(COLUMN_EVENTID,model.eventId);
        content.put(COLUMN_ADRESS,model.address);
        String where = COLUMN_ID+" = "+model.id;
        db.update(TABLE,content,where,null);
    }

    public void delete(PremiseModel model) {
        String where = COLUMN_ID+" = "+model.id;
        db.delete(TABLE,where,null);
    }
}
