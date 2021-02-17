package org.sean.review.designpattern.singleton;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.atomic.AtomicReference;

import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class HungerSingletonTest {

    @DisplayName("单线程单例初始化")
    @Test
    void testGetInstanceBySingleThread() {
        HungerSingleton hungerSingleton1 = HungerSingleton.getInstance();
        HungerSingleton hungerSingleton2 = HungerSingleton.getInstance();

        assertTrue(hungerSingleton1 == hungerSingleton2);
    }

    @DisplayName("多线程单例初始化")
    @Test
    void testGetInstanceByMultithreading() throws InterruptedException {
        AtomicReference<HungerSingleton> hungerSingleton1 = new AtomicReference<>();
        AtomicReference<HungerSingleton> hungerSingleton2 = new AtomicReference<>();
        CountDownLatch countDownLatch = new CountDownLatch(2);
        new Thread(() -> {
            hungerSingleton1.set(HungerSingleton.getInstance());
            countDownLatch.countDown();
        }).start();
        new Thread(() -> {
            hungerSingleton2.set(HungerSingleton.getInstance());
            countDownLatch.countDown();
        }).start();
        countDownLatch.await();

        assertTrue(hungerSingleton1.get() == hungerSingleton2.get());
    }

    @DisplayName("单例饿汉模式之反射测试，在构造方法中只能抛出异常，因为进入了构造方法即创建了对象。")
    @Test
    void testGetInstanceByReflect() throws NoSuchMethodException, IllegalAccessException, InvocationTargetException, InstantiationException {
        Constructor constructor = HungerSingleton.class.getDeclaredConstructor();
        constructor.setAccessible(true);
        assertThrows(InvocationTargetException.class, () -> {
            constructor.newInstance();
        });
    }

}
