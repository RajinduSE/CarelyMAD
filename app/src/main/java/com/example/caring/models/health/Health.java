package com.example.caring.models.health;
/*
*
*@Author Dilmi
*
 */

public class Health {
    private int healthId;
    private double weight;
    private double height;
    private String doctor;
    private String healthCondition;
    private String allergies;

    public Health(int healthId, double weight, double height, String doctor, String healthCondition, String allergies) {
        this.healthId = healthId;
        this.weight = weight;
        this.height = height;
        this.doctor = doctor;
        this.healthCondition = healthCondition;
        this.allergies = allergies;
    }

    public Health() {
    }

    public Health(double weight, double height, String doctor, String healthCondition, String allergies) {
        this.weight = weight;
        this.height = height;
        this.doctor = doctor;
        this.healthCondition = healthCondition;
        this.allergies = allergies;
    }

    public Health(int healthId,  String doctor, String healthCondition) {
        this.healthId = healthId;
        this.doctor = doctor;
        this.healthCondition = healthCondition;

    }

    public int getHealthId() {
        return healthId;
    }

    public void setHealthId(int  healthId) {
        this.healthId = healthId;
    }

    public double getWeight() {
        return weight;
    }

    public void setWeight(double weight) {
        this.weight = weight;
    }

    public double getHeight() {
        return height;
    }

    public void setHeight(double height) {
        this.height = height;
    }

    public String getDoctor() {
        return doctor;
    }

    public void setDoctor(String doctor) {
        this.doctor = doctor;
    }

    public String getHealthCondition() {
        return healthCondition;
    }

    public void setHealthCondition(String healthCondition) {
        this.healthCondition = healthCondition;
    }

    public String getAllergies() {
        return allergies;
    }

    public void setAllergies(String allergies) {
        this.allergies = allergies;
    }
}
