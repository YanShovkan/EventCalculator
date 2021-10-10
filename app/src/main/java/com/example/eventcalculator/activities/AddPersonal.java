package com.example.eventcalculator.activities;

import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;

import com.example.eventcalculator.R;
import com.example.eventcalculator.database.Storages.EventStorage;
import com.example.eventcalculator.database.Storages.PersonalStorage;
import com.example.eventcalculator.eventBusinessLogic.businessLogic.EventLogic;
import com.example.eventcalculator.eventBusinessLogic.businessLogic.PersonalLogic;
import com.example.eventcalculator.eventBusinessLogic.models.EventModel;
import com.example.eventcalculator.eventBusinessLogic.models.PersonalModel;

import java.util.List;

public class AddPersonal extends AppCompatActivity {

    EditText editTextName;
    EditText editTextPosition;
    EditText editTextPayment;
    PersonalLogic personalLogic;
    EventLogic eventLogic;
    int eventId = 0;
    Integer onePersonalId;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_personal);

        PersonalStorage personalStorage = new PersonalStorage(this);
        personalLogic = new PersonalLogic(personalStorage);

        EventStorage eventStorage = new EventStorage(this);
        eventLogic = new EventLogic(this, eventStorage);

        editTextName = (EditText) findViewById(R.id.editTextName);
        editTextPosition = (EditText) findViewById(R.id.editTextPosition);
        editTextPayment = (EditText) findViewById(R.id.editTextPayment);

        eventId = getIntent().getExtras().getInt("eventId");

        List<EventModel> eventList = eventLogic.read(null);

        int eventId = 1;
        if(!eventList.isEmpty()) {
            eventId = eventList.get(eventList.size() - 1).id + 1;
        }
    }

    public void addButtonClick(View view) {


        PersonalModel personalModel = new PersonalModel(
                editTextPosition.getText().toString(),
                editTextName.getText().toString(),
                Integer.parseInt(editTextPayment.getText().toString()));
        personalModel.setEventId(eventId);

        List<PersonalModel> list = personalLogic.Read(null);

//        int tempId = 1;
//        if(!list.isEmpty()) {
//            tempId = list.get(list.size() - 1).getId() + 1;
//        }
//
//        personalModel.setId(tempId);

        personalLogic.CreateOrUpdate(personalModel);
    }
}