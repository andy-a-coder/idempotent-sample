package com.andy.sample;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.transaction.annotation.EnableTransactionManagement;

@SpringBootApplication
@MapperScan({
    "com.andy.sample.dao",
})
@EnableTransactionManagement(proxyTargetClass = true)
@EnableConfigurationProperties
public class IdempotentSampleWebApplication {
    public static void main(String[] args) {
        SpringApplication.run(IdempotentSampleWebApplication.class, args);
    }
}

