package org.sean.review.designpattern.factory.model;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.sean.review.designpattern.factory.method.ProductClient;
import org.sean.review.designpattern.factory.method.model.Product;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class ProductClientTest {

    @DisplayName("使用工厂方法模式创建产品")
    @Test
    void testCreateProduct() {
        Product product = ProductClient.newProduct();

        assertEquals("产品A", product.getProductName());
    }

}
