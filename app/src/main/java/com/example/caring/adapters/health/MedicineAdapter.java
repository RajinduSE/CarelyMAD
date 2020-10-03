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

import java.util.List;

public class MedicineAdapter extends ArrayAdapter<MedicsN> {
    private Context context;
    private int resource;
    List<MedicsN> medicines;

    public MedicineAdapter(Context context, int resource, List<MedicsN> medicines) {
        super(context, resource, medicines);
        this.context = context;
        this.resource = resource;
        this.medicines = medicines;
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        LayoutInflater inflater = LayoutInflater.from(context);
        View row = inflater.inflate(resource, parent, false);

        TextView medicine = row.findViewById(R.id.medicine);
        TextView time = row.findViewById(R.id.time);

        MedicsN medicsN = medicines.get(position);
        medicine.setText(medicsN.getMedicine());
        time.setText(medicsN.getTime());

        return row;
    }
}
