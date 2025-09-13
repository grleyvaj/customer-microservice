package com.devsu.customer_service;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableAsync;

@SpringBootApplication(
  scanBasePackages = {
	"com.devsu.customer_service.application.beans",
	"com.devsu.customer_service.application.configuration",
	"com.devsu.customer_service.application.handlers",
	"com.devsu.customer_service.application.controller",
	"com.devsu.customer_service.infrastructure.persistence"
  }
)
@EnableAsync
public class CustomerServiceApplication {

	public static void main(String[] args) {
		SpringApplication.run(CustomerServiceApplication.class, args);
	}

}
