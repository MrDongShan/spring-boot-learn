package com.mrdongshan.elasticsearch;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;

@SpringBootApplication
@EnableConfigurationProperties
public class DemoElasticsearchJavaApplication {

    public static void main(String[] args) {
        SpringApplication.run(DemoElasticsearchJavaApplication.class, args);
    }

}
