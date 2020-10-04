package com.example.caring;


import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.widget.ListView;
import com.example.caring.adapters.AchievementGalleryDashboardAdapter;

public class AchievementGallery extends AppCompatActivity {
    ListView listView;
    String title[] = {"Sportmeet 2019", "Prize giving 2020"};
    int image[] = {R.drawable.galley, R.drawable.gallery2};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_achievement_gallery);

        listView = findViewById(R.id.list_view);
        AchievementGalleryDashboardAdapter customAdaptor = new AchievementGalleryDashboardAdapter(this, title, image);
        listView.setAdapter(customAdaptor);
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

