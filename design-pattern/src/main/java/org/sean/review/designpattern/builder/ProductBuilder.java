package org.sean.review.designpattern.builder;

import org.sean.review.designpattern.builder.model.Product;

public interface ProductBuilder {

    ProductBuilder productName(String productName);

    ProductBuilder companyName(String companyName);

    ProductBuilder attribute1(String attribute1);

    ProductBuilder attribute2(String attribute2);

    ProductBuilder attribute3(String attribute3);

    ProductBuilder attribute4(String attribute4);

    Product build();

}
