package com.example.caring;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import com.example.caring.adapters.MainDashboardAdapter;

public class MainDashboard extends AppCompatActivity {

    ListView listView;
    String title[] = {"Education", "Health", "Achievements", "Time Table"};
    int image[] = {R.drawable.c, R.drawable.b, R.drawable.a, R.drawable.d};
    Context context = this;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_dashboard);

        listView = findViewById(R.id.list_view);
        MainDashboardAdapter customAdaptor = new MainDashboardAdapter(this, title, image);
        listView.setAdapter(customAdaptor);

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                switch (i){
                    case 0 : startActivity(new Intent(context, EducationDashboard.class));
                            break;
                    case 1 : startActivity(new Intent(context, Dashboard.class));
                            break;
                    case 2 : startActivity(new Intent(context, GalleryDashboard.class));
                            break;
                    case 3 : startActivity(new Intent(context, MainDashboard.class));
                            break;
                }
            }
        });
    }
}

