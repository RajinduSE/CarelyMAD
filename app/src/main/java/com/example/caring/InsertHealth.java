package com.example.caring;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.example.caring.dbHandlers.DbHandler;
import com.example.caring.models.health.Health;

public class InsertHealth extends AppCompatActivity {
    private EditText weight,height,doctor,healthCondition,allergies;
    private Button button;
    private DbHandler healthDbHandler;
    private Context context;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_insert_health);

        weight = findViewById(R.id.inputWeight);
        height = findViewById(R.id.inputHeight);
        doctor = findViewById(R.id.inputDoctor);
        healthCondition = findViewById(R.id.inputSpecialization);
        allergies = findViewById(R.id.inputDoctor2);
        button = findViewById(R.id.health_add_btn);
        context = this;
        healthDbHandler = new DbHandler(context);

        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                double addWeight = Double.valueOf(weight.getText().toString());
                double addHeight = Double.valueOf(height.getText().toString());
                String addDoctor = height.getText().toString();
                String addHealthCondition = healthCondition.getText().toString();
                String addAllergies = allergies.getText().toString();

                Health insertHealth = new Health(addWeight,addHeight,addDoctor,addHealthCondition,addAllergies);

                healthDbHandler.addHealth(insertHealth);
                startActivity(new Intent(context,UpdateHealth.class));
            }
        });
    }
}