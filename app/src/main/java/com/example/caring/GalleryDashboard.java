package com.example.caring;


import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ListView;
import com.example.caring.adapters.GalleryDashboardAdapter;

public class GalleryDashboard extends AppCompatActivity {

    ListView listView;

    String title[] = {"Achievement", "Gallery", "Update Achievement", "Delete Achievement"};

    int image[] = {R.drawable.ic_baseline_cloud_upload_24, R.drawable.ic_baseline_image_24, R.drawable.ic_baseline_update_24, R.drawable.ic_baseline_delete_24};
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_gallery_dashboard);

        listView = findViewById(R.id.list_view);
        GalleryDashboardAdapter customAdaptor = new GalleryDashboardAdapter(this, title, image);
        listView.setAdapter(customAdaptor);
    }
}

