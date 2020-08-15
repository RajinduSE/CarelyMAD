package com.example.caring;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

public class GalleryDashboard extends AppCompatActivity {

    ListView listView;

    String title[] = {"Achievement", "Gallery", "Update Achievement", "Delete Achievement"};

    int image[] = {R.drawable.ic_baseline_cloud_upload_24, R.drawable.ic_baseline_image_24, R.drawable.ic_baseline_update_24, R.drawable.ic_baseline_delete_24};
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_gallery_dashboard);

        listView = findViewById(R.id.list_view);
        CustomAdaptor5 customAdaptor = new CustomAdaptor5(this, title, image);
        listView.setAdapter(customAdaptor);
    }
}

class CustomAdaptor5 extends ArrayAdapter<String> {
    Context context;
    int[] images;
    String[] title;
    CustomAdaptor5(Context context, String[] title, int[] images){
        super(context, R.layout.single_row_education_dashboard, R.id.title, title);
        this.context = context;
        this.images = images;
        this.title = title;
    }

    //create a single row
    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View row = inflater.inflate(R.layout.single_row_education_dashboard, parent, false);
        ImageView imageView = row.findViewById(R.id.imageView);
        TextView titleView = row.findViewById(R.id.title);

        imageView.setImageResource(images[position]);
        titleView.setText(title[position]);
        return row;
    }
}