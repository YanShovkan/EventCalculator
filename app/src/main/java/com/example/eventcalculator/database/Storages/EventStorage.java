package com.example.eventcalculator.database.Storages;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import com.example.eventcalculator.database.DatabaseHelper;
import com.example.eventcalculator.eventBusinessLogic.interfaces.IEventStorage;
import com.example.eventcalculator.eventBusinessLogic.models.EventModel;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.ArrayList;

public class EventStorage implements IEventStorage {

    DatabaseHelper sqlHelper;
    SQLiteDatabase db;
    final String TABLE = "event";
    final String COLUMN_ID = "eventid";
    final String COLUMN_NAME = "event_name";
    final String COLUMN_DAYCOUNT = "day_count";
    final String COLUMN_DESCRIPTION = "description";
    final String COLUMN_COUNTOFPEOPLE = "count_of_people";

    public EventStorage(Context context) {
        sqlHelper = new DatabaseHelper(context);
        db = sqlHelper.getWritableDatabase();
    }

    public EventStorage open(){
        db = sqlHelper.getWritableDatabase();
        return this;
    }

    public void close(){
        db.close();
    }

    public List<EventModel> getFullList() {
        Cursor cursor = db.rawQuery("select * from " + TABLE, null);
        List<EventModel> list = new ArrayList<>();
        if (!cursor.moveToFirst()) {
            return list;
        }
        do {
            EventModel obj = new EventModel();

            obj.dayCount = cursor.getInt((int) cursor.getColumnIndex(COLUMN_DAYCOUNT));
            obj.countOfPeople = cursor.getInt((int) cursor.getColumnIndex(COLUMN_COUNTOFPEOPLE));
            obj.id = cursor.getInt((int) cursor.getColumnIndex(COLUMN_ID));
            list.add(obj);
            cursor.moveToNext();
        } while (!cursor.isAfterLast());
        return list;
    }

    public List<EventModel> getFilteredList(EventModel model) {
        Cursor cursor = db.rawQuery("select * from " + TABLE, null);
        List<EventModel> list = new ArrayList<>();
        if (!cursor.moveToFirst()) {
            return list;
        }
        do {
            EventModel obj = new EventModel();

            obj.dayCount = cursor.getInt((int) cursor.getColumnIndex(COLUMN_DAYCOUNT));;
            obj.countOfPeople = cursor.getInt((int) cursor.getColumnIndex(COLUMN_COUNTOFPEOPLE));
            obj.id = cursor.getInt((int) cursor.getColumnIndex(COLUMN_ID));
            list.add(obj);
            cursor.moveToNext();
        } while (!cursor.isAfterLast());
        return list;
    }

    public EventModel getElement(EventModel model) {
        Cursor cursor = db.rawQuery("select * from " + TABLE + " where "
                + COLUMN_ID + " = " + model.id, null);
        EventModel obj = new EventModel();
        if (!cursor.moveToFirst()) {
            return null;
        }
        obj.id = cursor.getInt((int) cursor.getColumnIndex(COLUMN_ID));
        obj.dayCount = cursor.getInt((int) cursor.getColumnIndex(COLUMN_DAYCOUNT));;
        obj.countOfPeople = cursor.getInt((int) cursor.getColumnIndex(COLUMN_COUNTOFPEOPLE));
        return obj;
    }

    public void insert(EventModel model) {
        ContentValues content = new ContentValues();
        content.put(COLUMN_COUNTOFPEOPLE,model.countOfPeople);
        content.put(COLUMN_DAYCOUNT,model.dayCount);
        content.put(COLUMN_NAME,model.name);
        db.insert(TABLE,null,content);
    }

    public void update(EventModel model) {
        ContentValues content=new ContentValues();
        content.put(COLUMN_COUNTOFPEOPLE,model.countOfPeople);
        content.put(COLUMN_DAYCOUNT,model.dayCount);
        content.put(COLUMN_NAME,model.name);
        String where = COLUMN_ID+" = "+model.id;
        db.update(TABLE,content,where,null);
    }

    public void delete(EventModel model) {
        String where = COLUMN_ID+" = "+model.id;
        db.delete(TABLE,where,null);
    }

}
