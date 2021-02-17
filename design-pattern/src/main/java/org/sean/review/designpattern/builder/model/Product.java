package org.sean.review.designpattern.builder.model;

import org.apache.commons.lang3.StringUtils;

public class Product {

    private String productName;
    private String companyName;
    private String attribute1;
    private String attribute2;
    private String attribute3;
    private String attribute4;

    public Product(String productName, String companyName, String attribute1, String attribute2, String attribute3, String attribute4) {
        this.productName = productName;
        this.companyName = companyName;
        this.attribute1 = attribute1;
        this.attribute2 = attribute2;
        this.attribute3 = attribute3;
        this.attribute4 = attribute4;
    }

    public String getProductName() {
        return productName;
    }

    public void setProductName(String productName) {
        this.productName = productName;
    }

    public String getCompanyName() {
        return companyName;
    }

    public void setCompanyName(String companyName) {
        this.companyName = companyName;
    }

    public String getAttribute1() {
        return attribute1;
    }

    public void setAttribute1(String attribute1) {
        this.attribute1 = attribute1;
    }

    public String getAttribute2() {
        return attribute2;
    }

    public void setAttribute2(String attribute2) {
        this.attribute2 = attribute2;
    }

    public String getAttribute3() {
        return attribute3;
    }

    public void setAttribute3(String attribute3) {
        this.attribute3 = attribute3;
    }

    public String getAttribute4() {
        return attribute4;
    }

    public void setAttribute4(String attribute4) {
        this.attribute4 = attribute4;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder("Product{")
                .append("productName='")
                .append(productName)
                .append('\'')
                .append(", companyName='")
                .append(companyName)
                .append('\'');
        if (StringUtils.isNoneEmpty(attribute1)) {
            sb.append(", attribute1='")
                    .append(attribute1)
                    .append('\'');
        }
        if (StringUtils.isNoneEmpty(attribute2)) {
            sb.append(", attribute2='")
                    .append(attribute2)
                    .append('\'');
        }
        if (StringUtils.isNoneEmpty(attribute3)) {
            sb.append(", attribute3='")
                    .append(attribute3)
                    .append('\'');
        }
        if (StringUtils.isNoneEmpty(attribute4)) {
            sb.append(", attribute4='")
                    .append(attribute4)
                    .append('\'');
        }
        sb.append('}');

        return sb.toString();
    }
}
