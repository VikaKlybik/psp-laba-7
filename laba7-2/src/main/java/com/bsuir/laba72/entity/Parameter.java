package com.bsuir.laba72.entity;

public class Parameter {
    private int id;
    private String name;
    private String measure;

    public Parameter(int id, String name, String measure) {
        this.id = id;
        this.name = name;
        this.measure = measure;
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

    public String getMeasure() {
        return measure;
    }

    public void setMeasure(String measure) {
        this.measure = measure;
    }
}