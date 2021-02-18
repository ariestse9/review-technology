package org.sean.review.dubbo.provider.service;

import lombok.extern.slf4j.Slf4j;
import org.apache.dubbo.common.URL;
import org.apache.dubbo.config.annotation.Service;
import org.apache.dubbo.rpc.RpcContext;
import org.apache.dubbo.rpc.service.GenericException;
import org.apache.dubbo.rpc.service.GenericService;

@Slf4j
@Service(version = "generic", interfaceName = "org.sean.review.dubbo.DemoService")
public class DefaultGenericService implements GenericService {
    @Override
    public Object $invoke(String method, String[] parameterTypes, Object[] args) throws GenericException {
        log.info("泛化调用" + method + "(" + parameterTypes + ", " + args + ")");
        if ("say".equals(method) && parameterTypes.length == 1 && args.length == 1 && args[0] instanceof String) {
            return say((String) args[0]);
        }
        throw new RuntimeException("方法" + method + "不支持！");
    }

    private String say(String msg) {
        URL url = RpcContext.getContext().getUrl();
        String value = String.format("%s:%s, Hello, %s!", url.getProtocol(), url.getPort(), msg);

        return value;
    }
}
