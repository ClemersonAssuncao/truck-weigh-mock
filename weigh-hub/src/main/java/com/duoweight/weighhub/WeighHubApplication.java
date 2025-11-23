package com.duoweight.weighhub;

import com.duoweight.weighhub.infrastructure.config.TestContainersConfig;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Import;

@SpringBootApplication(scanBasePackages = "com.duoweight.weighhub")
@Import(TestContainersConfig.class)
public class WeighHubApplication {
    
    public static void main(String[] args) {
        SpringApplication.run(WeighHubApplication.class, args);
    }
}
