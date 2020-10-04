package com.example.caring;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;

import com.example.caring.dbHandlers.DbHandler;
import com.example.caring.models.education.Mark;
import com.github.mikephil.charting.charts.BarChart;
import com.github.mikephil.charting.data.BarData;
import com.github.mikephil.charting.data.BarDataSet;
import com.github.mikephil.charting.data.BarEntry;
import com.github.mikephil.charting.utils.ColorTemplate;

import java.util.ArrayList;
import java.util.List;

public class EducationProgress extends AppCompatActivity {

    private DbHandler progressDbHandler;
    private Context context;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_education_progress);

        BarChart barChart = findViewById(R.id.barChart);
        context = this;
        progressDbHandler = new DbHandler(context);

        int userGrade = Integer.parseInt(getIntent().getStringExtra("GRADE"));

        Mark mark_1 = new Mark();
        Mark mark_2 = new Mark();
        Mark mark_3 = new Mark();

        mark_1 = progressDbHandler.getSingleMark(userGrade,1);
        mark_2 = progressDbHandler.getSingleMark(userGrade,2);
        mark_3 = progressDbHandler.getSingleMark(userGrade,3);

        ArrayList<BarEntry> marks = new ArrayList<>();
        marks.add(new BarEntry(1, (float)mark_1.getAverage()));
        marks.add(new BarEntry(2, (float)mark_2.getAverage()));
        marks.add(new BarEntry(3, (float)mark_3.getAverage()));


        BarDataSet barDataSet = new BarDataSet(marks, "Progress");
        barDataSet.setColors(ColorTemplate.MATERIAL_COLORS);
        barDataSet.setValueTextColor(Color.BLACK);
        barDataSet.setValueTextSize(16f);

        BarData barData = new BarData(barDataSet);

        barChart.setFitBars(true);
        barChart.setData(barData);
        barChart.getDescription().setText("Grade " + userGrade + " Progress");
        barChart.animateY(2000);
    }

    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.option_menu, menu);
        return true;
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