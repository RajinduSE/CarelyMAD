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
import com.example.caring.models.education.Mark;

import es.dmoral.toasty.Toasty;

public class InsertMarks extends AppCompatActivity {
    private EditText grade;
    private EditText term;
    private EditText total;
    private EditText subCount;
    private Button btn;
    private Context context;
    private DbHandler markDbHandler;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_insert_marks);

        grade = findViewById(R.id.insertGrade);
        term = findViewById(R.id.insertTerm);
        total = findViewById(R.id.insertTotal);
        subCount = findViewById(R.id.insertSubCount);
        btn = findViewById(R.id.insertBtn);
        context = this;

        grade.setEnabled(false);
        term.setEnabled(false);

        String userGrade = getIntent().getStringExtra("GRADE");
        String userTerm = getIntent().getStringExtra("TERM");

        grade.setText(userGrade);
        term.setText(userTerm);

        markDbHandler = new DbHandler(context);

        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                int userGrade = Integer.parseInt(grade.getText().toString());
                int userTerm = Integer.parseInt(term.getText().toString());
                double userTotal = Double.valueOf(total.getText().toString());
                int userSubCount = Integer.parseInt(subCount.getText().toString());

                Mark mark = new Mark(userGrade, userTerm, userTotal, userSubCount);
                if(markDbHandler.addMark(mark)){
                    Toasty.success(getApplicationContext(), "Inserted Successfully", Toast.LENGTH_LONG).show();
                    startActivity(new Intent(context, ViewEducation.class));
                }else{
                    Toasty.error(getApplicationContext(), "Inserting Error", Toast.LENGTH_LONG).show();
                    startActivity(new Intent(context, InsertMarks.class));
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

