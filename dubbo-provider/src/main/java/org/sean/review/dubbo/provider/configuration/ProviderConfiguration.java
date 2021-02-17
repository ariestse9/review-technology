package org.sean.review.dubbo.provider.configuration;

import org.apache.dubbo.config.spring.context.annotation.EnableDubbo;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;

@Configuration
@EnableDubbo(scanBasePackages = "org.sean.review.dubbo.provider.service")
@PropertySource("/provider.properties")
public class ProviderConfiguration {
}
