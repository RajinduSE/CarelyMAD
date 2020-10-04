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

public class Update_Timetable extends AppCompatActivity {

    private EditText title, description;
    private Button button;
    private DbHandler dbHandler;
    private Context context;
    private Long updateDate;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_update__timetable);

        title = findViewById(R.id.txtTitle);
        description = findViewById(R.id.txtDescription);
        button = findViewById(R.id.editTask);
        context = this;
        dbHandler = new DbHandler(context);

        final String id = getIntent().getStringExtra("id");
        Task task = dbHandler.getSingleTask(Integer.parseInt(id));

        title.setText(task.getTitle());
        description.setText(task.getDescription());

        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String editTitle = title.getText().toString();
                String editDescription = description.getText().toString();
                updateDate = System.currentTimeMillis();

                Task task = new Task(Integer.parseInt(id) , editTitle, editDescription, updateDate, 0);
                int state = dbHandler.updateSingleTask(task);

                if(state > 0){
                    Toasty.success(getApplicationContext(), "Updated Successfully", Toast.LENGTH_LONG).show();
                    startActivity(new Intent(context, ViewTimetable.class));
                }else{
                    Toasty.error(getApplicationContext(), "Updating Error", Toast.LENGTH_LONG).show();
                    startActivity(new Intent(context, ViewTimetable.class));
                }


            }
        });
    }
}