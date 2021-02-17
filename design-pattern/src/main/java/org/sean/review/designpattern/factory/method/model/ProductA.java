package org.sean.review.designpattern.factory.method.model;

public class ProductA implements Product {

    private String productName;

    public ProductA() {
        this.productName = "产品A";
    }

    public ProductA(String productName) {
        this.productName = productName;
    }

    @Override
    public String getProductName() {
        return this.productName;
    }

}
