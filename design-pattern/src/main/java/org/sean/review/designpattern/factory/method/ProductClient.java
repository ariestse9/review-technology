package org.sean.review.designpattern.factory.method;

import org.sean.review.designpattern.factory.method.model.Product;

import java.util.ResourceBundle;

public class ProductClient {

    private static final String key = "org.sean.review.designpattern.factory.method";

    public static Product newProduct() {
        ProductFactory factory;
        String type;

        ResourceBundle resourceBundle = ResourceBundle.getBundle("config");
        type = resourceBundle.getString(key);

        if ("a".equals(type)) {
            factory = new ProductAFactory();
        } else if ("b".equals(type)) {
            factory = new ProductBFactory();
        } else {
            throw new RuntimeException("不支持的产品！");
        }
        Product product = factory.createProduct();
        return product;
    }

}
