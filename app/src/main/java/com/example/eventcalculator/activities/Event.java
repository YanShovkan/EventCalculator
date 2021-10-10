package com.example.eventcalculator.activities;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.example.eventcalculator.R;
import com.example.eventcalculator.database.Storages.EventStorage;
import com.example.eventcalculator.eventBusinessLogic.businessLogic.EventLogic;
import com.example.eventcalculator.eventBusinessLogic.models.EventModel;

import java.util.List;

public class Event extends AppCompatActivity {
    private Button buttonAddProducts;
    private Button buttonAddHandouts;
    private Button buttonAddPersonal;
    private Button buttonAddPremises;
    private Button buttonAddEquipments;
    private Button buttonCreateEvent;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_event);
        EventStorage eventStorage = new EventStorage(this);
        EventLogic eventLogic = new EventLogic(eventStorage);
        List<EventModel> eventList = eventLogic.read(null);
        buttonAddProducts = (Button)findViewById(R.id.buttonAddProducts);



        int eventId = 1;

        if(!eventList.isEmpty()) {
            eventId = eventList.get(eventList.size() - 1).id + 1;
        }

        int evendIdToPut = eventId;

        buttonAddProducts.setOnClickListener(
                new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        Intent intent = new Intent(Event.this, Products.class);
                        intent.putExtra("eventId", evendIdToPut);
                        startActivity(intent);
                    }
                }
        );

        buttonAddHandouts = (Button)findViewById(R.id.buttonAddHandouts);

        buttonAddHandouts.setOnClickListener(
                new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        Intent intent = new Intent(Event.this, Handouts.class);
                        startActivity(intent);
                    }
                }
        );

        buttonAddPersonal = (Button)findViewById(R.id.buttonAddPersonal);

        buttonAddPersonal.setOnClickListener(
                new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        Intent intent = new Intent(Event.this, Personal.class);
                        startActivity(intent);
                    }
                }
        );

        buttonAddPremises = (Button)findViewById(R.id.buttonAddPremises);

        buttonAddPremises.setOnClickListener(
                new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        Intent intent = new Intent(Event.this, Premises.class);
                        startActivity(intent);
                    }
                }
        );

        buttonAddEquipments = (Button)findViewById(R.id.buttonAddEquipments);

        buttonAddEquipments.setOnClickListener(
                new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        Intent intent = new Intent(Event.this, Equipments.class);
                        startActivity(intent);
                    }
                }
        );

        buttonCreateEvent = (Button)findViewById(R.id.buttonCreateEvent);

        buttonCreateEvent.setOnClickListener(
                new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        eventLogic.createOrUpdate(new EventModel());
                    }
                }
        );
    }
}