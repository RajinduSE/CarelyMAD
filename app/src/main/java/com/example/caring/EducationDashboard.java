package com.example.caring;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import com.example.caring.adapters.EducationDashboardAdapter;
import com.example.caring.models.education.School;

public class EducationDashboard extends AppCompatActivity {

    ListView listView;
    String title[] = {"School", "Progress"};
    int image[] = {R.drawable.ic_baseline_cloud_upload_24, R.drawable.ic_baseline_update_24};
    Context context;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_education_dashboard);

        listView = findViewById(R.id.list_view);
        EducationDashboardAdapter customAdaptor = new EducationDashboardAdapter(this, title, image);
        listView.setAdapter(customAdaptor);
        context = this;

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                switch (i){
                    case 0 : startActivity(new Intent(context, Schools.class));
                        break;
                    case 1 : startActivity(new Intent(context, ViewEducation.class));
                        break;

                }
            }
        });
    }
}

