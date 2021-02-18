package org.sean.review.core.asynchronization;

import java.util.concurrent.Callable;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.Future;
import java.util.function.Supplier;

public interface AsynchronizationExecutor<T> {

    void executeWithThread(Callable<T> callable);

    /**
     * Java1.8之前的异步调用方法
     *
     * @param callable
     * @return
     */
    Future<T> executeFuture(Callable<T> callable);

    /**
     * Java1.8之后支持了回调
     *
     * @param supplier
     * @return
     */
    CompletableFuture<String> executeCompletableFuture(Supplier<T> supplier);

}
