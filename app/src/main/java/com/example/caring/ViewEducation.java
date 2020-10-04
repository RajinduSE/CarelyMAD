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
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.caring.dbHandlers.DbHandler;
import com.example.caring.models.education.Mark;

import es.dmoral.toasty.Toasty;

public class ViewEducation extends AppCompatActivity {
    private Button btn1, btn2, btn3, btn4, btn5, btn6;
    private EditText editText;
    private Context context;
    private DbHandler educationDb;

    public void addOperation(int grade, String term){
        if(term.isEmpty()){
            Toasty.error(getApplicationContext(), "Term Required", Toast.LENGTH_LONG).show();
            startActivity(new Intent(context, ViewEducation.class));
        }else{
            Mark mark = educationDb.getSingleMark(grade, Integer.parseInt(term));
            if(mark == null) {
                Intent intent = new Intent(context, InsertMarks.class);
                intent.putExtra("GRADE", String.valueOf(grade));
                intent.putExtra("TERM", term);
                startActivity(intent);
            }else {
                Toasty.error(getApplicationContext(), "Already Inserted", Toast.LENGTH_LONG).show();
                startActivity(new Intent(context, ViewEducation.class));
            }
        }
    }

    public void updateOperation(int grade, String term){
        if(term.isEmpty()){
            Toasty.error(getApplicationContext(), "Term Required", Toast.LENGTH_LONG).show();
            startActivity(new Intent(context, ViewEducation.class));
        }else{
            Mark mark = educationDb.getSingleMark(grade, Integer.parseInt(term));
            if(mark == null) {
                Toasty.error(getApplicationContext(), "No data", Toast.LENGTH_LONG).show();
                startActivity(new Intent(context, ViewEducation.class));
            }else {
                Intent intent = new Intent(context, UpdateMark.class);
                intent.putExtra("GRADE", String.valueOf(grade));
                intent.putExtra("TERM", term);
                startActivity(intent);
            }
        }
    }

    public void progressOperation(int grade){

        Mark mark1 = educationDb.getSingleMark(grade, 1);
        Mark mark2 = educationDb.getSingleMark(grade, 2);
        Mark mark3 = educationDb.getSingleMark(grade, 3);

        if(mark1 != null && mark2 != null && mark3 != null){
            Intent intent = new Intent(context, EducationProgress.class);
            intent.putExtra("GRADE", String.valueOf(grade));
            startActivity(intent);
        }else{
            Toasty.error(getApplicationContext(), "Three Terms Required", Toast.LENGTH_LONG).show();
            startActivity(new Intent(context, ViewEducation.class));
        }


    }


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_education);

        btn1 = findViewById(R.id.button1);
        btn2 = findViewById(R.id.button2);
        btn3 = findViewById(R.id.button3);
        btn4 = findViewById(R.id.button4);
        btn5 = findViewById(R.id.button5);
        btn6 = findViewById(R.id.button6);
        editText = findViewById(R.id.termVal);
        context = this;
        educationDb = new DbHandler(context);

        //educationDb.deleteMark(1,3);

        btn1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                AlertDialog.Builder builder = new AlertDialog.Builder(context);
                builder.setTitle("Grade1");

                builder.setPositiveButton("Progress", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {

                        progressOperation(1);
                    }
                });

                builder.setNegativeButton("Add", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        String userTerm = editText.getText().toString();
                        addOperation(1, userTerm);
                    }
                });

                builder.setNeutralButton("Update", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        String userTerm = editText.getText().toString();
                        updateOperation(1, userTerm);
                    }
                });

                builder.show();
            }
        });

        btn2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                AlertDialog.Builder builder = new AlertDialog.Builder(context);
                builder.setTitle("Grade2");

                builder.setPositiveButton("Progress", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        progressOperation(2);
                    }
                });

                builder.setNegativeButton("Add", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        String userTerm = editText.getText().toString();
                        addOperation(2, userTerm);
                    }
                });

                builder.setNeutralButton("Update", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        String userTerm = editText.getText().toString();
                        updateOperation(2, userTerm);
                    }
                });

                builder.show();
            }
        });

        btn3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                AlertDialog.Builder builder = new AlertDialog.Builder(context);
                builder.setTitle("Grade3");

                builder.setPositiveButton("Progress", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        progressOperation(3);
                    }
                });

                builder.setNegativeButton("Add", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        String userTerm = editText.getText().toString();
                        addOperation(3, userTerm);
                    }
                });

                builder.setNeutralButton("Update", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        String userTerm = editText.getText().toString();
                        updateOperation(3, userTerm);
                    }
                });

                builder.show();
            }
        });

        btn4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                AlertDialog.Builder builder = new AlertDialog.Builder(context);
                builder.setTitle("Grade4");

                builder.setPositiveButton("Progress", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        progressOperation(4);
                    }
                });

                builder.setNegativeButton("Add", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        String userTerm = editText.getText().toString();
                        addOperation(4, userTerm);
                    }
                });

                builder.setNeutralButton("Update", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        String userTerm = editText.getText().toString();
                        updateOperation(4, userTerm);
                    }
                });

                builder.show();
            }
        });

        btn5.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                AlertDialog.Builder builder = new AlertDialog.Builder(context);
                builder.setTitle("Grade5");

                builder.setPositiveButton("Progress", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        progressOperation(5);
                    }
                });

                builder.setNegativeButton("Add", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        String userTerm = editText.getText().toString();
                        addOperation(5, userTerm);
                    }
                });

                builder.setNeutralButton("Update", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        String userTerm = editText.getText().toString();
                        updateOperation(5, userTerm);
                    }
                });

                builder.show();
            }
        });

        btn6.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                AlertDialog.Builder builder = new AlertDialog.Builder(context);
                builder.setTitle("Grade6");

                builder.setPositiveButton("Progress", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        progressOperation(6);
                    }
                });

                builder.setNegativeButton("Add", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        String userTerm = editText.getText().toString();
                        addOperation(6, userTerm);
                    }
                });

                builder.setNeutralButton("Update", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        String userTerm = editText.getText().toString();
                        updateOperation(6, userTerm);
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