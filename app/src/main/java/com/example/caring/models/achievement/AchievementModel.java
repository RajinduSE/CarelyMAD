package com.example.caring.models.achievement;

public class AchievementModel {
    private int id;
    private String event;
    private String description;
    private String award;
    private int year;

    public AchievementModel(){

    }

    public AchievementModel(int id, String event, String description, String award, int year) {
        this.id = id;
        this.event = event;
        this.description = description;
        this.award = award;
        this.year = year;
    }

    public AchievementModel(String event, String description, String award, int year) {
        this.event = event;
        this.description = description;
        this.award = award;
        this.year = year;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getEvent() {
        return event;
    }

    public void setEvent(String event) {
        this.event = event;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getAward() {
        return award;
    }

    public void setAward(String award) {
        this.award = award;
    }

    public int getYear() {
        return year;
    }

    public void setYear(int year) {
        this.year = year;
    }
}
