package com.test;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

/**
 * Created by araksgyulumyan
 * Date - 7/2/18
 * Time - 8:14 PM
 */

@EnableAutoConfiguration
@SpringBootApplication
@EnableJpaRepositories(basePackages = "com.test.core.repository.*")
@ComponentScan("com.test.*")
public class TestApplication {

    public static void main(String[] args) {
        SpringApplication.run(TestApplication.class, args);
    }
}
