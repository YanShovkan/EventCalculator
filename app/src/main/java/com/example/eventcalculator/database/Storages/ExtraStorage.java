package com.example.eventcalculator.database.Storages;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.view.View;

import com.example.eventcalculator.database.DatabaseHelper;
import com.example.eventcalculator.database.Models.Extra;

import java.util.List;
import java.util.ArrayList;

public class ExtraStorage {

    DatabaseHelper sqlHelper;
    SQLiteDatabase db;
    final String TABLE = "extra";
    final String COLUMN_ID = "extraid";
    final String COLUMN_NAME = "extra_name";
    final String COLUMN_COST = "extra_cost";
    final String COLUMN_DESCRIPTION = "description";
    final String COLUMN_EVENTID = "eventid";

    public ExtraStorage(Context context) {
        sqlHelper = new DatabaseHelper(context);
        db = sqlHelper.getWritableDatabase();
    }

    public ExtraStorage open(){
        db = sqlHelper.getWritableDatabase();
        return this;
    }

    public void close(){
        db.close();
    }

    public List<Extra> GetFullList() {
        Cursor cursor = db.rawQuery("select * from " + TABLE, null);
        List<Extra> list;
        list = new ArrayList<Extra>();
        if (!cursor.moveToFirst()) {
            return list;
        }
        do {
            Extra obj = new Extra();
            obj.id = cursor.getInt((int) cursor.getColumnIndex(COLUMN_ID));
            obj.cost = cursor.getInt((int) cursor.getColumnIndex(COLUMN_COST));
            obj.eventId = cursor.getInt((int) cursor.getColumnIndex(COLUMN_EVENTID));
            obj.name = cursor.getString((int) cursor.getColumnIndex(COLUMN_NAME));
            obj.description=cursor.getString((int) cursor.getColumnIndex(COLUMN_DESCRIPTION));
            list.add(obj);
            cursor.moveToNext();
        } while (!cursor.isLast());
        return list;
    }

    public List<Extra> GetFilteredList(Extra model) {
        Cursor cursor = db.rawQuery("select * from " + TABLE + " where "
                + COLUMN_EVENTID + " = " + model.eventId, null);
        List<Extra> list;
        list = new ArrayList<Extra>();
        if (!cursor.moveToFirst()) {
            return list;
        }
        do {
            Extra obj = new Extra();
            obj.id = cursor.getInt((int) cursor.getColumnIndex(COLUMN_ID));
            obj.cost = cursor.getInt((int) cursor.getColumnIndex(COLUMN_COST));
            obj.eventId = cursor.getInt((int) cursor.getColumnIndex(COLUMN_EVENTID));
            obj.name = cursor.getString((int) cursor.getColumnIndex(COLUMN_NAME));
            obj.description=cursor.getString((int) cursor.getColumnIndex(COLUMN_DESCRIPTION));
            list.add(obj);
            cursor.moveToNext();
        } while (!cursor.isLast());
        return list;
    }

    public Extra GetElement(Extra model) {
        Cursor cursor = db.rawQuery("select * from " + TABLE + " where "
                + COLUMN_ID + " = " + model.id, null);
        Extra obj = new Extra();
        if (!cursor.moveToFirst()) {
            return null;
        }
        obj.id = cursor.getInt((int) cursor.getColumnIndex(COLUMN_ID));
        obj.cost = cursor.getInt((int) cursor.getColumnIndex(COLUMN_COST));
        obj.eventId = cursor.getInt((int) cursor.getColumnIndex(COLUMN_EVENTID));
        obj.name = cursor.getString((int) cursor.getColumnIndex(COLUMN_NAME));
        obj.description=cursor.getString((int) cursor.getColumnIndex(COLUMN_DESCRIPTION));
        return obj;
    }

    public void Insert(Extra model) {
        ContentValues content = new ContentValues();
        content.put(COLUMN_COST,model.cost);
        content.put(COLUMN_EVENTID,model.eventId);
        content.put(COLUMN_NAME,model.name);
        content.put(COLUMN_DESCRIPTION,model.description);
        db.insert(TABLE,null,content);
    }

    public void Update(Extra model) {
        ContentValues content=new ContentValues();
        content.put(COLUMN_COST,model.cost);
        content.put(COLUMN_EVENTID,model.eventId);
        content.put(COLUMN_NAME,model.name);
        content.put(COLUMN_DESCRIPTION,model.description);
        String where = COLUMN_ID+" = "+model.id;
        db.update(TABLE,content,where,null);
    }

    public void Delete(Extra model) {
        String where = COLUMN_ID+" = "+model.id;
        db.delete(TABLE,where,null);
    }

}
