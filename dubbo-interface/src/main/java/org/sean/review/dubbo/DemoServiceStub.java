package org.sean.review.dubbo;

import java.util.concurrent.CompletableFuture;

public class DemoServiceStub implements DemoService {

    private DemoService demoService;

    public DemoServiceStub(DemoService demoService) {
        this.demoService = demoService;
    }

    @Override
    public String say(String msg) {
        String value;
        try {
            value = demoService.say(msg);
        } catch (Exception e) {
            value = "本地存根测试！";
        }
        return value;
    }

    @Override
    public String say(String msg, String key, Callback call) {
        String value;
        try {
            value = demoService.say(msg, key, call);
        } catch (Exception e) {
            value = "本地存根测试！";
        }
        return value;
    }

}
