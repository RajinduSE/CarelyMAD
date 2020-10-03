package com.example.caring;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.example.caring.dbHandlers.DbHandler;
import com.example.caring.models.health.Health;

public class UpdateHealth extends AppCompatActivity {
    private EditText weight,height,doctor,healthCondition,allergies;
    private Button button;
    private DbHandler healthDbHandler;
    private Context context;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_update_health);

        weight = findViewById(R.id.editWeight);
        height = findViewById(R.id.editHeight);
        doctor = findViewById(R.id.editDoctor);
        healthCondition = findViewById(R.id.editSpecialization);
        allergies = findViewById(R.id.editDoctor2);
        button = findViewById(R.id.health_add_btn);
        context = this;
        healthDbHandler = new DbHandler(context);

        Health health = healthDbHandler.getSingleHealth();

        weight.setText(String.valueOf(health.getWeight()));
        height.setText(String.valueOf(health.getHeight()));
        doctor.setText(health.getDoctor());
        healthCondition.setText(health.getHealthCondition());
        allergies.setText(health.getAllergies());


    }
}