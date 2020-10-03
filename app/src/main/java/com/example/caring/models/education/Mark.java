package com.example.caring.models.education;

public class Mark {
    private int id;
    private int grade;
    private int term;
    private double total;
    private int subCount;
    private double average;

    public Mark(){

    }

    public Mark(int id, int grade, int term, double total, int subCount, double average) {
        this.id = id;
        this.grade = grade;
        this.term = term;
        this.total = total;
        this.subCount = subCount;
        this.average = average;
    }

    public Mark(int grade, int term, double total, int subCount, double average) {
        this.grade = grade;
        this.term = term;
        this.total = total;
        this.subCount = subCount;
        this.average = average;
    }

    public Mark(int grade, int term, double total, int subCount) {
        this.grade = grade;
        this.term = term;
        this.total = total;
        this.subCount = subCount;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getGrade() {
        return grade;
    }

    public void setGrade(int grade) {
        this.grade = grade;
    }

    public int getTerm() {
        return term;
    }

    public void setTerm(int term) {
        this.term = term;
    }

    public double getTotal() {
        return total;
    }

    public void setTotal(double total) {
        this.total = total;
    }

    public int getSubCount() {
        return subCount;
    }

    public void setSubCount(int subCount) {
        this.subCount = subCount;
    }

    public double getAverage() {
        return average;
    }

    public void setAverage(double average) {
        this.average = average;
    }

    public double calculateAverage(double total, int subCount){
        average = total / subCount;
        return average;
    }
}
