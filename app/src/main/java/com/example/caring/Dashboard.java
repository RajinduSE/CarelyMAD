package com.example.caring;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.example.caring.dbHandlers.DbHandler;
import com.example.caring.models.health.Health;

public class Dashboard extends AppCompatActivity {
    private Button btn1, btn2, btn3;
    private Context context;
    private DbHandler dbHandler;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dashboard);

        btn1 = findViewById(R.id.dashboard_add_btn);
        btn2 = findViewById(R.id.dashboard_add_btn2);
        btn3 = findViewById(R.id.dashboard_add_btn3);
        context = this;
        dbHandler = new DbHandler(context);

        btn1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Health health = dbHandler.getSingleHealth();

                if(health != null){
                    startActivity(new Intent(context, UpdateHealth.class));
                }else{
                    startActivity(new Intent(context, InsertHealth.class));
                }
            }
        });

        btn2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(context, All_medicine.class));
            }
        });

        btn3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(context, ViewVaccine.class));
            }
        });
    }
}