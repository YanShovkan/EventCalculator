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
import com.example.eventcalculator.database.Storages.PersonalStorage;
import com.example.eventcalculator.eventBusinessLogic.businessLogic.PersonalLogic;
import com.example.eventcalculator.eventBusinessLogic.models.PersonalModel;
import com.example.eventcalculator.eventBusinessLogic.models.ProductModel;

import java.util.Arrays;
import java.util.List;

public class Personal extends AppCompatActivity {
    TableRow selectedRow;
    PersonalStorage personalStorage = new PersonalStorage(this);
    PersonalLogic personalLogic=new PersonalLogic(personalStorage);
    int eventId;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_personal);
        selectedRow = new TableRow(this);
        eventId = getIntent().getExtras().getInt("eventId");
        fillTable(Arrays.asList("Должность", "Имя", "Оплата"), getPersonal());
    }

    void fillTable(List<String> titles, List<PersonalModel> personal) {

        TableLayout tableLayoutPersonal = findViewById(R.id.tableLayoutPersonal);

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
        tableLayoutPersonal.addView(tableRowTitles);


        for (PersonalModel onePersonal : personal) {
            TableRow tableRow = new TableRow(this);

            TextView textViewName = new TextView(this);
            textViewName.setText(onePersonal.position);
            textViewName.setHeight(170);
            textViewName.setTextSize(16);
            textViewName.setTextColor(Color.WHITE);
            textViewName.setGravity(Gravity.CENTER);

            TextView textViewCost = new TextView(this);
            textViewName.setHeight(170);
            textViewCost.setTextSize(16);
            textViewCost.setText(String.valueOf(onePersonal.name));
            textViewCost.setTextColor(Color.WHITE);
            textViewCost.setGravity(Gravity.CENTER);

            TextView textViewCount = new TextView(this);
            textViewName.setHeight(170);
            textViewCount.setTextSize(16);
            textViewCount.setText(String.valueOf(onePersonal.payment));
            textViewCount.setTextColor(Color.WHITE);
            textViewCount.setGravity(Gravity.CENTER);

            TextView textViewId = new TextView(this);
            textViewId.setVisibility(View.INVISIBLE);
            textViewId.setText(String.valueOf(onePersonal.id));

            tableRow.addView(textViewName);
            tableRow.addView(textViewCost);
            tableRow.addView(textViewCount);

            tableRow.setBackgroundColor(Color.parseColor("#FF6200EE"));

            tableRow.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {

                    selectedRow = tableRow;

                    for(int i = 0; i < tableLayoutPersonal.getChildCount(); i++){
                        View view = tableLayoutPersonal.getChildAt(i);
                        if (view instanceof TableRow ){
                            view.setBackgroundColor(Color.parseColor("#FF6200EE"));
                        }
                    }

                    tableRow.setBackgroundColor(Color.parseColor("#FF6200FF"));
                }
            });

            tableLayoutPersonal.addView(tableRow);
        }
    }

    List<PersonalModel> getPersonal(){
        PersonalModel personalModel = new PersonalModel();
        personalModel.setEventId(eventId);
        List<PersonalModel> personal = personalLogic.Read(personalModel);
        return  personal;
    }

    public void buttonAdd_click(View view) {

    }

    public void buttonDelete_click(View view) {

    }

    public void buttonUpdate_click(View view) {

    }
}