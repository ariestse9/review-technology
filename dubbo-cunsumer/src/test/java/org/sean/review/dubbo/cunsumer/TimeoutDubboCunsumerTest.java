package org.sean.review.dubbo.cunsumer;

import lombok.extern.slf4j.Slf4j;
import org.apache.dubbo.config.annotation.Reference;
import org.apache.dubbo.rpc.RpcException;
import org.junit.jupiter.api.Test;
import org.sean.review.dubbo.DemoService;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
@Slf4j
public class TimeoutDubboCunsumerTest {

    @Reference(version = "timeout", timeout = 1000)
    private DemoService demoServiceTimeout;

    @Test
    void testTimeout() {
//        demoServiceTimeout.say("Sean");
        assertThrows(RpcException.class, () -> {
            demoServiceTimeout.say("Sean");
        });
    }

}
