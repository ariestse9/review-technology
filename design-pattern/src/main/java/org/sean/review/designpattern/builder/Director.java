package org.sean.review.designpattern.builder;

import org.sean.review.designpattern.builder.model.Product;

public interface Director {

    Product construct(String productName, String companyName, String attribute1, String attribute2, String attribute3, String attribute4);

}
