package org.sean.review.designpattern.singleton;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.atomic.AtomicReference;

import static org.junit.jupiter.api.Assertions.*;

public class InnerClassSingletonTest extends BaseSingletonTest<InnerClassSingleton> {

    @DisplayName("单线程内部类单例")
    @Test
    void testGetInstanceBySingleThread() {
        InnerClassSingleton innerClassSingleton1 = InnerClassSingleton.getInstance();
        InnerClassSingleton innerClassSingleton2 = InnerClassSingleton.getInstance();

        assertTrue(innerClassSingleton1 == innerClassSingleton2);
    }

    @DisplayName("多线程内部类单例")
    @Test
    void testGetInstanceByMultithreading() throws InterruptedException {
        AtomicReference<InnerClassSingleton> singletonAtomicReference1 = new AtomicReference<>();
        AtomicReference<InnerClassSingleton> singletonAtomicReference2 = new AtomicReference<>();
        CountDownLatch countDownLatch = new CountDownLatch(2);

        new Thread(() -> {
            singletonAtomicReference1.set(InnerClassSingleton.getInstance());
            countDownLatch.countDown();
        }).start();
        new Thread(() -> {
            singletonAtomicReference2.set(InnerClassSingleton.getInstance());
            countDownLatch.countDown();
        }).start();
        countDownLatch.await();

        assertTrue(singletonAtomicReference1.get() == singletonAtomicReference2.get());
    }

    @DisplayName("内部类模式使用持久化获得单例对象")
    @Test
    void testGetInstanceByPersistence() throws IOException, ClassNotFoundException {
        final String fileName = "InnerClassSingletonPersistence";
        InnerClassSingleton innerClassSingleton = InnerClassSingleton.getInstance();
        writeInstance(fileName, innerClassSingleton);

        InnerClassSingleton innerClassSingleton1 = readInstance(fileName);
        InnerClassSingleton innerClassSingleton2 = InnerClassSingleton.getInstance();

        assertTrue(innerClassSingleton1 == innerClassSingleton2);
        clearPersistence(fileName);
    }

}
