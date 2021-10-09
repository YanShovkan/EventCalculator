package com.example.eventcalculator.database.Storages;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import com.example.eventcalculator.database.DatabaseHelper;
import com.example.eventcalculator.eventBusinessLogic.interfaces.IPersonalStorage;
import com.example.eventcalculator.eventBusinessLogic.models.PersonalModel;
import java.util.List;
import java.util.ArrayList;

public class PersonalStorage implements IPersonalStorage {
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

    public List<PersonalModel> getFullList() {
        Cursor cursor = db.rawQuery("select * from " + TABLE, null);
        List<PersonalModel> list = new ArrayList<>();
        if (!cursor.moveToFirst()) {
            return list;
        }
        do {
            PersonalModel obj = new PersonalModel();
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

    public List<PersonalModel> getFilteredList(PersonalModel model) {
        Cursor cursor = db.rawQuery("select * from " + TABLE + " where "
                + COLUMN_EVENTID + " = " + model.eventId, null);
        List<PersonalModel> list = new ArrayList<>();
        if (!cursor.moveToFirst()) {
            return list;
        }
        do {
            PersonalModel obj = new PersonalModel();
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

    public PersonalModel getElement(PersonalModel model) {
        Cursor cursor = db.rawQuery("select * from " + TABLE + " where "
                + COLUMN_ID + " = " + model.id, null);
        PersonalModel obj = new PersonalModel();
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

    public void insert(PersonalModel model) {
        ContentValues content = new ContentValues();
        content.put(COLUMN_PAYMENT,model.payment);
        content.put(COLUMN_POSITION,model.position);
        content.put(COLUMN_EVENTID,model.eventId);
        content.put(COLUMN_NAME,model.name);
        db.insert(TABLE,null,content);
    }

    public void update(PersonalModel model) {
        ContentValues content=new ContentValues();
        content.put(COLUMN_PAYMENT,model.payment);
        content.put(COLUMN_POSITION,model.position);
        content.put(COLUMN_EVENTID,model.eventId);
        content.put(COLUMN_NAME,model.name);
        String where = COLUMN_ID+" = "+model.id;
        db.update(TABLE,content,where,null);
    }

    public void delete(PersonalModel model) {
        String where = COLUMN_ID+" = "+model.id;
        db.delete(TABLE,where,null);
    }
}
