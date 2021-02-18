package org.sean.review.core.asynchronization;

import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.concurrent.*;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.ReentrantLock;

import static java.lang.Thread.sleep;

@Slf4j
@SpringBootTest
public class DefaultAsynchronizationExecutorTest {

    @Autowired
    private AsynchronizationExecutor<String> defaultAsynchronizationExecutor;

    @Test
    void testAsyncWithThread() throws InterruptedException {
        defaultAsynchronizationExecutor.executeWithThread(() -> {
            log.info("开始异步调用...");
            sleep(2000);
            log.info("异步调用结束.");
            return "success";
        });
        sleep(3000);
        log.info("主线程结束.");
    }

    /**
     * 使用wait与notify方法阻塞主线程
     */
    @Test
    void testAsyncWithThreadByWaitAndNotify() throws InterruptedException {
        final Object lock = new Object();
        defaultAsynchronizationExecutor.executeWithThread(() -> {
            log.info("开始异步调用...");
            sleep(2000);
            log.info("异步调用结束.");
            synchronized (lock) {
                lock.notifyAll();
            }
            return "success";
        });
        synchronized (lock) {
            lock.wait();
        }
        log.info("主线程结束.");
    }

    @Test
    void testAsyncWithThreadByReentrantLock() {
        final ReentrantLock lock = new ReentrantLock();
        final Condition condition = lock.newCondition();

        defaultAsynchronizationExecutor.executeWithThread(() -> {
            log.info("开始异步调用...");
            sleep(2000);
            log.info("异步调用结束.");

            lock.lock();
            try {
                condition.signal();
            } finally {
                lock.unlock();
            }
            return "success";
        });
        lock.lock();
        try {
            condition.await();
        } catch (InterruptedException e) {
            e.printStackTrace();
        } finally {
            lock.unlock();
        }
    }

    @Test
    void testAsyncWithFuture() throws ExecutionException, InterruptedException {
        Future<String> future = defaultAsynchronizationExecutor.executeFuture(() -> {
            log.info("开始异步调用...");
            sleep(2000);
            log.info("异步调用结束.");
            return "success";
        });
        while (!future.isCancelled() && !future.isDone()) {
            log.info("调用结果为:" + future.get());
        }

        log.info("主线程结束.");
    }

    @Test
    void testAsyncWithCompletableFuture() throws InterruptedException, BrokenBarrierException {
        final CyclicBarrier cyclicBarrier = new CyclicBarrier(2);
        CompletableFuture<String> completableFuture = defaultAsynchronizationExecutor.executeCompletableFuture(() -> {
            log.info("开始异步调用...");
            try {
                sleep(2000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            log.info("结束异步调用.");
            return "success";
        });

        completableFuture.thenAccept(e -> {
            log.info(e + " thenAccept.");
        });
        completableFuture.whenComplete((s, t) -> {
            if (t != null) {
                throw new RuntimeException("调用异常!");
            } else {
                log.info("结果为：" + s);
            }
            try {
                cyclicBarrier.await();
            } catch (InterruptedException interruptedException) {
                interruptedException.printStackTrace();
            } catch (BrokenBarrierException brokenBarrierException) {
                brokenBarrierException.printStackTrace();
            }
        });
        cyclicBarrier.await();
        log.info("主线程结束.");
    }

}
