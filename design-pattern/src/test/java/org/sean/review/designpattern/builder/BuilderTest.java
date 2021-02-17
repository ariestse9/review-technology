package org.sean.review.designpattern.builder;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.sean.review.designpattern.builder.impl.DefaultDirector;
import org.sean.review.designpattern.builder.impl.DefaultProductBuilder;
import org.sean.review.designpattern.builder.model.Product;

import static org.junit.jupiter.api.Assertions.*;

/**
 * 建造者模式单元测试
 */
public class BuilderTest {

    @DisplayName("通过建造者构建Product")
    @Test
    void testBuildProduct() {
        ProductBuilder productBuilder = new DefaultProductBuilder();
        Director director = new DefaultDirector(productBuilder);
        Product product = director.construct("iPhone", "apple", "a1", "a2", "a3", "a4");

        assertNotNull(product);
        assertEquals("Product{productName='iPhone', companyName='apple', attribute1='a1', attribute2='a2', attribute3='a3', attribute4='a4'}", product.toString());
    }

    @DisplayName("简单使用Builder创建Product")
    @Test
    void testSimpleBuildProduct() {
        ProductBuilder productBuilder = new DefaultProductBuilder();
        Product product = productBuilder.productName("iPhone 12")
                .companyName("apple").build();

        assertNotNull(product);
        assertEquals("Product{productName='iPhone 12', companyName='apple'}", product.toString());
    }

}
