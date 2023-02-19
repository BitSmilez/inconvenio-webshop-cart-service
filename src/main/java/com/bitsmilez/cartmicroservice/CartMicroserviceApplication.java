package com.bitsmilez.cartmicroservice;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;


@SpringBootApplication
@EnableJpaRepositories("com.bitsmilez.cartmicroservice.core.domain.service.interfaces")
public class CartMicroserviceApplication {


	public static void main(String[] args) {
		SpringApplication.run(CartMicroserviceApplication.class, args);
	}




}