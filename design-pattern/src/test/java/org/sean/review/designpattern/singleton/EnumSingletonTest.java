package org.sean.review.designpattern.singleton;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.atomic.AtomicReference;

import static org.junit.jupiter.api.Assertions.assertTrue;

public class EnumSingletonTest extends BaseSingletonTest<EnumSingleton> {

    @DisplayName("单线程枚举单例")
    @Test
    void testGetInstanceBySingleThread() {
        EnumSingleton enumSingleton1 = EnumSingleton.getInstance();
        EnumSingleton enumSingleton2 = EnumSingleton.getInstance();

        assertTrue(enumSingleton1 == enumSingleton2);
    }

    @DisplayName("多线程枚举单例")
    @Test
    void testGetInstanceByMultithreading() throws InterruptedException {
        AtomicReference<EnumSingleton> singletonAtomicReference1 = new AtomicReference<>();
        AtomicReference<EnumSingleton> singletonAtomicReference2 = new AtomicReference<>();
        CountDownLatch countDownLatch = new CountDownLatch(2);

        new Thread(() -> {
            singletonAtomicReference1.set(EnumSingleton.getInstance());
            countDownLatch.countDown();
        }).start();
        new Thread(() -> {
            singletonAtomicReference2.set(EnumSingleton.getInstance());
            countDownLatch.countDown();
        }).start();
        countDownLatch.await();

        assertTrue(singletonAtomicReference1.get() == singletonAtomicReference2.get());
    }

    @DisplayName("枚举模式使用持久化获得单例对象")
    @Test
    void testGetInstanceByPersistence() throws IOException, ClassNotFoundException {
        final String fileName = "EnumSingletonPersistence";
        EnumSingleton enumSingleton = EnumSingleton.getInstance();
        writeInstance(fileName, enumSingleton);

        EnumSingleton enumSingleton1 = readInstance(fileName);
        EnumSingleton enumSingleton2 = EnumSingleton.getInstance();

        assertTrue(enumSingleton1 == enumSingleton2);
        clearPersistence(fileName);
    }

}
