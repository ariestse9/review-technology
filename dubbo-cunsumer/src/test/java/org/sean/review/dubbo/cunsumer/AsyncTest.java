package org.sean.review.dubbo.cunsumer;

import org.apache.dubbo.config.annotation.Reference;
import org.junit.jupiter.api.Test;
import org.sean.review.dubbo.DemoService;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.concurrent.CompletableFuture;
import java.util.concurrent.CountDownLatch;

@SpringBootTest
public class AsyncTest {

    @Reference(version = "default", group = "default")
    private DemoService demoService;

    @Test
    void testAsync() throws InterruptedException {
        CompletableFuture<String> completableFuture = demoService.asyncSay("Sean");
        CountDownLatch countDownLatch = new CountDownLatch(1);
        completableFuture.whenComplete((v, t) -> {
            if (t != null) {
                t.printStackTrace();
            } else {
                System.out.println(v);
            }
            countDownLatch.countDown();
        });
        System.out.println("主线程结束...");
        countDownLatch.await();
    }

}
