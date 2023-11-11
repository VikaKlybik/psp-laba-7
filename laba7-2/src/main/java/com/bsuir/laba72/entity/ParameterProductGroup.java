package com.bsuir.laba72.entity;

public class ParameterProductGroup {
    private int parameterId;
    private int productGroupId;

    public ParameterProductGroup(int parameterId, int productGroupId) {
        this.parameterId = parameterId;
        this.productGroupId = productGroupId;
    }

    public int getParameterId() {
        return parameterId;
    }

    public void setParameterId(int parameterId) {
        this.parameterId = parameterId;
    }

    public int getProductGroupId() {
        return productGroupId;
    }

    public void setProductGroupId(int productGroupId) {
        this.productGroupId = productGroupId;
    }
}