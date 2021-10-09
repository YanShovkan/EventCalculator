package com.example.eventcalculator.activities;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.example.eventcalculator.R;
import com.example.eventcalculator.database.DatabaseHelper;
import com.example.eventcalculator.database.Storages.ProductStorage;
import com.example.eventcalculator.eventBusinessLogic.models.ProductModel;

import java.util.List;

public class MainActivity extends AppCompatActivity {
    private Button buttonPlanEvent;
    DatabaseHelper databaseHelper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        /*ProductStorage productStorage = new ProductStorage(this);
        ProductLogic productLogic = new ProductLogic(productStorage);*/

        /*SQLiteDatabase db = getBaseContext().openOrCreateDatabase("app.db", MODE_PRIVATE, null);
        db.execSQL("CREATE TABLE IF NOT EXISTS users (name TEXT, age INTEGER)");
        db.execSQL("INSERT OR IGNORE INTO users VALUES ('Tom Smith', 23), ('John Dow', 31)");
        Cursor query = db.rawQuery("SELECT * FROM users;", null);
        if(query.moveToFirst()){
            String name = query.getString(0);
            int age = query.getInt(1);
        }*/

        ProductStorage productStorage = new ProductStorage(this);
        productStorage.open();

        productStorage.insert(new ProductModel(1, "bara", 1477, 228, 3));
        List<ProductModel> p = productStorage.getFullList();

        productStorage.close();

        //adapter.insert(new User(1, "Bibikin", 23));
        //User newUser = adapter.getUser(1);

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