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
import com.example.eventcalculator.eventBusinessLogic.models.PremiseModel;

import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;

public class Premises extends AppCompatActivity {
    TableRow selectedRow;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_premises);
        selectedRow = new TableRow(this);

        fillTable(Arrays.asList("Адрес", "Стоимость за день"), getPremises());

    }

    void fillTable(List<String> titles, List<PremiseModel> premises) {

        TableLayout tableLayoutPremises = findViewById(R.id.tableLayoutPremises);

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
        tableLayoutPremises.addView(tableRowTitles);


        for (PremiseModel premise : premises) {
            TableRow tableRow = new TableRow(this);

            TextView textViewName = new TextView(this);
            textViewName.setText(premise.address);
            textViewName.setHeight(170);
            textViewName.setTextSize(16);
            textViewName.setTextColor(Color.WHITE);
            textViewName.setGravity(Gravity.CENTER);

            TextView textViewCost = new TextView(this);
            textViewName.setHeight(170);
            textViewCost.setTextSize(16);
            textViewCost.setText(String.valueOf(premise.address));
            textViewCost.setTextColor(Color.WHITE);
            textViewCost.setGravity(Gravity.CENTER);

            TextView textViewCount = new TextView(this);
            textViewName.setHeight(170);
            textViewCount.setTextSize(16);
            textViewCount.setText(String.valueOf(premise.cost));
            textViewCount.setTextColor(Color.WHITE);
            textViewCount.setGravity(Gravity.CENTER);

            TextView textViewId = new TextView(this);
            textViewId.setVisibility(View.INVISIBLE);
            textViewId.setText(String.valueOf(premise.id));

            tableRow.addView(textViewName);
            tableRow.addView(textViewCost);
            tableRow.addView(textViewCount);

            tableRow.setBackgroundColor(Color.parseColor("#FF6200EE"));

            tableRow.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {

                    selectedRow = tableRow;

                    for(int i = 0; i < tableLayoutPremises.getChildCount(); i++){
                        View view = tableLayoutPremises.getChildAt(i);
                        if (view instanceof TableRow ){
                            view.setBackgroundColor(Color.parseColor("#FF6200EE"));
                        }
                    }

                    tableRow.setBackgroundColor(Color.parseColor("#FF6200FF"));
                }
            });

            tableLayoutPremises.addView(tableRow);
        }
    }

    List<PremiseModel> getPremises(){
        List<PremiseModel> premises = new LinkedList<PremiseModel>();

        for(int i = 0; i < 10; i++){
            premises.add(new PremiseModel("address" + i,i*10,1));
        }

        return  premises;
    }

}