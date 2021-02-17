package org.sean.review.designpattern.factory.method;

import org.sean.review.designpattern.factory.method.model.Product;
import org.sean.review.designpattern.factory.method.model.ProductB;

public class ProductBFactory implements ProductFactory {
    @Override
    public Product createProduct() {
        Product product = new ProductB();
        //其他创建逻辑
        return product;
    }
}
