package com.example.caring.models.education;

public class School {
    private int id;
    private String name;
    private int phone;
    private int from;
    private int to;

    public School(){

    }

    public School(int id, String name, int phone, int from, int to) {
        this.id = id;
        this.name = name;
        this.phone = phone;
        this.from = from;
        this.to = to;
    }

    public School(String name, int phone, int from, int to) {
        this.name = name;
        this.phone = phone;
        this.from = from;
        this.to = to;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getPhone() {
        return phone;
    }

    public void setPhone(int phone) {
        this.phone = phone;
    }

    public int getFrom() {
        return from;
    }

    public void setFrom(int from) {
        this.from = from;
    }

    public int getTo() {
        return to;
    }

    public void setTo(int to) {
        this.to = to;
    }
}
