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
import com.example.eventcalculator.database.Storages.ProductStorage;
import com.example.eventcalculator.eventBusinessLogic.businessLogic.ProductLogic;
import com.example.eventcalculator.eventBusinessLogic.models.ProductModel;

import java.util.List;

public class Products extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_products);
        Bundle arguments = getIntent().getExtras();
        ProductStorage productStorage = new ProductStorage(this);
        ProductLogic productLogic = new ProductLogic(productStorage);
        int data = getIntent().getExtras().getInt("eventId");

        fillTable(new String[]{"Название", "Стоимость за штуку", "Количество на человека"}, productLogic.read(null));
    }

    void fillTable(String[] titles, List<ProductModel> products) {
        TableLayout tableLayoutProducts = findViewById(R.id.tableLayoutProducts);

        TableRow tableRowTitles = new TableRow(this);

        for (String title : titles) {
            TextView textView = new TextView(this);

            textView.setText(title);
            textView.setTextColor(Color.WHITE);
            textView.setGravity(Gravity.CENTER);
            textView.setWidth(250);
            tableRowTitles.addView(textView);
        }

        tableRowTitles.setBackgroundColor(Color.parseColor("#FF6200EE"));
        tableLayoutProducts.addView(tableRowTitles);

        for (ProductModel product : products) {
            TableRow tableRow = new TableRow(this);

            TextView textViewName = new TextView(this);
            textViewName.setText(product.name);
            textViewName.setTextColor(Color.WHITE);
            textViewName.setGravity(Gravity.CENTER);

            TextView textViewCost = new TextView(this);
            textViewCost.setText(Integer.toString(product.price));
            textViewCost.setTextColor(Color.WHITE);
            textViewCost.setGravity(Gravity.CENTER);

            TextView textViewCount = new TextView(this);
            textViewCount.setText(Integer.toString(product.countPerPeople));
            textViewCount.setTextColor(Color.WHITE);
            textViewCount.setGravity(Gravity.CENTER);

            tableRow.addView(textViewName);
            tableRow.addView(textViewCost);
            tableRow.addView(textViewCount);

            tableRow.setBackgroundColor(Color.parseColor("#FF6200EE"));

            tableRow.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) { }
            });

            tableLayoutProducts.addView(tableRow);
        }
    }
}