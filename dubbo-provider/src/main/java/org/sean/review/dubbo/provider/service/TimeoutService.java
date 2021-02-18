package org.sean.review.dubbo.provider.service;

import lombok.extern.slf4j.Slf4j;
import org.apache.dubbo.common.URL;
import org.apache.dubbo.config.annotation.DubboService;
import org.apache.dubbo.config.annotation.Service;
import org.apache.dubbo.rpc.RpcContext;
import org.sean.review.dubbo.Callback;
import org.sean.review.dubbo.DemoService;

import java.util.concurrent.CompletableFuture;

@Slf4j
//@DubboService(version = "timeout", timeout = 6000)
@Service(version = "timeout", timeout = 6000)
public class TimeoutService implements DemoService {
    @Override
    public String say(String msg) {
        URL url = RpcContext.getContext().getUrl();
        try {
            Thread.sleep(5000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        log.info(String.format("Request TimeoutService with %s.", msg));
        return String.format("%s:%s Hello, %s!", url.getProtocol(), url.getPort(), msg);
    }

}
