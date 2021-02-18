package org.sean.review.core.asynchronization.impl;

import lombok.extern.slf4j.Slf4j;
import org.sean.review.core.asynchronization.AsynchronizationExecutor;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import java.util.concurrent.*;
import java.util.function.Supplier;

@Slf4j
@Component
public class DefaultAsynchronizationExecutor implements AsynchronizationExecutor<String> {

    private ExecutorService executor = Executors.newFixedThreadPool(10);

    @Override
    public void executeWithThread(Callable<String> callable) {
        new Thread(() -> {
            log.info("服务端开始新线程...");
            try {
                callable.call();
            } catch (Exception e) {
                log.info("回调失败：" + e.getStackTrace());
            }
            log.info("服务端线程结束.");
        }).start();
    }

    @Override
    public Future<String> executeFuture(Callable<String> callable) {
        return executor.submit(callable);
    }

    @Override
    public CompletableFuture<String> executeCompletableFuture(Supplier<String> supplier) {
        CompletableFuture<String> future = CompletableFuture.supplyAsync(supplier);
        return future;
    }



}
