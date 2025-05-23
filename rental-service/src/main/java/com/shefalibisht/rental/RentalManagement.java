package com.shefalibisht.rental;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.loadbalancer.LoadBalanced;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.context.annotation.Bean;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.web.reactive.function.client.WebClient;

@EnableEurekaClient
@SpringBootApplication
public class RentalManagement {

	@Bean
	@LoadBalanced
	public WebClient.Builder getWebClient() {

		return WebClient.builder()
				//.defaultHeaders(header -> header.add("X-XSRF-TOKEN",hea));
				.defaultHeader(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON_VALUE);
	}

	public static void main(String[] args) {
		SpringApplication.run(RentalManagement.class, args);
	}

}
