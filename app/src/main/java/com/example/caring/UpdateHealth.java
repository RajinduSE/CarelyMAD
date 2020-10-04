package com.example.caring;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
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

    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.option_menu, menu);
        return true;
    }

    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.dashboard:
                startActivity(new Intent(this, MainDashboard.class));
                return true;
            case R.id.education:
                startActivity(new Intent(this, EducationDashboard.class));
                return true;
            case R.id.health:
                startActivity(new Intent(this, Dashboard.class));
                return true;
            case R.id.achievement:
                startActivity(new Intent(this, ViewAchievement.class));
                return true;
            case R.id.timeTable:
                startActivity(new Intent(this, ViewTimetable.class));
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }
}