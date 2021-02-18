package org.sean.review.dubbo;

/**
 * 本地伪装，用于服务降级
 */
public class DemoServiceMock implements DemoService {
    @Override
    public String say(String msg) {
        return "Local mock for DemoService.";
    }

}
