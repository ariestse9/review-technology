package org.sean.review.dubbo;

import java.util.concurrent.CompletableFuture;

public interface DemoService {

    /**
     * 普通服务调用
     *
     * @param msg
     * @return
     */
    String say(String msg);

    /**
     * 用户登陆
     *
     * @return
     */
    default String login(Authentication authentication) { return "Invalid authentication"; };

    /**
     * 回调支持
     *
     * @param msg
     * @param call
     * @return
     */
//    default String say(String msg, Callback call) { return null; };

    /**
     * 支持key的回调
     *
     * @param msg
     * @param key
     * @param call
     * @return
     */
    default String say(String msg, String key, Callback call) { return null; };

    /**
     * 支持异步的方法调用
     *
     * @param msg
     * @return
     */
    default CompletableFuture<String> asyncSay(String msg) { return null; };

}
