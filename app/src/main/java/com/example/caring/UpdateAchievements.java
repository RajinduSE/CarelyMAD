package com.example.caring;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.caring.dbHandlers.DbHandler;
import com.example.caring.models.achievement.AchievementModel;
import com.example.caring.models.education.School;

import es.dmoral.toasty.Toasty;

public class UpdateAchievements extends AppCompatActivity {
    private EditText event;
    private EditText description;
    private EditText award;
    private EditText year;
    private Button btn;
    private Context context;
    private DbHandler achievementDbHandler;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_update_achievements);

        event = findViewById(R.id.editEvent);
        description = findViewById(R.id.editDescription);
        award = findViewById(R.id.editAwards);
        year = findViewById(R.id.editDate);
        btn = findViewById(R.id.edit_achievement);

        context = this;
        achievementDbHandler = new DbHandler(context);

        final String id = getIntent().getStringExtra("id");
        AchievementModel achievementModel = achievementDbHandler.getSingleAchievement(Integer.parseInt(id));

        event.setText(achievementModel.getEvent());
        description.setText(achievementModel.getDescription());
        award.setText(achievementModel.getAward());
        year.setText(String.valueOf(achievementModel.getYear()));

        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String editedEvent =  event.getText().toString();
                String editedDescription = description.getText().toString();
                String editedAward = award.getText().toString();
                String editedYear = year.getText().toString();

                AchievementModel achievementModel = new AchievementModel(Integer.parseInt(id), editedEvent, editedDescription, editedAward, Integer.parseInt(editedYear));
                int state = achievementDbHandler.updateSingleAchievement(achievementModel);
                if(state > 0){
                    Toasty.success(getApplicationContext(), "Updated Successfully", Toast.LENGTH_LONG).show();
                    startActivity(new Intent(context, ViewAchievement.class));
                }else {
                    Toasty.error(getApplicationContext(), "Updating Error", Toast.LENGTH_LONG).show();
                    startActivity(new Intent(context, ViewAchievement.class));
                }
            }
        });
    }
}