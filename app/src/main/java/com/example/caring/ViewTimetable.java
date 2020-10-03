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
import android.widget.TextView;

import com.example.caring.adapters.task.TaskAdapter;
import com.example.caring.dbHandlers.DbHandler;
import com.example.caring.models.task.Task;

import java.util.ArrayList;
import java.util.List;

public class ViewTimetable extends AppCompatActivity {

    private Button add;
    private ListView listView;
    private TextView textView;
    Context context;
    private DbHandler dbHandler;
    private List<Task> tasks;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_timetable);

        add = findViewById(R.id.addTask);
        listView = findViewById(R.id.taskList);
        textView = findViewById(R.id.taskCount);
        context = this;
        dbHandler = new DbHandler(context);
        tasks = new ArrayList<Task>();

        tasks = dbHandler.getAllTasks();
        TaskAdapter taskAdapter = new TaskAdapter(context, R.layout.single_task, tasks);

        listView.setAdapter(taskAdapter);

        int countTask = dbHandler.countTask();
        textView.setText("You have " + countTask + " tasks");

        add.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(context, Insert_Timetable.class));
            }
        });

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                final Task task = tasks.get(i);
                AlertDialog.Builder builder = new AlertDialog.Builder(context);
                builder.setTitle(task.getTitle());
                builder.setMessage(task.getDescription());

                builder.setPositiveButton("Finished", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        task.setFinished(System.currentTimeMillis());
                        dbHandler.updateSingleTask(task);
                        startActivity(new Intent(context, ViewTimetable.class));
                    }
                });
                builder.setNegativeButton("Delete", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        dbHandler.deleteTask(task.getId());
                        startActivity(new Intent(context, ViewTimetable.class));
                    }
                });
                builder.setNeutralButton("Update", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        Intent intent = new Intent(context, Update_Timetable.class);
                        intent.putExtra("id", String.valueOf(task.getId()));
                        startActivity(intent);
                    }
                });
                builder.show();
            }
        });
    }
}

