package org.sean.review.designpattern.singleton;

import java.io.Serializable;

public class HungerSingleton implements Serializable {

    public static final long serialVersionUID = 42L;

    private static HungerSingleton instance = new HungerSingleton();

    private HungerSingleton() {
        System.out.println(instance);
        if (instance != null) {
            throw new RuntimeException("该对象已实例化！");
        }
    }

    public static HungerSingleton getInstance() {
        return instance;
    }

    public Object readResolve() {
        return instance;
    }

}
