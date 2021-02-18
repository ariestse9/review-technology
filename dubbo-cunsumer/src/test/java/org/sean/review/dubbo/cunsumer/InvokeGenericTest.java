package org.sean.review.dubbo.cunsumer;

import org.apache.dubbo.config.annotation.Reference;
import org.apache.dubbo.rpc.service.GenericService;
import org.junit.jupiter.api.Test;
import org.sean.review.dubbo.DemoService;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

@SpringBootTest
public class InvokeGenericTest {

    @Reference(version = "generic")
    private DemoService defaultDemoService;

    @Reference(version = "generic", interfaceName = "org.sean.review.dubbo.DemoService", generic = true)
    private GenericService genericService;

    @Test
    void testGenericService() {
        String value = defaultDemoService.say("Sean");

        assertEquals("dubbo:20881, Hello, Sean!", value);

    }

    @Test
    void testGenericInvoke() {
        Object value = genericService.$invoke("say", new String[]{"java.lang.String"}, new Object[]{"Sean"});

        assertNotNull(value);
        assertEquals("dubbo:20881, Hello, Sean!", value);
    }

}
