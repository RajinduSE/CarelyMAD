package com.example.caring;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.example.caring.dbHandlers.DbHandler;
import com.example.caring.models.education.School;
import com.example.caring.models.health.MedicsN;

public class ViewMedicine extends AppCompatActivity {

    private TextView txt1, txt2, txt3;
    private Button btn;
    private Context context;
    private DbHandler dbHandler;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_medicine);

        txt1 = findViewById(R.id.medicine);
        txt2 = findViewById(R.id.time);
        txt3 = findViewById(R.id.atATime);
        btn = findViewById(R.id.button);
        context = this;
        dbHandler = new DbHandler(context);

        final String id = getIntent().getStringExtra("id");
        MedicsN medicsN = dbHandler.getSingleMedics(Integer.parseInt(id));

        txt1.setText(medicsN.getMedicine());
        txt2.setText(medicsN.getTime());
        txt3.setText(medicsN.getAtATime());

        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(context, All_medicine.class));
            }
        });
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