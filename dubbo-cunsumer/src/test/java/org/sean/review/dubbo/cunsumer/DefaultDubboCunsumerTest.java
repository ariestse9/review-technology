package org.sean.review.dubbo.cunsumer;

import lombok.extern.slf4j.Slf4j;
import org.apache.dubbo.config.annotation.Reference;
import org.junit.jupiter.api.Test;
import org.sean.review.dubbo.DemoService;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
@Slf4j
public class DefaultDubboCunsumerTest {

    @Reference(loadbalance = "roundrobin", version = "default", group = "default")
    private DemoService demoServiceRoundRobin;

    @Reference(loadbalance = "consistenthash", version = "default", group = "default")
    private DemoService demoServiceConsistentHash;

    @Reference(loadbalance = "leastactive", version = "default", group = "default")
    private DemoService demoServiceLeastActive;

    @Test
    void testRoundRobin() {
        for (int i = 0; i < 10; i++) {
            String value = demoServiceRoundRobin.say(i + ":Sean");
            System.out.println(value);
        }
    }

    @Test
    void testConsistentHash() {
        for (int i = 0; i < 100; i++) {
            String value = demoServiceConsistentHash.say(i%5 + ":Sean");
            System.out.println(value);
        }
    }

    @Test
    void testLeastActive() {
        for (int i = 0; i < 100; i++) {
            String value = demoServiceLeastActive.say(i + ":Sean");
            System.out.println(value);
        }
    }

}
