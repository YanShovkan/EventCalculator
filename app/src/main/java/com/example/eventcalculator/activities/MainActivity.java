package com.example.eventcalculator.activities;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.example.eventcalculator.R;
import com.example.eventcalculator.database.DatabaseHelper;
import com.example.eventcalculator.database.Storages.PremiseStorage;
import com.example.eventcalculator.database.Storages.ProductStorage;
import com.example.eventcalculator.eventBusinessLogic.businessLogic.PremiseLogic;
import com.example.eventcalculator.eventBusinessLogic.businessLogic.ProductLogic;
import com.example.eventcalculator.eventBusinessLogic.models.ProductModel;

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


        productLogic.createOrUpdate(new ProductModel(1, "Гаврила", 123, 2, 1));
        productLogic.createOrUpdate(new ProductModel(0, "Гаврила", 23, 3, 1));
        productLogic.createOrUpdate(new ProductModel(0, "Томас", 23, 3, 1));
        productLogic.delete(new ProductModel(0, "Томас", 23, 3, 1));
        List<ProductModel> p = productLogic.read(null);

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