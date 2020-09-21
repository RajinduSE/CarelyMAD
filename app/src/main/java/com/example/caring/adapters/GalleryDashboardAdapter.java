package com.example.caring.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.example.caring.R;

public class GalleryDashboardAdapter extends ArrayAdapter<String> {
    Context context;
    int[] images;
    String[] title;
    public GalleryDashboardAdapter(Context context, String[] title, int[] images){
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