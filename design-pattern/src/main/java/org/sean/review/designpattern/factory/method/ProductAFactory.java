package org.sean.review.designpattern.factory.method;

import org.sean.review.designpattern.factory.method.model.Product;
import org.sean.review.designpattern.factory.method.model.ProductA;

public class ProductAFactory implements ProductFactory {

    @Override
    public Product createProduct() {
        Product product = new ProductA();
        //创建产品其他逻辑
        return product;
    }

}
