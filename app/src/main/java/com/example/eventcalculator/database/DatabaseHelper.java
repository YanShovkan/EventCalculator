package com.example.eventcalculator.database;

import android.database.sqlite.SQLiteOpenHelper;
import android.database.sqlite.SQLiteDatabase;
import android.content.Context;

public class DatabaseHelper extends SQLiteOpenHelper {
    private static final String DATABASE_NAME = "eventCalculator.db"; // название бд
    private static final int SCHEMA = 1; // версия базы данных

    public DatabaseHelper(Context context) {
        super(context, DATABASE_NAME, null, SCHEMA);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {

        db.execSQL("CREATE TABLE event (\n" +
                "    eventid integer PRIMARY KEY,\n" +
                "    event_name character(100) NOT NULL,\n" +
                "    date_from date,\n" +
                "\t  date_to date,\n" +
                "    description character(150),\n" +
                "\t  count_of_people integer NOT NULL);\n");

        db.execSQL("CREATE TABLE product (\n" +
                "    productid integer NOT NULL,\n" +
                "    product_name character(100) NOT NULL,\n" +
                "    product_cost integer NOT NULL,\n" +
                "\t  count_per_person real NOT NULL,\n" +
                "\t  eventid integer NOT NULL,\n" +
                "\t  CONSTRAINT product_pkey PRIMARY KEY (productid),\n" +
                "\t  CONSTRAINT eventfk FOREIGN KEY (eventid) \n" +
                "\t  REFERENCES event(eventid) ON DELETE CASCADE\n" +
                ");");

        db.execSQL("CREATE TABLE personal (" +
                "    personalid integer NOT NULL,\n" +
                "\t  personal_name character(100) NOT NULL,\n" +
                "\t  personal_position character(100) NOT NULL,\n" +
                "\t  payment integer NOT NULL,\n" +
                "\t  eventid integer NOT NULL,\n" +
                "\t  CONSTRAINT personal_pkey PRIMARY KEY (personalid),\n" +
                "\t  CONSTRAINT eventfk FOREIGN KEY (eventid)\n" +
                "\t  REFERENCES event(eventid) ON DELETE CASCADE\n" +
                "\t);\n");

        db.execSQL("CREATE TABLE premise (" +
                "    premiseid integer NOT NULL,\n" +
                "\t  pramise_name character(100),\n" +
                "\t  premise_adress character(100),\n" +
                "\t  premise_cost integer NOT NULL,\n" +
                "\t  eventid integer NOT NULL,\n" +
                "\t  CONSTRAINT premise_pkey PRIMARY KEY (premiseid),\n" +
                "\t  CONSTRAINT eventfk FOREIGN KEY (eventid)\n" +
                "\t  REFERENCES event(eventid) ON DELETE CASCADE\n" +
                "\t );");

        db.execSQL("CREATE TABLE equipment (" +
                "    equipmentid integer NOT NULL,\n" +
                "\t  equipment_name character(100) NOT NULL,\n" +
                "\t  equipment_cost integer NOT NULL,\n" +
                "\t  eventid integer NOT NULL,\n" +
                "\t  CONSTRAINT equipment_pkey PRIMARY KEY (equipmentid),\n" +
                "\t  CONSTRAINT eventfk FOREIGN KEY (eventid)\n" +
                "\t  REFERENCES event(eventid) ON DELETE CASCADE\n" +
                "\t );");

        db.execSQL("CREATE TABLE extra (extraid integer NOT NULL,\n" +
                "\t  extra_name character(100),\n" +
                "\t  description character(150),\n" +
                "\t  extra_cost integer NOT NULL,\n" +
                "\t  eventid integer NOT NULL,\n" +
                "\t  CONSTRAINT extra_pkey PRIMARY KEY (extraid),\n" +
                "\t  CONSTRAINT eventfk FOREIGN KEY (eventid)\n" +
                "\t  REFERENCES event(eventid) ON DELETE CASCADE\n" +
                "\t );");
    }
    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion,  int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS "+DATABASE_NAME);
        onCreate(db);
    }
}
