package org.sean.review.designpattern.singleton;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.io.*;
import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.atomic.AtomicReference;

import static org.junit.jupiter.api.Assertions.*;

public class LazySingletonTest extends BaseSingletonTest<LazySingleton> {

    @DisplayName("单线程单例初始化")
    @Test
    void testGetInstanceBySingleThread() {
        LazySingleton lazySingleton1 = LazySingleton.getInstance();
        LazySingleton lazySingleton2 = LazySingleton.getInstance();

        assertTrue(lazySingleton1 == lazySingleton2);
    }

    @DisplayName("多线程单例初始化")
    @Test
    void testGetInstanceByMultithreading() throws InterruptedException {
        AtomicReference<LazySingleton> lazySingleton1 = new AtomicReference<>();
        AtomicReference<LazySingleton> lazySingleton2 = new AtomicReference<>();
        CountDownLatch countDownLatch = new CountDownLatch(2);
        new Thread(() -> {
            lazySingleton1.set(LazySingleton.getInstance());
            countDownLatch.countDown();
        }).start();
        new Thread(() -> {
            lazySingleton2.set(LazySingleton.getInstance());
            countDownLatch.countDown();
        }).start();

        countDownLatch.await();

        assertTrue(lazySingleton1.get() == lazySingleton2.get());
    }

    @DisplayName("单例懒汉模式之反射测试，该模式无法预防反射攻击")
    @Test
    void testGetInstanceByReflect() throws NoSuchMethodException, IllegalAccessException, InvocationTargetException, InstantiationException {
        Constructor constructor = LazySingleton.class.getDeclaredConstructor();
        constructor.setAccessible(true);
        LazySingleton lazySingleton1 = (LazySingleton) constructor.newInstance();
        LazySingleton lazySingleton2 = LazySingleton.getInstance();

        assertTrue(lazySingleton1 != lazySingleton2);
    }

    @DisplayName("懒汉模式使用持久化获得单例对象")
    @Test
    void testGetInstanceByPersistence() throws IOException, ClassNotFoundException {
        final String fileName = "LazySingletonPersistence";
        LazySingleton lazySingleton = LazySingleton.getInstance();
        writeInstance(fileName, lazySingleton);

        LazySingleton lazySingleton1 = readInstance(fileName);
        LazySingleton lazySingleton2 = LazySingleton.getInstance();

        assertTrue(lazySingleton1 == lazySingleton2);
        clearPersistence(fileName);
    }

}
