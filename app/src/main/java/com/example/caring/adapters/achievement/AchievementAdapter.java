package com.example.caring.adapters.achievement;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.example.caring.R;
import com.example.caring.models.achievement.AchievementModel;


import java.util.List;

public class AchievementAdapter extends ArrayAdapter<AchievementModel> {
    private Context context;
    private int resource;
    List<AchievementModel> achievements;

    public AchievementAdapter(Context context, int resource, List<AchievementModel> achievements) {
        super(context, resource, achievements);
        this.context = context;
        this.resource = resource;
        this.achievements = achievements;
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        LayoutInflater inflater = LayoutInflater.from(context);
        View row = inflater.inflate(resource, parent, false);

        TextView event = row.findViewById(R.id.event);
        TextView award = row.findViewById(R.id.award);
        TextView year = row.findViewById(R.id.year);


        AchievementModel achievementModel = achievements.get(position);
        event.setText(achievementModel.getEvent());
        award.setText(achievementModel.getAward());
        year.setText(String.valueOf(achievementModel.getYear()));

        return row;
    }
}
