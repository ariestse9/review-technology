package org.sean.review.dubbo.cunsumer;

import org.apache.dubbo.config.annotation.Reference;
import org.junit.jupiter.api.Test;
import org.sean.review.dubbo.DemoService;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.assertEquals;

/**
 * 服务降级以及本地伪装
 */
@SpringBootTest
public class ServiceDowngradeAndLocalMockTest {

    @Reference(version = "timeout", mock = "force:return Service Downgrade", timeout = 500)
    private DemoService serviceDowngradeDemoService;

    @Reference(version = "timeout", mock = "fail:return fial to return Service Downgrade", timeout = 500)
    private DemoService failServiceDowngradeDemoService;

    @Reference(version = "timeout", mock = "org.sean.review.dubbo.DemoServiceMock", timeout = 500)
    private DemoService localMockDemoService;

    /**
     * 还可在伪装中抛出异常：mock = "throw com.foo.MockException"
     */
    @Reference(version = "timeout", mock = "true", timeout = 500)
    private DemoService defaultLocalMockDemoService;

    @Test
    void testServiceDowngrade() {
        String value = serviceDowngradeDemoService.say("Sean");

        assertEquals("Service Downgrade", value);
    }

    @Test
    void testFailServiceDowngrade() {
        String value = failServiceDowngradeDemoService.say("Sean");

        assertEquals("fial to return Service Downgrade", value);
    }

    @Test
    void testLocalMock() {
        String value = localMockDemoService.say("Sean");

        assertEquals("Local mock for DemoService.", value);
    }

    @Test
    void testDefaultLocalMock() {
        String value = defaultLocalMockDemoService.say("Sean");

        System.out.println(value);
        assertEquals("Local mock for DemoService.", value);
    }

}
