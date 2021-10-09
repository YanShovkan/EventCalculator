package com.example.eventcalculator.helperClasses;

import android.content.Context;
import android.graphics.Color;
import android.view.Gravity;
import android.widget.TableLayout;
import android.widget.TableRow;
import android.widget.TextView;

import java.lang.reflect.Field;
import java.util.Properties;

public class ViewTables<T> {

    public void fillTable(String[] titles, T[] objects, Context context) {

        TableLayout tableLayout = new TableLayout(context);
        TableRow tableRowTitles = new TableRow(context);

        for (String title : titles) {
            TextView textView = new TextView(context);

            textView.setText(title);
            textView.setTextColor(Color.WHITE);

            tableRowTitles.addView(textView);
        }

        tableLayout.addView(tableRowTitles);

        for (T object : objects) {
            TableRow tableRow = new TableRow(context);

            Field[] fields = object.getClass().getFields();

            for (Object field : fields) {
                TextView textView = new TextView(context);

                textView.setText(field.toString());
                textView.setTextColor(Color.WHITE);
                textView.setGravity(Gravity.CENTER);

                tableRow.addView(textView);
            }

            tableLayout.addView(tableRow);
        }
    }
}