package com.example.eventcalculator.activities;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

import com.example.eventcalculator.R;
import com.example.eventcalculator.helperClasses.ViewTables;

public class Products extends AppCompatActivity {
    ViewTables<Product> table = new ViewTables<Product>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_products);

        table.fillTable(new String[] {"name", "surname"},new Product[]{ new Product("yan", "shovkan"), new Product("Vald", "Reznikov")} , this);
    }
}
class Product{

    String name;
    String surname;

    Product(String name, String surname){
        this.name = name;
        this.surname = surname;
    }
}