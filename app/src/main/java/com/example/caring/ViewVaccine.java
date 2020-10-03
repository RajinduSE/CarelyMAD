package com.example.caring;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ListView;

import com.example.caring.adapters.education.SchoolAdapter;
import com.example.caring.adapters.health.VaccineAdapter;
import com.example.caring.dbHandlers.DbHandler;
import com.example.caring.models.education.School;
import com.example.caring.models.health.Vaccine;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

public class ViewVaccine extends AppCompatActivity {

    private Button add;
    private ListView listView;
    private Context context;
    private DbHandler vaccineDbHandler;
    private List<Vaccine> vaccines;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_vaccine);

        add = findViewById(R.id.btnAddVaccine);
        listView = findViewById(R.id.vaccinelist);
        context = this;
        vaccineDbHandler = new DbHandler(context);
        vaccines = new ArrayList<Vaccine>();

        vaccines = vaccineDbHandler.getAllVaccine();
        VaccineAdapter vaccineAdapter = new VaccineAdapter(context, R.layout.single_row_vaccine, vaccines);

        listView.setAdapter(vaccineAdapter);

        add.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(context, InjectionCourse.class));
            }
        });

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                final Vaccine vaccine = vaccines.get(i);
                AlertDialog.Builder builder = new AlertDialog.Builder(context);
                builder.setTitle(vaccine.getVaccineName());

                builder.setNegativeButton("Delete", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        vaccineDbHandler.deleteVaccine(vaccine.getVaccineId());
                        startActivity(new Intent(context, ViewVaccine.class));
                    }
                });

                builder.setNeutralButton("Update", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        Intent intent = new Intent(context, UpdateVaccine.class);
                        intent.putExtra("id", String.valueOf(vaccine.getVaccineId()));
                        startActivity(intent);
                    }
                });

                builder.show();
            }
        });

    }
}