package com.example.caring;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ListView;

import com.example.caring.adapters.education.SchoolAdapter;
import com.example.caring.adapters.health.MedicineAdapter;
import com.example.caring.dbHandlers.DbHandler;
import com.example.caring.models.education.School;
import com.example.caring.models.health.MedicsN;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

public class All_medicine extends AppCompatActivity {
    private Button add;
    private ListView listView;
    private Context context;
    private DbHandler medicsDbHandler;
    private List<MedicsN> medicines;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_all_medicine);

        add = findViewById(R.id.btnAddMedicine);
        listView = findViewById(R.id.medicinelist);
        context = this;
        medicsDbHandler = new DbHandler(context);
        medicines = new ArrayList<MedicsN>();

        medicines = medicsDbHandler.getAllMedics();
        MedicineAdapter medicineAdapter = new MedicineAdapter(context, R.layout.single_row_medics, medicines);

        listView.setAdapter(medicineAdapter);

        add.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(context, Medics.class));
            }
        });

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                final MedicsN medicsN = medicines.get(i);
                AlertDialog.Builder builder = new AlertDialog.Builder(context);
                builder.setTitle(medicsN.getMedicine());

                builder.setPositiveButton("View", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        Intent intent = new Intent(context, ViewMedicine.class);
                        intent.putExtra("id", String.valueOf(medicsN.getMedicsId()));
                        startActivity(intent);
                    }
                });

                builder.setNegativeButton("Delete", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        medicsDbHandler.deleteMedics(medicsN.getMedicsId());
                        startActivity(new Intent(context, All_medicine.class));
                    }
                });

                builder.show();
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