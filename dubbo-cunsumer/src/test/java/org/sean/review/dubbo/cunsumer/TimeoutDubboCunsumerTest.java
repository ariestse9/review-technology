package org.sean.review.dubbo.cunsumer;

import lombok.extern.slf4j.Slf4j;
import org.apache.dubbo.config.annotation.Reference;
import org.junit.jupiter.api.Test;
import org.sean.review.dubbo.DemoService;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
@Slf4j
public class TimeoutDubboCunsumerTest {

    @Reference(version = "timeout", group = "default")
    private DemoService demoServiceTimeout;

    @Test
    void testTimeout() {

    }

}
