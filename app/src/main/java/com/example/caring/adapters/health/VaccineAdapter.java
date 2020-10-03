package com.example.caring.adapters.health;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.example.caring.R;
import com.example.caring.models.health.MedicsN;
import com.example.caring.models.health.Vaccine;

import java.util.List;

public class VaccineAdapter extends ArrayAdapter<Vaccine> {
    private Context context;
    private int resource;
    List<Vaccine> vaccines;

    public VaccineAdapter(Context context, int resource, List<Vaccine> vaccines) {
        super(context, resource, vaccines);
        this.context = context;
        this.resource = resource;
        this.vaccines = vaccines;
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        LayoutInflater inflater = LayoutInflater.from(context);
        View row = inflater.inflate(resource, parent, false);

        TextView vaccineText = row.findViewById(R.id.vaccine);
        TextView ageText = row.findViewById(R.id.age);

        Vaccine vaccine = vaccines.get(position);
        vaccineText.setText(vaccine.getVaccineName());
        ageText.setText(vaccine.getAge());

        return row;
    }
}
