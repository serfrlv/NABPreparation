package com.shineSolutions.nabPreparation;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.circuitbreaker.EnableCircuitBreaker;

@SpringBootApplication
@EnableCircuitBreaker
public class NabPreparationApplication {

	public static void main(String[] args) {
		SpringApplication.run(NabPreparationApplication.class, args);
	}
}
