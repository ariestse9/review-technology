package org.sean.review.dubbo.cunsumer;

import org.apache.dubbo.config.annotation.Reference;
import org.junit.jupiter.api.Test;
import org.sean.review.dubbo.Callback;
import org.sean.review.dubbo.DemoService;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
public class CallbackTest {

    @Reference(version = "default", group = "default")
    private DemoService demoService;

    @Test
    void testCallbackWithoutKey() {
        String msg = "Sean";
        String value = demoService.say(msg, "key", m -> {
            System.out.println(String.format("回调中...%s", m));
        });
        System.out.println(value);
    }

}
