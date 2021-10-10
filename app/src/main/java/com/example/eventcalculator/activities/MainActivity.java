package com.example.eventcalculator.activities;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.example.eventcalculator.R;
import com.example.eventcalculator.database.DatabaseHelper;
import com.example.eventcalculator.database.Storages.EquipmentStorage;
import com.example.eventcalculator.database.Storages.EventStorage;
import com.example.eventcalculator.database.Storages.ExtraStorage;
import com.example.eventcalculator.database.Storages.HandoutStorage;
import com.example.eventcalculator.database.Storages.PersonalStorage;
import com.example.eventcalculator.database.Storages.PremiseStorage;
import com.example.eventcalculator.database.Storages.ProductStorage;
import com.example.eventcalculator.eventBusinessLogic.businessLogic.EquipmentLogic;
import com.example.eventcalculator.eventBusinessLogic.businessLogic.EventLogic;
import com.example.eventcalculator.eventBusinessLogic.businessLogic.ExtraLogic;
import com.example.eventcalculator.eventBusinessLogic.businessLogic.HandoutLogic;
import com.example.eventcalculator.eventBusinessLogic.businessLogic.PersonalLogic;
import com.example.eventcalculator.eventBusinessLogic.businessLogic.PremiseLogic;
import com.example.eventcalculator.eventBusinessLogic.businessLogic.ProductLogic;
import com.example.eventcalculator.eventBusinessLogic.models.EventModel;
import com.example.eventcalculator.eventBusinessLogic.models.PremiseModel;
import com.example.eventcalculator.eventBusinessLogic.models.ProductModel;

import java.util.Date;
import java.util.List;

public class MainActivity extends AppCompatActivity {
    private Button buttonPlanEvent;
    DatabaseHelper databaseHelper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        ProductStorage productStorage = new ProductStorage(this);
        ProductLogic productLogic = new ProductLogic(productStorage);

        PremiseStorage premiseStorage = new PremiseStorage(this);
        PremiseLogic premiseLogic = new PremiseLogic(premiseStorage);

        EquipmentStorage equipmentStorage = new EquipmentStorage(this);
        EquipmentLogic equipmentLogic = new EquipmentLogic(equipmentStorage);

        EventStorage eventStorage = new EventStorage(this);
        EventLogic eventLogic = new EventLogic(eventStorage);

        PersonalStorage personalStorage = new PersonalStorage(this);
        PersonalLogic personalLogic = new PersonalLogic(personalStorage);

        ExtraStorage extraStorage = new ExtraStorage(this);
        ExtraLogic extraLogic = new ExtraLogic(extraStorage);

        HandoutStorage handoutStorage = new HandoutStorage(this);
        HandoutLogic handoutLogic = new HandoutLogic(handoutStorage);

        buttonPlanEvent = (Button)findViewById(R.id.buttonPlanEvent);

        buttonPlanEvent.setOnClickListener(
                new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        Intent intent = new Intent(MainActivity.this, Event.class);
                        startActivity(intent);
                    }
                }
        );
    }
}