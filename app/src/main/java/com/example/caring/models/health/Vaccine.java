package com.example.caring.models.health;

public class Vaccine {
    private int vaccineId;
    private String vaccineName;
    private String age;

    public Vaccine(int vaccineId, String vaccineName, String age) {
        this.vaccineId = vaccineId;
        this.vaccineName = vaccineName;
        this.age = age;
    }

    public Vaccine(String vaccineName, String age) {
        this.vaccineName = vaccineName;
        this.age = age;
    }

    public Vaccine() {
    }

    public int getVaccineId() {
        return vaccineId;
    }

    public void setVaccineId(int vaccineId) {
        this.vaccineId = vaccineId;
    }

    public String getVaccineName() {
        return vaccineName;
    }

    public void setVaccineName(String vaccineName) {
        this.vaccineName = vaccineName;
    }

    public String getAge() {
        return age;
    }

    public void setAge(String age) {
        this.age = age;
    }
}
