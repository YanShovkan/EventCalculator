package com.example.eventcalculator;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class Event extends AppCompatActivity {
    private Button buttonAddProducts;
    private Button buttonAddHandouts;
    private Button buttonAddPersonal;
    private Button buttonAddPremises;
    private Button buttonAddEquipments;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_event);

        buttonAddProducts = (Button)findViewById(R.id.buttonAddProducts);

        buttonAddProducts.setOnClickListener(
                new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        Intent intent = new Intent(Event.this, Products.class);
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
    }
}