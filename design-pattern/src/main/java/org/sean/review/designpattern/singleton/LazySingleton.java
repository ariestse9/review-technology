package org.sean.review.designpattern.singleton;

import java.io.Serializable;

public class LazySingleton implements Serializable {

    public static final long serialVersionUID = 42L;

    private static volatile LazySingleton instance;

    private LazySingleton() { }

    public static LazySingleton getInstance() {
        if (instance == null) {
            synchronized (LazySingleton.class) {
                if (instance == null) {
                    instance = new LazySingleton();
                }
            }
        }
        return instance;
    }

    public Object readResolve() {
        return instance;
    }

}
