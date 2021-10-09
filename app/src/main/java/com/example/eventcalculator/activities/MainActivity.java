package com.example.eventcalculator.activities;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.example.eventcalculator.R;

public class MainActivity extends AppCompatActivity {
    private Button buttonPlanEvent;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

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