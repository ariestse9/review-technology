package org.sean.review.dubbo.cunsumer;

import org.apache.dubbo.config.annotation.Reference;
import org.junit.jupiter.api.Test;
import org.sean.review.dubbo.Authentication;
import org.sean.review.dubbo.DemoService;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.assertEquals;

@SpringBootTest
public class AuthenticationTest {

    private static final String SUCCESS = "success";

    private static final String INVALID_AUTHENTICATION = "Invalid authentication";

    @Reference(version = "default", group = "default")
    private DemoService demoService;

    @Test
    void testLoginSuccess() {
        Authentication authentication = new Authentication("sean", "sean");

        assertEquals(SUCCESS, demoService.login(authentication));
    }

    @Test
    void testLoginFail() {
        Authentication authentication = new Authentication("sean", "sean1");

        assertEquals(INVALID_AUTHENTICATION, demoService.login(authentication));
    }

}
