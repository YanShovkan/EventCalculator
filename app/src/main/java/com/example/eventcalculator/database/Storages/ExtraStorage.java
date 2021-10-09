package com.example.eventcalculator.database.Storages;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import com.example.eventcalculator.database.DatabaseHelper;
import com.example.eventcalculator.eventBusinessLogic.interfaces.IExtraStorage;
import com.example.eventcalculator.eventBusinessLogic.models.ExtraModel;
import java.util.ArrayList;
import java.util.List;

public class ExtraStorage implements IExtraStorage {

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

    public List<ExtraModel> getFullList() {
        Cursor cursor = db.rawQuery("select * from " + TABLE, null);
        List<ExtraModel> list = new ArrayList<>();
        if (!cursor.moveToFirst()) {
            return list;
        }
        do {
            ExtraModel obj = new ExtraModel();
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

    public List<ExtraModel> getFilteredList(ExtraModel model) {
        Cursor cursor = db.rawQuery("select * from " + TABLE + " where "
                + COLUMN_EVENTID + " = " + model.eventId, null);
        List<ExtraModel> list = new ArrayList<>();
        if (!cursor.moveToFirst()) {
            return list;
        }
        do {
            ExtraModel obj = new ExtraModel();
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

    public ExtraModel getElement(ExtraModel model) {
        Cursor cursor = db.rawQuery("select * from " + TABLE + " where "
                + COLUMN_ID + " = " + model.id, null);
        ExtraModel obj = new ExtraModel();
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

    public void insert(ExtraModel model) {
        ContentValues content = new ContentValues();
        content.put(COLUMN_COST,model.cost);
        content.put(COLUMN_EVENTID,model.eventId);
        content.put(COLUMN_NAME,model.name);
        content.put(COLUMN_DESCRIPTION,model.description);
        db.insert(TABLE,null,content);
    }

    public void update(ExtraModel model) {
        ContentValues content=new ContentValues();
        content.put(COLUMN_COST,model.cost);
        content.put(COLUMN_EVENTID,model.eventId);
        content.put(COLUMN_NAME,model.name);
        content.put(COLUMN_DESCRIPTION,model.description);
        String where = COLUMN_ID+" = "+model.id;
        db.update(TABLE,content,where,null);
    }

    public void delete(ExtraModel model) {
        String where = COLUMN_ID+" = "+model.id;
        db.delete(TABLE,where,null);
    }

}
