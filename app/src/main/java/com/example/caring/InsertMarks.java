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

public class InsertMarks extends AppCompatActivity {
    ListView listView;
    String title[] = {"Maths", "Sinhala", "English", "Tamil", "Religion", "ENV", "Dancing", "Music", "Art"};
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_insert_marks);

        listView = findViewById(R.id.list_view);
        CustomAdaptor2 customAdaptor = new CustomAdaptor2(this, title);
        listView.setAdapter(customAdaptor);
    }
}

class CustomAdaptor2 extends ArrayAdapter<String> {
    Context context;
    String[] title;
    CustomAdaptor2(Context context, String[] title){
        super(context, R.layout.marks_single_subject, R.id.title, title);
        this.context = context;
        this.title = title;
    }

    //create a single row
    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View row = inflater.inflate(R.layout.marks_single_subject, parent, false);
        TextView titleView = row.findViewById(R.id.title);

        titleView.setText(title[position]);
        return row;
    }
}