package com.example.eventcalculator.activities;

import android.os.Bundle;
import android.view.View;
import android.widget.EditText;

import androidx.appcompat.app.AppCompatActivity;

import com.example.eventcalculator.R;
import com.example.eventcalculator.database.Storages.PersonalStorage;
import com.example.eventcalculator.eventBusinessLogic.businessLogic.PersonalLogic;
import com.example.eventcalculator.eventBusinessLogic.models.PersonalModel;

import java.util.Arrays;

public class OnePersonal extends AppCompatActivity {
        PersonalStorage personalStorage = new PersonalStorage(this);
        PersonalLogic personalLogic=new PersonalLogic(personalStorage);
        Integer onePersonalId;
        PersonalModel personalModel;
        int eventId;

        @Override
        protected void onCreate(Bundle savedInstanceState) {
                super.onCreate(savedInstanceState);
                setContentView(R.layout.activity_onepersonal);

                onePersonalId = getIntent().getExtras().getInt("OnePersonalId");
                eventId = getIntent().getExtras().getInt("eventId");

                if(onePersonalId != null){
                        PersonalModel person = new PersonalModel();
                        person.setId(onePersonalId);
                        setPersonal(personalStorage.getElement(person));
                }

        }

        public void setPersonal(PersonalModel personalModel){
                this.personalModel = personalModel;
                EditText editText = (EditText) findViewById(R.id.editText_OnePersonalPosition);
                editText.setText(personalModel.position);
                editText = (EditText) findViewById(R.id.editText_OnePersonalPayment);
                editText.setText(personalModel.payment);
                editText = (EditText) findViewById(R.id.editText_PersonName);
                editText.setText(personalModel.name);
        }

        public void buttonSave_onClick(View view) {
                PersonalModel savePersonalModel = new PersonalModel();
                EditText editText = (EditText) findViewById(R.id.editText_OnePersonalPosition);
                savePersonalModel.position=editText.getText().toString();
                editText = (EditText) findViewById(R.id.editText_OnePersonalPayment);
                savePersonalModel.payment=Integer.parseInt(editText.getText().toString());
                editText = (EditText) findViewById(R.id.editText_PersonName);
                savePersonalModel.name=editText.getText().toString();
                savePersonalModel.eventId=eventId;
                if(personalModel!=null){
                        savePersonalModel.id=personalModel.id;
                }
                personalLogic.CreateOrUpdate(savePersonalModel);
        }
}
