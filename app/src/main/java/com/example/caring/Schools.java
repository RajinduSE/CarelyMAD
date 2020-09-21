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
import com.example.caring.dbHandlers.education.SchoolDbHandler;
import com.example.caring.models.education.School;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

public class Schools extends AppCompatActivity {

    private Button add;
    private ListView listView;
    private Context context;
    private SchoolDbHandler schoolDbHandler;
    private List<School> schools;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_schools);

        add = findViewById(R.id.btnAddSchool);
        listView = findViewById(R.id.schoollist);
        context = this;
        schoolDbHandler = new SchoolDbHandler(context);
        schools = new ArrayList<School>();

        schools = schoolDbHandler.getAllSchools();
        SchoolAdapter schoolAdapter = new SchoolAdapter(context, R.layout.single_school, schools);

        listView.setAdapter(schoolAdapter);

        add.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(context, InsertSchool.class));
            }
        });

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                final School school = schools.get(i);
                AlertDialog.Builder builder = new AlertDialog.Builder(context);
                builder.setTitle(school.getName());
                builder.setMessage("From - " + school.getFrom());

                builder.setPositiveButton("Leaved", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        Date date = new Date();
                        school.setTo(Calendar.getInstance().get(Calendar.YEAR));
                        schoolDbHandler.updateSingleSchool(school);
                        startActivity(new Intent(context, Schools.class));
                    }
                });

                builder.setNegativeButton("Delete", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        schoolDbHandler.deleteSchool(school.getId());
                        startActivity(new Intent(context, Schools.class));
                    }
                });

                builder.setNeutralButton("Update", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        Intent intent = new Intent(context, UpdateSchool.class);
                        intent.putExtra("id", String.valueOf(school.getId()));
                        startActivity(intent);
                    }
                });

                builder.show();
            }
        });

    }
}