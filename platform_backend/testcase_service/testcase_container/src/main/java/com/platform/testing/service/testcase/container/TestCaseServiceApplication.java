package com.platform.testing.service.testcase.container;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.persistence.autoconfigure.EntityScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@SpringBootApplication(scanBasePackages = "com.platform.testing.service.testcase")
@EnableJpaRepositories(basePackages = "com.platform.testing.service.testcase")
@EntityScan(basePackages = {"com.platform.testing.service.testcase"})
public class TestCaseServiceApplication {
    public  static void main(String[] args) {
        SpringApplication.run(TestCaseServiceApplication.class, args);
    }
}
