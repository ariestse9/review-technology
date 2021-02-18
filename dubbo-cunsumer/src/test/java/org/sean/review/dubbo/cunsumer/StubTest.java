package org.sean.review.dubbo.cunsumer;

import org.apache.dubbo.config.annotation.Reference;
import org.junit.jupiter.api.Test;
import org.sean.review.dubbo.DemoService;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.assertEquals;

/**
 * 本地存根测试
 */
@SpringBootTest
public class StubTest {

    @Reference(version = "timeout", stub = "true", timeout = 500)
    private DemoService stubDemoService;

    @Test
    void testStub() {
        String value = stubDemoService.say("Sean");

        assertEquals("本地存根测试！", value);
    }

}
