package com.example.eventcalculator.activities;

import androidx.appcompat.app.AppCompatActivity;

import android.graphics.Color;
import android.os.Bundle;
import android.view.Gravity;
import android.view.View;
import android.widget.TableLayout;
import android.widget.TableRow;
import android.widget.TextView;

import com.example.eventcalculator.R;
import com.example.eventcalculator.eventBusinessLogic.models.EquipmentModel;

import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;

public class Equipments extends AppCompatActivity {

    TableRow selectedRow;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_equipments);
        selectedRow = new TableRow(this);

        fillTable(Arrays.asList("Название", "Стоимость за штуку"), getEquipments());

    }

    void fillTable(List<String> titles, List<EquipmentModel> equipments) {

        TableLayout tableLayoutEquipments = findViewById(R.id.tableLayoutEquipments);

        TableRow tableRowTitles = new TableRow(this);

        for (String title : titles) {
            TextView textView = new TextView(this);

            textView.setTextSize(16);
            textView.setText(title);
            textView.setTextColor(Color.WHITE);
            textView.setGravity(Gravity.CENTER);
            textView.setWidth( getWindowManager().getDefaultDisplay().getWidth() / 3);
            tableRowTitles.addView(textView);
        }

        tableRowTitles.setBackgroundColor(Color.parseColor("#FF6200EE"));
        tableLayoutEquipments.addView(tableRowTitles);


        for (EquipmentModel equipment : equipments) {
            TableRow tableRow = new TableRow(this);

            TextView textViewName = new TextView(this);
            textViewName.setText(equipment.name);
            textViewName.setHeight(170);
            textViewName.setTextSize(16);
            textViewName.setTextColor(Color.WHITE);
            textViewName.setGravity(Gravity.CENTER);

            TextView textViewCost = new TextView(this);
            textViewName.setHeight(170);
            textViewCost.setTextSize(16);
            textViewCost.setText(String.valueOf(equipment.cost));
            textViewCost.setTextColor(Color.WHITE);
            textViewCost.setGravity(Gravity.CENTER);

            TextView textViewId = new TextView(this);
            textViewId.setVisibility(View.INVISIBLE);
            textViewId.setText(String.valueOf(equipment.id));

            tableRow.addView(textViewName);
            tableRow.addView(textViewCost);

            tableRow.setBackgroundColor(Color.parseColor("#FF6200EE"));

            tableRow.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {

                    selectedRow = tableRow;

                    for(int i = 0; i < tableLayoutEquipments.getChildCount(); i++){
                        View view = tableLayoutEquipments.getChildAt(i);
                        if (view instanceof TableRow ){
                            view.setBackgroundColor(Color.parseColor("#FF6200EE"));
                        }
                    }

                    tableRow.setBackgroundColor(Color.parseColor("#FF6200FF"));
                }
            });

            tableLayoutEquipments.addView(tableRow);
        }
    }

    List<EquipmentModel> getEquipments(){
        List<EquipmentModel> equipments = new LinkedList<EquipmentModel>();

        for(int i = 0; i < 10; i++){
            equipments.add(new EquipmentModel("name" + i,i*10,1));
        }

        return  equipments;
    }

}