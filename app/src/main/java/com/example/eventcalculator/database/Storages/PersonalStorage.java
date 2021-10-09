package com.example.eventcalculator.database.Storages;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.view.View;

import com.example.eventcalculator.database.DatabaseHelper;
import com.example.eventcalculator.database.Models.Personal;

import java.util.List;
import java.util.ArrayList;

public class PersonalStorage {
    DatabaseHelper sqlHelper;
    SQLiteDatabase db;
    final String TABLE = "personal";
    final String COLUMN_ID = "personalid";
    final String COLUMN_NAME = "personal_name";
    final String COLUMN_POSITION = "personal_position";
    final String COLUMN_PAYMENT = "personal_payment";
    final String COLUMN_EVENTID = "eventid";

    public PersonalStorage(Context context) {
        sqlHelper = new DatabaseHelper(context);
        db = sqlHelper.getWritableDatabase();
    }

    public PersonalStorage open(){
        db = sqlHelper.getWritableDatabase();
        return this;
    }

    public void close(){
        db.close();
    }

    public List<Personal> GetFullList() {
        Cursor cursor = db.rawQuery("select * from " + TABLE, null);
        List<Personal> list;
        list = new ArrayList<Personal>();
        if (!cursor.moveToFirst()) {
            return list;
        }
        do {
            Personal obj = new Personal();
            obj.id = cursor.getInt((int) cursor.getColumnIndex(COLUMN_ID));
            obj.position = cursor.getString((int) cursor.getColumnIndex(COLUMN_POSITION));
            obj.payment = cursor.getInt((int) cursor.getColumnIndex(COLUMN_PAYMENT));
            obj.eventId = cursor.getInt((int) cursor.getColumnIndex(COLUMN_EVENTID));
            obj.name = cursor.getString((int) cursor.getColumnIndex(COLUMN_NAME));

            list.add(obj);
            cursor.moveToNext();
        } while (!cursor.isLast());
        return list;
    }

    public List<Personal> GetFilteredList(Personal model) {
        Cursor cursor = db.rawQuery("select * from " + TABLE + " where "
                + COLUMN_EVENTID + " = " + model.eventId, null);
        List<Personal> list;
        list = new ArrayList<Personal>();
        if (!cursor.moveToFirst()) {
            return list;
        }
        do {
            Personal obj = new Personal();
            obj.id = cursor.getInt((int) cursor.getColumnIndex(COLUMN_ID));
            obj.position = cursor.getString((int) cursor.getColumnIndex(COLUMN_POSITION));
            obj.payment = cursor.getInt((int) cursor.getColumnIndex(COLUMN_PAYMENT));
            obj.eventId = cursor.getInt((int) cursor.getColumnIndex(COLUMN_EVENTID));
            obj.name = cursor.getString((int) cursor.getColumnIndex(COLUMN_NAME));

            list.add(obj);
            cursor.moveToNext();
        } while (!cursor.isLast());
        return list;
    }

    public Personal GetElement(Personal model) {
        Cursor cursor = db.rawQuery("select * from " + TABLE + " where "
                + COLUMN_ID + " = " + model.id, null);
        Personal obj = new Personal();
        if (!cursor.moveToFirst()) {
            return null;
        }
        obj.id = cursor.getInt((int) cursor.getColumnIndex(COLUMN_ID));
        obj.position = cursor.getString((int) cursor.getColumnIndex(COLUMN_POSITION));
        obj.payment = cursor.getInt((int) cursor.getColumnIndex(COLUMN_PAYMENT));
        obj.eventId = cursor.getInt((int) cursor.getColumnIndex(COLUMN_EVENTID));
        obj.name = cursor.getString((int) cursor.getColumnIndex(COLUMN_NAME));
        return obj;
    }

    public void Insert(Personal model) {
        ContentValues content = new ContentValues();
        content.put(COLUMN_PAYMENT,model.payment);
        content.put(COLUMN_POSITION,model.position);
        content.put(COLUMN_EVENTID,model.eventId);
        content.put(COLUMN_NAME,model.name);
        db.insert(TABLE,null,content);
    }

    public void Update(Personal model) {
        ContentValues content=new ContentValues();
        content.put(COLUMN_PAYMENT,model.payment);
        content.put(COLUMN_POSITION,model.position);
        content.put(COLUMN_EVENTID,model.eventId);
        content.put(COLUMN_NAME,model.name);
        String where = COLUMN_ID+" = "+model.id;
        db.update(TABLE,content,where,null);
    }

    public void Delete(Personal model) {
        String where = COLUMN_ID+" = "+model.id;
        db.delete(TABLE,where,null);
    }
}
