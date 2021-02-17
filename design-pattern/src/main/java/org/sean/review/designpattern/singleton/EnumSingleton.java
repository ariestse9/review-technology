package org.sean.review.designpattern.singleton;

public enum EnumSingleton {
    INSTANCE;

    static EnumSingleton getInstance() {
        return INSTANCE;
    }
}
