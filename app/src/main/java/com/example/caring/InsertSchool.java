package com.example.caring;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.caring.dbHandlers.education.SchoolDbHandler;
import com.example.caring.models.education.School;

import es.dmoral.toasty.Toasty;

public class InsertSchool extends AppCompatActivity {

    private EditText name;
    private EditText phone;
    private EditText from;
    private Button btn;
    private Context context;
    private SchoolDbHandler schoolDbHandler;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_insert_school);

        name = findViewById(R.id.editSchool);
        phone = findViewById(R.id.editPhone);
        from = findViewById(R.id.editFrom);
        btn = findViewById(R.id.editBtn);
        context = this;

        schoolDbHandler = new SchoolDbHandler(context);

        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String inputName = name.getText().toString();
                int inputPhone = Integer.parseInt(phone.getText().toString());
                int inputFrom = Integer.parseInt(from.getText().toString());

                School school = new School(inputName, inputPhone, inputFrom, 0);
                if(schoolDbHandler.addSchool(school)){
                    Toasty.success(getApplicationContext(), "Inserted Successfully", Toast.LENGTH_LONG).show();
                    startActivity(new Intent(context, Schools.class));
                }else{
                    Toasty.error(getApplicationContext(), "Inserting Error", Toast.LENGTH_LONG).show();
                    startActivity(new Intent(context, InsertSchool.class));
                }
            }
        });


    }
}