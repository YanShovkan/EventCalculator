package com.example.eventcalculator.activities;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentManager;

import android.app.AlertDialog;
import android.app.Dialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.example.eventcalculator.R;
import com.example.eventcalculator.database.Storages.EquipmentStorage;
import com.example.eventcalculator.database.Storages.EventStorage;
import com.example.eventcalculator.database.Storages.HandoutStorage;
import com.example.eventcalculator.database.Storages.PersonalStorage;
import com.example.eventcalculator.database.Storages.PremiseStorage;
import com.example.eventcalculator.database.Storages.ProductStorage;
import com.example.eventcalculator.eventBusinessLogic.businessLogic.EquipmentLogic;
import com.example.eventcalculator.eventBusinessLogic.businessLogic.EventLogic;
import com.example.eventcalculator.eventBusinessLogic.businessLogic.HandoutLogic;
import com.example.eventcalculator.eventBusinessLogic.businessLogic.PersonalLogic;
import com.example.eventcalculator.eventBusinessLogic.businessLogic.PremiseLogic;
import com.example.eventcalculator.eventBusinessLogic.businessLogic.ProductLogic;
import com.example.eventcalculator.eventBusinessLogic.models.EquipmentModel;
import com.example.eventcalculator.eventBusinessLogic.models.EventModel;
import com.example.eventcalculator.eventBusinessLogic.models.HandoutModel;
import com.example.eventcalculator.eventBusinessLogic.models.PersonalModel;
import com.example.eventcalculator.eventBusinessLogic.models.PremiseModel;
import com.example.eventcalculator.eventBusinessLogic.models.ProductModel;

import java.util.List;

public class Event extends AppCompatActivity {
    private Button buttonAddProducts;
    private Button buttonAddHandouts;
    private Button buttonAddPersonal;
    private Button buttonAddPremises;
    private Button buttonAddEquipments;
    private Button buttonCreateEvent;
    private EditText editText_EventName;
    private EditText editText_PersonCount;
    private EditText editText_DaysCount;
    EventLogic eventLogic;
    EquipmentLogic equipmentLogic;
    PersonalLogic personalLogic;
    PremiseLogic premiseLogic;
    ProductLogic productLogic;
    HandoutLogic handoutLogic;
    int eventId;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_event);

        eventLogic = new EventLogic(new EventStorage(this));
        equipmentLogic = new EquipmentLogic(new EquipmentStorage(this));
        personalLogic = new PersonalLogic(new PersonalStorage(this));
        premiseLogic = new PremiseLogic(new PremiseStorage(this));
        productLogic = new ProductLogic(new ProductStorage(this));
        handoutLogic = new HandoutLogic(new HandoutStorage(this));

        List<EventModel> eventList = eventLogic.read(null);
        buttonAddProducts = (Button)findViewById(R.id.buttonAddProducts);
        editText_EventName = findViewById(R.id.editText_EventName);
        editText_PersonCount = findViewById(R.id.editText_PersonCount);
        editText_DaysCount = findViewById(R.id.editText_DaysCount);


        eventId = 1;

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
                        eventLogic.createOrUpdate(new EventModel(editText_EventName.getText().toString(), Integer.parseInt(editText_DaysCount.getText().toString()),Integer.parseInt(editText_PersonCount.getText().toString()) ));

                        int totalCost;

                        PremiseModel premiseModel = new PremiseModel();
                        premiseModel.setEventId(eventId);
                        List<PremiseModel> premises = premiseLogic.read(premiseModel);


                        ProductModel productModel = new ProductModel();
                        productModel.setEventId(eventId);
                        List<ProductModel> products = productLogic.read(productModel);


                        HandoutModel handoutModel = new HandoutModel();
                        handoutModel.setEventId(eventId);
                        List<HandoutModel> handouts = handoutLogic.Read(handoutModel);


                        PersonalModel personalModel = new PersonalModel();
                        personalModel.setEventId(eventId);
                        List<PersonalModel> personal = personalLogic.Read(personalModel);


                        EquipmentModel equipmentModel = new EquipmentModel();
                        equipmentModel.setEventId(eventId);
                        List<EquipmentModel> equipments = equipmentLogic.Read(equipmentModel);



                        AlertDialog.Builder builder = new AlertDialog.Builder(Event.this);
                        builder.setMessage("Итоговая стоимость вашего мероприятия: ");
                        builder.setCancelable(true);

                        builder.setPositiveButton(
                                "Понятненько",
                                new DialogInterface.OnClickListener() {
                                    public void onClick(DialogInterface dialog, int id) {
                                        dialog.cancel();
                                    }
                                });

                        AlertDialog alert = builder.create();
                        alert.show();
                    }
                }
        );
    }
}