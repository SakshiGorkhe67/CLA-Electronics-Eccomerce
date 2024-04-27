package com.lcwd.category.service;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
//import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.web.client.RestTemplate;

@SpringBootApplication
@EnableDiscoveryClient
@ComponentScan(basePackages = {"com.*"})
public class CategoryServiceApplication {
	  @Bean
			public RestTemplate restTemplate() {
			return new RestTemplate();
		}

	public static void main(String[] args) {
		SpringApplication.run(CategoryServiceApplication.class, args);
	 
	
	}

}
