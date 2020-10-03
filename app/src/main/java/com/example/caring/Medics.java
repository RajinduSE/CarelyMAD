package com.example.caring;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.Toast;

import com.example.caring.dbHandlers.DbHandler;
import com.example.caring.models.health.MedicsN;

import java.util.ArrayList;

import es.dmoral.toasty.Toasty;

public class Medics extends AppCompatActivity {
    private EditText medicine, atATime, description;
    private Button button;
    private CheckBox checkbox, checkBox2, checkBox3;
    private DbHandler medicsDbHandler;
    private ArrayList<String> time;
    Context context;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_medics);

        medicine = findViewById(R.id.inputMedicine);
        atATime = findViewById(R.id.inputAtaTime);
        description = findViewById(R.id.inputDescription);
        button = findViewById(R.id.health_add_medics);
        time = new ArrayList<>();
        context = this;
        checkbox = findViewById(R.id.checkBox);
        checkBox2 = findViewById(R.id.checkBox2);
        checkBox3 = findViewById(R.id.checkBox3);
        medicsDbHandler = new DbHandler(context);

        checkbox.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (checkbox.isChecked()) {
                    time.add("Morning");
                } else {
                    time.remove("Morning");
                }
            }

        });
        checkBox2.setOnClickListener((new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (checkBox2.isChecked()) {
                    time.add("Day");
                } else {
                    time.remove("Day");
                }
            }
        }));

        checkBox3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (checkBox3.isChecked()) {
                    time.add("Night");
                } else {
                    time.remove("Night");
                }
            }
        });

        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                String addMedicine = medicine.getText().toString();
                String addAtATime = atATime.getText().toString();
                String addDescription = description.getText().toString();

                StringBuilder stringBuilder = new StringBuilder();
                for (String s : time) {
                    stringBuilder.append(s).append(" ");
                }

                MedicsN medicsN = new MedicsN(addMedicine, stringBuilder.toString(), addAtATime, addDescription);
                boolean status = medicsDbHandler.addMedics(medicsN);

                if (status) {
                    Toasty.success(getApplicationContext(), "Inserted Successfully", Toast.LENGTH_LONG).show();
                    startActivity(new Intent(context, All_medicine.class));
                } else {
                    Toasty.error(getApplicationContext(), "Inserting Error", Toast.LENGTH_LONG).show();
                    startActivity(new Intent(context, All_medicine.class));
                }
            }
        });
    }
}