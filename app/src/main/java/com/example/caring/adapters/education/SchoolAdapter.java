package com.example.caring.adapters.education;

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
import com.example.caring.models.education.School;

import java.util.List;

public class SchoolAdapter extends ArrayAdapter<School> {
    private Context context;
    private int resource;
    List<School> schools;

    public SchoolAdapter(Context context, int resource, List<School> schools) {
        super(context, resource, schools);
        this.context = context;
        this.resource = resource;
        this.schools = schools;
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        LayoutInflater inflater = LayoutInflater.from(context);
        View row = inflater.inflate(resource, parent, false);

        TextView title = row.findViewById(R.id.schoolName);
        TextView description = row.findViewById(R.id.description);
        ImageView imageView = row.findViewById(R.id.chk);


        School school = schools.get(position);
        title.setText(school.getName());

        if(school.getTo() == 0){
            description.setText(String.valueOf(school.getFrom()));
        }else{
            description.setText(String.valueOf(school.getFrom()) + " - " + String.valueOf(school.getTo()));
        }

        imageView.setVisibility(row.INVISIBLE);

        if(school.getTo() != 0){
            imageView.setVisibility(View.VISIBLE);
        }

        return row;
    }
}
