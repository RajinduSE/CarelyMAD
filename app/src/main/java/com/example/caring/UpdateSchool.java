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
import com.example.caring.models.education.School;

import es.dmoral.toasty.Toasty;

public class UpdateSchool extends AppCompatActivity {
    private EditText name;
    private EditText phone;
    private EditText from;
    private Button btn;
    private Context context;
    private DbHandler schoolDbHandler;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_update_school);

        name = findViewById(R.id.editSchool);
        phone = findViewById(R.id.editPhone);
        from = findViewById(R.id.editFrom);
        btn = findViewById(R.id.editBtn);
        context = this;
        schoolDbHandler = new DbHandler(context);

        final String id = getIntent().getStringExtra("id");
        School school = schoolDbHandler.getSingleSchool(Integer.parseInt(id));

        name.setText(school.getName());
        phone.setText(String.valueOf(school.getPhone()));
        from.setText(String.valueOf(school.getFrom()));

        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String editedName =  name.getText().toString();
                int editedPhone = Integer.parseInt(phone.getText().toString());
                int editedFrom = Integer.parseInt(from.getText().toString());

                School school = new School(Integer.parseInt(id), editedName, editedPhone, editedFrom, 0);
                int state = schoolDbHandler.updateSingleSchool(school);
                if(state > 0){
                    Toasty.success(getApplicationContext(), "Updated Successfully", Toast.LENGTH_LONG).show();
                    startActivity(new Intent(context, Schools.class));
                }else {
                    Toasty.error(getApplicationContext(), "Updating Error", Toast.LENGTH_LONG).show();
                    startActivity(new Intent(context, InsertSchool.class));
                }
            }
        });
    }
}