package org.sean.review.designpattern.factory.method.model;

public class ProductB implements Product {

    private String productName;

    public ProductB() {
        this.productName = "产品B";
    }

    public ProductB(String productName) {
        this.productName = productName;
    }

    @Override
    public String getProductName() {
        return this.productName;
    }
}
