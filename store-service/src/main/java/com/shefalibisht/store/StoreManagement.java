package com.shefalibisht.store;

import org.springframework.boot.SpringApplication;
        import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;

@SpringBootApplication
@EnableEurekaClient
public class StoreManagement {

    public static void main(String[] args) {
        SpringApplication.run(StoreManagement.class, args);
    }

}
