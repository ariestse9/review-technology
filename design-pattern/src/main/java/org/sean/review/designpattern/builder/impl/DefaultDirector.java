package org.sean.review.designpattern.builder.impl;

import org.sean.review.designpattern.builder.Director;
import org.sean.review.designpattern.builder.ProductBuilder;
import org.sean.review.designpattern.builder.model.Product;

public class DefaultDirector implements Director {

    private ProductBuilder builder;

    public DefaultDirector(ProductBuilder builder) {
        this.builder = builder;
    }

    @Override
    public Product construct(String productName, String companyName, String attribute1, String attribute2, String attribute3, String attribute4) {
        Product product = builder.productName(productName)
                .companyName(companyName)
                .attribute1(attribute1)
                .attribute2(attribute2)
                .attribute3(attribute3)
                .attribute4(attribute4)
                .build();
        return product;
    }
}
