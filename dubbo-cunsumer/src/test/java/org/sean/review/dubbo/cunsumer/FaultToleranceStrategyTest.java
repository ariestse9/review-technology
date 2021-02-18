package org.sean.review.dubbo.cunsumer;

import org.apache.dubbo.config.annotation.Reference;
import org.junit.jupiter.api.Test;
import org.sean.review.dubbo.DemoService;
import org.springframework.boot.test.context.SpringBootTest;

/**
 * 集群容错测试
 * Failover Cluster:失败重试
 * Failfast Cluster:只发起一次请求
 * Failsafe Cluster:出现异常直接忽略
 * Failback Cluster:后台记录失败请求，定时重发
 * Forking Cluster:并行调用多个服务器，只要有一个成功则成功
 * Broadcast Cluster:广播所有，逐个调用，有一个失败则报错
 */
@SpringBootTest
public class FaultToleranceStrategyTest {

    @Reference(version = "timeout", retries = 2, timeout = 500)
    private DemoService failOverDemoService;

    @Reference(version = "timeout", cluster = "failfast", timeout = 500)
    private DemoService failFastDemoService;

    @Reference(version = "timeout", cluster = "failsafe", timeout = 500)
    private DemoService failSafeDemoService;

    @Reference(version = "timeout", cluster = "failback", timeout = 500)
    private DemoService failBackDemoService;

    @Reference(version = "timeout", cluster = "forking", timeout = 500)
    private DemoService forkingDemoService;

    @Reference(version = "timeout", cluster = "broadcast", timeout = 500)
    private DemoService broadcastDemoService;

    @Test
    void testFailOVer() {
        failOverDemoService.say("Sean");
    }

    @Test
    void testFailFast() {
        failFastDemoService.say("Sean");
    }

    @Test
    void testFailSafe() {
        failSafeDemoService.say("Sean");
    }

    @Test
    void testFailBack() {
        failBackDemoService.say("Sean");
    }

    @Test
    void testForking() {
        forkingDemoService.say("Sean");
    }

    @Test
    void testBbroadcas() {
        broadcastDemoService.say("Sean");
    }

}
