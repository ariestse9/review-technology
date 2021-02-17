package org.sean.review.dubbo.cunsumer;

import org.apache.dubbo.config.annotation.Reference;
import org.sean.review.dubbo.DemoService;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;

@SpringBootApplication
public class DefaultDubboCunsumer {

    public static void main(String[] args) {
        SpringApplication.run(DefaultDubboCunsumer.class);
    }

}
