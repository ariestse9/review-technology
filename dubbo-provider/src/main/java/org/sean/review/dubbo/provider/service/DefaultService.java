package org.sean.review.dubbo.provider.service;

import lombok.extern.slf4j.Slf4j;
import org.apache.dubbo.common.URL;
import org.apache.dubbo.config.annotation.Argument;
import org.apache.dubbo.config.annotation.DubboService;
import org.apache.dubbo.config.annotation.Method;
import org.apache.dubbo.config.annotation.Service;
import org.apache.dubbo.rpc.RpcContext;
import org.sean.review.dubbo.Authentication;
import org.sean.review.dubbo.Callback;
import org.sean.review.dubbo.DemoService;
import org.springframework.util.StringUtils;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.CompletableFuture;

@Slf4j
//@DubboService(version = "default", group = "default")
//@Service(version = "default", group = "default")
@Service(version = "default", group = "default", methods = {@Method(name = "say", arguments = {@Argument(index = 2, callback = true)})}, callbacks = 3)
public class DefaultService implements DemoService {

    private Map<String, Callback> callbackMap = new HashMap<>();

    @Override
    public String login(Authentication authentication) {
        if (authentication != null
                && "sean".equals(authentication.getName())
                && "sean".equals(authentication.getPassword())) {
            return "success";
        }
        return "Invalid authentication";
    }

    @Override
    public String say(String msg) {
        URL url = RpcContext.getContext().getUrl();
        String value = String.format("%s:%s, Hello, %s!", url.getProtocol(), url.getPort(), msg);

        return value;
    }

    @Override
    public String say(String msg, String key, Callback call) {
        Callback callback;
        if (!StringUtils.isEmpty(key) && callbackMap.get(key) != null) {
            log.info("Callback已存在，从缓存中取...");
            callback = callbackMap.get(key);
        } else {
            callback = call;
        }

        URL url = RpcContext.getContext().getUrl();
        String value = String.format("%s:%s, Hello, %s!", url.getProtocol(), url.getPort(), msg);
        log.info("开始回调...");
        call.call(msg);
        return value;
    }

    @Override
    public CompletableFuture<String> asyncSay(String msg) {
        URL url = RpcContext.getContext().getUrl();
        CompletableFuture<String> future = CompletableFuture.supplyAsync(() -> {
            log.info("开始异步调用...");
            String value = String.format("%s:%s, Hello, %s!", url.getProtocol(), url.getPort(), msg);
            log.info("异步调用返回.");
            return value;
        });
        return future;
    }
}
