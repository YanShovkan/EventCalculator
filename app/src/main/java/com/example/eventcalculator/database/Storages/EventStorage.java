package com.example.eventcalculator.database.Storages;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Build;
import android.view.View;

import androidx.annotation.RequiresApi;

import com.example.eventcalculator.database.DatabaseHelper;
import com.example.eventcalculator.database.Models.Equipment;
import com.example.eventcalculator.database.Models.Event;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.ZoneId;
import java.util.Date;
import java.util.List;
import java.util.ArrayList;

public class EventStorage {

    DatabaseHelper sqlHelper;
    SQLiteDatabase db;
    final SimpleDateFormat simpleDateFormat;
    final String TABLE = "event";
    final String COLUMN_ID = "eventid";
    final String COLUMN_NAME = "event_name";
    final String COLUMN_DATEFROM = "date_from";
    final String COLUMN_DATETO = "date_to";
    final String COLUMN_DESCRIPTION = "description";
    final String COLUMN_COUNTOFPEOPLE = "count_of_people";

    public EventStorage(Context context) {
        sqlHelper = new DatabaseHelper(context);
        db = sqlHelper.getWritableDatabase();
        simpleDateFormat = new SimpleDateFormat("dd-MM-yyyy");
    }

    public EventStorage open(){
        db = sqlHelper.getWritableDatabase();
        return this;
    }

    public void close(){
        db.close();
    }

    public List<Event> GetFullList() {
        Cursor cursor = db.rawQuery("select * from " + TABLE, null);
        List<Event> list;
        list = new ArrayList<Event>();
        if (!cursor.moveToFirst()) {
            return list;
        }
        do {
            Event obj = new Event();
            String dateFromString = cursor.getString((int) cursor.getColumnIndex(COLUMN_DATEFROM));
            String dateToString = cursor.getString((int) cursor.getColumnIndex(COLUMN_DATETO));
            Date dateTo = null;
            Date dateFrom = null;
            try {
                dateTo = simpleDateFormat.parse(dateToString);
                dateFrom = simpleDateFormat.parse(dateFromString);
            } catch (ParseException e) {
                e.printStackTrace();
            }
            obj.dateFrom = dateFrom;
            obj.dateTo = dateTo;
            obj.countOfPeople = cursor.getInt((int) cursor.getColumnIndex(COLUMN_COUNTOFPEOPLE));
            cursor.moveToNext();
        } while (!cursor.isLast());
        return list;
    }

    public List<Event> GetFilteredList(Event model) {
        Cursor cursor = db.rawQuery("select * from " + TABLE + " where "
                + COLUMN_DATEFROM + " > CURRENT_DATE" +
                " ORDER BY "+ COLUMN_DATEFROM, null);
        List<Event> list;
        list = new ArrayList<Event>();
        if (!cursor.moveToFirst()) {
            return list;
        }
        do {
            Event obj = new Event();
            String dateFromString = cursor.getString((int) cursor.getColumnIndex(COLUMN_DATEFROM));
            String dateToString = cursor.getString((int) cursor.getColumnIndex(COLUMN_DATETO));
            Date dateTo = null;
            Date dateFrom = null;
            try {
                dateTo = simpleDateFormat.parse(dateToString);
                dateFrom = simpleDateFormat.parse(dateFromString);
            } catch (ParseException e) {
                e.printStackTrace();
            }
            obj.dateFrom = dateFrom;
            obj.dateTo = dateTo;
            obj.countOfPeople = cursor.getInt((int) cursor.getColumnIndex(COLUMN_COUNTOFPEOPLE));
            cursor.moveToNext();
        } while (!cursor.isLast());
        return list;
    }

    public Event GetElement(Event model) {
        Cursor cursor = db.rawQuery("select * from " + TABLE + " where "
                + COLUMN_ID + " = " + model.id, null);
        Event obj = new Event();
        if (!cursor.moveToFirst()) {
            return null;
        }
        obj.id = cursor.getInt((int) cursor.getColumnIndex(COLUMN_ID));
        String dateFromString = cursor.getString((int) cursor.getColumnIndex(COLUMN_DATEFROM));
        String dateToString = cursor.getString((int) cursor.getColumnIndex(COLUMN_DATETO));
        Date dateTo = null;
        Date dateFrom = null;
        try {
            dateTo = simpleDateFormat.parse(dateToString);
            dateFrom = simpleDateFormat.parse(dateFromString);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        obj.dateFrom = dateFrom;
        obj.dateTo = dateTo;
        obj.countOfPeople = cursor.getInt((int) cursor.getColumnIndex(COLUMN_COUNTOFPEOPLE));
        return obj;
    }

    public void Insert(Event model) {
        ContentValues content = new ContentValues();
        content.put(COLUMN_COUNTOFPEOPLE,model.countOfPeople);
        String dateFrom = simpleDateFormat.format(model.dateFrom);
        content.put(COLUMN_DATEFROM,dateFrom);
        String dateTo = simpleDateFormat.format(model.dateTo);
        content.put(COLUMN_DATETO,dateTo);
        content.put(COLUMN_NAME,model.name);
        db.insert(TABLE,null,content);
    }

    public void Update(Event model) {
        ContentValues content=new ContentValues();
        content.put(COLUMN_COUNTOFPEOPLE,model.countOfPeople);
        String dateFrom = simpleDateFormat.format(model.dateFrom);
        content.put(COLUMN_DATEFROM,dateFrom);
        String dateTo = simpleDateFormat.format(model.dateTo);
        content.put(COLUMN_DATETO,dateTo);
        content.put(COLUMN_NAME,model.name);
        String where = COLUMN_ID+" = "+model.id;
        db.update(TABLE,content,where,null);
    }

    public void Delete(Equipment model) {
        String where = COLUMN_ID+" = "+model.id;
        db.delete(TABLE,where,null);
    }

}
