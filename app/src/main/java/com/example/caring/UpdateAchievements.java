package com.example.caring;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.basgeekball.awesomevalidation.AwesomeValidation;
import com.basgeekball.awesomevalidation.ValidationStyle;
import com.basgeekball.awesomevalidation.utility.RegexTemplate;
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
    AwesomeValidation awesomeValidation;

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

        //Validation Style
        awesomeValidation = new AwesomeValidation(ValidationStyle.BASIC);

        //Add validations
        awesomeValidation.addValidation(this,R.id.editEvent, RegexTemplate.NOT_EMPTY,R.string.invalid_input);
        awesomeValidation.addValidation(this,R.id.editDescription, RegexTemplate.NOT_EMPTY,R.string.invalid_input);
        awesomeValidation.addValidation(this,R.id.editAwards, RegexTemplate.NOT_EMPTY,R.string.invalid_input);
        awesomeValidation.addValidation(this,R.id.editDate, RegexTemplate.NOT_EMPTY,R.string.invalid_input);



        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(awesomeValidation.validate()) {
                    String editedEvent = event.getText().toString();
                    String editedDescription = description.getText().toString();
                    String editedAward = award.getText().toString();
                    String editedYear = year.getText().toString();

                    AchievementModel achievementModel = new AchievementModel(Integer.parseInt(id), editedEvent, editedDescription, editedAward, Integer.parseInt(editedYear));
                    int state = achievementDbHandler.updateSingleAchievement(achievementModel);
                    if (state > 0) {
                        Toasty.success(getApplicationContext(), "Successfully Updated!", Toast.LENGTH_LONG).show();
                        startActivity(new Intent(context, ViewAchievement.class));
                    } else {
                        Toasty.error(getApplicationContext(), "Error!", Toast.LENGTH_LONG).show();
                        startActivity(new Intent(context, ViewAchievement.class));
                    }
                }
            }
        });
    }
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.dashboard:
                startActivity(new Intent(this, MainDashboard.class));
                return true;
            case R.id.education:
                startActivity(new Intent(this, EducationDashboard.class));
                return true;
            case R.id.health:
                startActivity(new Intent(this, Dashboard.class));
                return true;
            case R.id.achievement:
                startActivity(new Intent(this, ViewAchievement.class));
                return true;
            case R.id.timeTable:
                startActivity(new Intent(this, ViewTimetable.class));
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }
}