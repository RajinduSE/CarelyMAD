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
import com.example.caring.models.education.Mark;

import es.dmoral.toasty.Toasty;

public class UpdateMark extends AppCompatActivity {
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
        setContentView(R.layout.activity_update_mark);

        grade = findViewById(R.id.editGrade);
        term = findViewById(R.id.editTerm);
        total = findViewById(R.id.editTotal);
        subCount = findViewById(R.id.editSubCount);
        btn = findViewById(R.id.editBtn);
        context = this;
        markDbHandler = new DbHandler(context);

        grade.setEnabled(false);
        term.setEnabled(false);

        int userGrade = Integer.parseInt(getIntent().getStringExtra("GRADE"));
        int userTerm = Integer.parseInt(getIntent().getStringExtra("TERM"));

        Mark mark = markDbHandler.getSingleMark(userGrade,userTerm);

        grade.setText(String.valueOf(mark.getGrade()));
        term.setText(String.valueOf(mark.getTerm()));
        total.setText(String.valueOf(mark.getTotal()));
        subCount.setText(String.valueOf(mark.getSubCount()));

        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                int userGrade = Integer.parseInt(grade.getText().toString());
                int userTerm = Integer.parseInt(term.getText().toString());
                double userTotal = Double.valueOf(total.getText().toString());
                int userSubCount = Integer.parseInt(subCount.getText().toString());

                Mark mark = new Mark(userGrade, userTerm, userTotal, userSubCount);

                int status = markDbHandler.updateSingleMark(mark);

                if(status > 0){
                    Toasty.success(getApplicationContext(), "Successfully Updated", Toast.LENGTH_LONG).show();
                    startActivity(new Intent(context, ViewEducation.class));
                }else{
                    Toasty.error(getApplicationContext(), "Updating Error", Toast.LENGTH_LONG).show();
                    startActivity(new Intent(context, ViewEducation.class));
                }
            }
        });



    }
}