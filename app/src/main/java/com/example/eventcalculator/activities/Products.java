package com.example.eventcalculator.activities;

import androidx.appcompat.app.AppCompatActivity;
import android.graphics.Color;
import android.graphics.Point;
import android.os.Bundle;
import android.view.Display;
import android.view.Gravity;
import android.view.View;
import android.widget.TableLayout;
import android.widget.TableRow;
import android.widget.TextView;

import com.example.eventcalculator.R;

import com.example.eventcalculator.database.Storages.ProductStorage;
import com.example.eventcalculator.eventBusinessLogic.businessLogic.ProductLogic;
import com.example.eventcalculator.eventBusinessLogic.models.ProductModel;

import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;

public class Products extends AppCompatActivity {

    TableRow selectedRow;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_products);
        selectedRow = new TableRow(this);
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

            textView.setTextSize(16);
            textView.setText(title);
            textView.setTextColor(Color.WHITE);
            textView.setGravity(Gravity.CENTER);
            textView.setWidth( getWindowManager().getDefaultDisplay().getWidth() / 3);
            tableRowTitles.addView(textView);
        }

        tableRowTitles.setBackgroundColor(Color.parseColor("#FF6200EE"));
        tableLayoutProducts.addView(tableRowTitles);


        for (ProductModel product : products) {
            TableRow tableRow = new TableRow(this);

            TextView textViewName = new TextView(this);
            textViewName.setText(product.name);
            textViewName.setHeight(170);
            textViewName.setTextSize(16);
            textViewName.setTextColor(Color.WHITE);
            textViewName.setGravity(Gravity.CENTER);

            TextView textViewCost = new TextView(this);
            textViewName.setHeight(170);
            textViewCost.setTextSize(16);
            textViewCost.setText(String.valueOf(product.price));
            textViewCost.setTextColor(Color.WHITE);
            textViewCost.setGravity(Gravity.CENTER);

            TextView textViewCount = new TextView(this);
            textViewName.setHeight(170);
            textViewCount.setTextSize(16);
            textViewCount.setText(String.valueOf(product.countPerPeople));
            textViewCount.setTextColor(Color.WHITE);
            textViewCount.setGravity(Gravity.CENTER);

            TextView textViewId = new TextView(this);
            textViewId.setVisibility(View.INVISIBLE);
            textViewId.setText(String.valueOf(product.id));

            tableRow.addView(textViewName);
            tableRow.addView(textViewCost);
            tableRow.addView(textViewCount);

            tableRow.setBackgroundColor(Color.parseColor("#FF6200EE"));

            tableRow.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {

                    selectedRow = tableRow;

                    for(int i = 0; i < tableLayoutProducts.getChildCount(); i++){
                        View view = tableLayoutProducts.getChildAt(i);
                        if (view instanceof TableRow ){
                            view.setBackgroundColor(Color.parseColor("#FF6200EE"));
                        }
                    }

                    tableRow.setBackgroundColor(Color.parseColor("#FF6200FF"));
                }
            });

            tableLayoutProducts.addView(tableRow);
        }
    }

    List<ProductModel> getProducts(){
        List<ProductModel> products = new LinkedList<ProductModel>();

        for(int i = 0; i < 10; i++){
            products.add(new ProductModel("name" + i,i*10,i*2,1));
        }

        return  products;
    }
}