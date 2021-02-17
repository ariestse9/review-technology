package org.sean.review.designpattern.builder.impl;

import org.sean.review.designpattern.builder.ProductBuilder;
import org.sean.review.designpattern.builder.model.Product;

public class DefaultProductBuilder implements ProductBuilder {

    private String productName;
    private String companyName;
    private String attribute1;
    private String attribute2;
    private String attribute3;
    private String attribute4;

    public ProductBuilder productName(String productName) {
        this.productName = productName;
        return this;
    }

    public ProductBuilder companyName(String companyName) {
        this.companyName = companyName;
        return this;
    }

    public ProductBuilder attribute1(String attribute1) {
        this.attribute1 = attribute1;
        return this;
    }

    public ProductBuilder attribute2(String attribute2) {
        this.attribute2 = attribute2;
        return this;
    }

    public ProductBuilder attribute3(String attribute3) {
        this.attribute3 = attribute3;
        return this;
    }

    public ProductBuilder attribute4(String attribute4) {
        this.attribute4 = attribute4;
        return this;
    }

    @Override
    public Product build() {
        return new Product(this.productName, this.companyName, this.attribute1, this.attribute2, this.attribute3, this.attribute4);
    }
}
