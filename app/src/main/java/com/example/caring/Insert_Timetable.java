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

import com.basgeekball.awesomevalidation.AwesomeValidation;
import com.basgeekball.awesomevalidation.ValidationStyle;
import com.basgeekball.awesomevalidation.utility.RegexTemplate;
import com.example.caring.dbHandlers.DbHandler;
import com.example.caring.models.task.Task;

import es.dmoral.toasty.Toasty;

public class Insert_Timetable extends AppCompatActivity {

    private EditText title, description;
    private Button button;
    private DbHandler dbHandler;
    Context context;
    AwesomeValidation awesomeValidation;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_insert__timetable);

        title = findViewById(R.id.txtTitle);
        description = findViewById(R.id.txtDescription);
        button = findViewById(R.id.addTask);
        context = this;

        dbHandler = new DbHandler(context);

        //Validation Style
        awesomeValidation = new AwesomeValidation(ValidationStyle.BASIC);
        //Add validations

        awesomeValidation.addValidation(this,R.id.txtTitle, RegexTemplate.NOT_EMPTY,R.string.invalid_input);

        awesomeValidation.addValidation(this,R.id.txtDescription, RegexTemplate.NOT_EMPTY,R.string.invalid_input);



        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(awesomeValidation.validate()) {
                String userTitle = title.getText().toString();
                String userDescription = description.getText().toString();


                    long started = System.currentTimeMillis();

                    Task task = new Task(userTitle, userDescription, started, 0);
                    if (dbHandler.addTask(task)) {
                        Toasty.success(getApplicationContext(), "Inserted Successfully", Toast.LENGTH_LONG).show();
                        startActivity(new Intent(context, ViewTimetable.class));
                    } else {
                        Toasty.error(getApplicationContext(), "Inserting Error", Toast.LENGTH_LONG).show();
                        startActivity(new Intent(context, ViewTimetable.class));
                    }
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