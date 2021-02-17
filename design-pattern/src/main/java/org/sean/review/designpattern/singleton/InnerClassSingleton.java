package org.sean.review.designpattern.singleton;

import java.io.Serializable;

public class InnerClassSingleton implements Serializable {

    public static final long serialVersionUID = 42L;

    static class InnerClassSingletonHolder {
        private static InnerClassSingleton instance = new InnerClassSingleton();
    }

    private InnerClassSingleton() {}

    public static InnerClassSingleton getInstance() {
        return InnerClassSingletonHolder.instance;
    }

    public Object readResolve() {
        return InnerClassSingletonHolder.instance;
    }

}
