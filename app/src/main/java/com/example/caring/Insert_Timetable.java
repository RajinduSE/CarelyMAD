package com.example.caring;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.caring.dbHandlers.DbHandler;
import com.example.caring.models.task.Task;

import es.dmoral.toasty.Toasty;

public class Insert_Timetable extends AppCompatActivity {

    private EditText title, description;
    private Button button;
    private DbHandler dbHandler;
    Context context;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_insert__timetable);

        title = findViewById(R.id.txtTitle);
        description = findViewById(R.id.txtDescription);
        button = findViewById(R.id.addTask);
        context = this;

        dbHandler = new DbHandler(context);

        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String userTitle = title.getText().toString();
                String userDescription = description.getText().toString();

                long started = System.currentTimeMillis();

                Task task = new Task(userTitle, userDescription, started, 0);
                if(dbHandler.addTask(task)){
                    Toasty.success(getApplicationContext(), "Inserted Successfully", Toast.LENGTH_LONG).show();
                    startActivity(new Intent(context, ViewTimetable.class));
                }else {
                    Toasty.error(getApplicationContext(), "Inserting Error", Toast.LENGTH_LONG).show();
                    startActivity(new Intent(context, ViewTimetable.class));
                }

            }
        });
    }
}