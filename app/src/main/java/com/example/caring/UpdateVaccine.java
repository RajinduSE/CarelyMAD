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
import android.widget.EditText;
import android.widget.Toast;

import com.example.caring.dbHandlers.DbHandler;
import com.example.caring.models.education.School;
import com.example.caring.models.health.Vaccine;

import es.dmoral.toasty.Toasty;

public class UpdateVaccine extends AppCompatActivity {
    private EditText name;
    private EditText age;
    private Button btn;
    private Context context;
    private DbHandler vaccineDbHandler;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_update_vaccine);

        name = findViewById(R.id.editVaccine);
        age = findViewById(R.id.editAge);
        btn = findViewById(R.id.addVaccine);
        context = this;
        vaccineDbHandler = new DbHandler(context);

        final String id = getIntent().getStringExtra("id");
        Vaccine vaccine = vaccineDbHandler.getSingleVaccine(Integer.parseInt(id));

        name.setText(vaccine.getVaccineName());
        age.setText(vaccine.getAge());

        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String editedName =  name.getText().toString();
                String editedAge = age.getText().toString();


                Vaccine vaccine = new Vaccine(Integer.parseInt(id), editedName, editedAge);
                int state = vaccineDbHandler.updateVaccine(vaccine);
                if(state > 0){
                    Toasty.success(getApplicationContext(), "Updated Successfully", Toast.LENGTH_LONG).show();
                    startActivity(new Intent(context, ViewVaccine.class));
                }else {
                    Toasty.error(getApplicationContext(), "Updating Error", Toast.LENGTH_LONG).show();
                    startActivity(new Intent(context, ViewVaccine.class));
                }
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