package com.example.eventcalculator.database;

import android.database.sqlite.SQLiteOpenHelper;
import android.database.sqlite.SQLiteDatabase;
import android.content.Context;

public class DatabaseHelper extends SQLiteOpenHelper {
    private static final String DATABASE_NAME = "eventCalculator.db"; // название бд
    private static final int SCHEMA = 1; // версия базы данных
    static final String TABLE_EVENT = "event";
    static final String TABLE_PRODUCT = "product";
    static final String TABLE_PREMISE = "premise";
    static final String TABLE_EQUIPMENT = "equipment";
    static final String TABLE_PERSONAL = "personal";
    static final String TABLE_EXTRA = "extra";// название таблицы в бд

    public DatabaseHelper(Context context) {
        super(context, DATABASE_NAME, null, SCHEMA);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {

        db.execSQL("CREATE TABLE event (\n" +
                "    eventid integer NOT NULL,\n" +
                "    event_name character(100) NOT NULL,\n" +
                "    date_from date,\n" +
                "\tdate_to date,\n" +
                "    description character(150),\n" +
                "\tcount_of_people integer NOT NULL\n" +
                ");" + "ALTER TABLE event\n" +
                "    ADD CONSTRAINT event_pkey PRIMARY KEY (eventid);\n");

        db.execSQL("CREATE TABLE event (\n" +
                "    eventid integer NOT NULL,\n" +
                "    event_name character(100) NOT NULL,\n" +
                "    date_from date,\n" +
                "\tdate_to date,\n" +
                "    description character(150),\n" +
                "\tcount_of_people integer NOT NULL\n" +
                ");");
        // добавление начальных данных
        db.execSQL("INSERT INTO "+ TABLE +" (" + COLUMN_NAME
                + ", " + COLUMN_YEAR  + ") VALUES ('Том Смит', 1981);");
    }
    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion,  int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS "+TABLE);
        onCreate(db);
    }
}
