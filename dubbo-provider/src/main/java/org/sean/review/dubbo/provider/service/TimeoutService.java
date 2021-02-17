package org.sean.review.dubbo.provider.service;

import lombok.extern.slf4j.Slf4j;
import org.apache.dubbo.common.URL;
import org.apache.dubbo.config.annotation.Service;
import org.apache.dubbo.rpc.RpcContext;
import org.sean.review.dubbo.DemoService;

@Slf4j
@Service(version = "timeout", group = "default")
public class TimeoutService implements DemoService {
    @Override
    public String say(String msg) {
        URL url = RpcContext.getContext().getUrl();
        try {
            Thread.sleep(5000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        return String.format("%s:%s Hello, %s!", url.getProtocol(), url.getPort(), msg);
    }
}
