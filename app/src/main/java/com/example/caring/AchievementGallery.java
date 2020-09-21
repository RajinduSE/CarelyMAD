package com.example.caring;


import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
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
}

