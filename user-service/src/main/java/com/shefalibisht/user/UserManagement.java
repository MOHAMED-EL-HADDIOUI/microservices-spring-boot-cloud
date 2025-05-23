package com.shefalibisht.user;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;

@SpringBootApplication
@EnableEurekaClient
public class UserManagement {

	public static void main(String[] args) {
		SpringApplication.run(UserManagement.class, args);
	}

}
