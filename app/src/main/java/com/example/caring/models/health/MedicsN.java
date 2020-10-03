package com.example.caring.models.health;

public class MedicsN {
    private int medicsId;
    private String medicine;
    private String time;
    private String atATime;
    private String Description;

    public MedicsN(int medicsId, String medicine, String time, String atATime, String description) {
        this.medicsId = medicsId;
        this.medicine = medicine;
        this.time = time;
        this.atATime = atATime;
        Description = description;
    }

    public MedicsN() {
    }

    public MedicsN(String medicine, String time, String atATime, String description) {
        this.medicine = medicine;
        this.time = time;
        this.atATime = atATime;
        Description = description;
    }

    public String getMedicine() {
        return medicine;
    }

    public void setMedicine(String medicine) {
        this.medicine = medicine;
    }

    public int getMedicsId() {
        return medicsId;
    }

    public void setMedicsId(int medicsId) {
        this.medicsId = medicsId;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public String getAtATime() {
        return atATime;
    }

    public void setAtATime(String atATime) {
        this.atATime = atATime;
    }

    public String getDescription() {
        return Description;
    }

    public void setDescription(String description) {
        Description = description;
    }
}
