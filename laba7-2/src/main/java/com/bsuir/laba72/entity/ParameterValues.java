package com.bsuir.laba72.entity;

public class ParameterValues {
    private int id;
    private int productId;
    private int parameterId;
    private String name;
    private String value;
    private String measure;

    public ParameterValues(int id, int productId, int parameterId,String name, String value, String measure) {
        this.id = id;
        this.productId = productId;
        this.parameterId = parameterId;
        this.value = value;
        this.name = name;
        this.measure = measure;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getProductId() {
        return productId;
    }

    public void setProductId(int productId) {
        this.productId = productId;
    }

    public int getParameterId() {
        return parameterId;
    }

    public void setParameterId(int parameterId) {
        this.parameterId = parameterId;
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
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