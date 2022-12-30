package com.bitsmilez.cartmicroservice;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;


@SpringBootApplication
@EnableJpaRepositories("com.bitsmilez.cartmicroservice.core.domain.service.interfaces")
public class CartMicroserviceApplication {
	@Autowired
	public static void main(String[] args) {
		SpringApplication.run(CartMicroserviceApplication.class, args);
	}



}
