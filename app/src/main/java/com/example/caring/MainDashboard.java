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

public class MainDashboard extends AppCompatActivity {

    ListView listView;

    String title[] = {"Education", "Health", "Achievements", "Time Table"};

    int image[] = {R.drawable.c, R.drawable.b, R.drawable.a, R.drawable.d};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_dashboard);

        listView = findViewById(R.id.list_view);
        CustomAdaptor customAdaptor = new CustomAdaptor(this, title, image);
        listView.setAdapter(customAdaptor);
    }
}

class CustomAdaptor extends ArrayAdapter<String> {
    Context context;
    int[] images;
    String[] title;
    CustomAdaptor(Context context, String[] title, int[] images){
        super(context, R.layout.single_row_dashboard, R.id.title, title);
        this.context = context;
        this.images = images;
        this.title = title;
    }

    //create a single row
    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View row = inflater.inflate(R.layout.single_row_dashboard, parent, false);
        ImageView imageView = row.findViewById(R.id.imageView);
        TextView titleView = row.findViewById(R.id.title);

        imageView.setImageResource(images[position]);
        titleView.setText(title[position]);
        return row;
    }
}