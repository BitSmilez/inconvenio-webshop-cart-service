package com.bitsmilez.cartmicroservice;

import com.bitsmilez.cartmicroservice.core.domain.model.Product;
import com.bitsmilez.cartmicroservice.core.domain.model.ProductID;
import com.bitsmilez.cartmicroservice.core.domain.service.interfaces.IProductRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

import java.math.BigDecimal;
import java.util.UUID;


@SpringBootApplication
@EnableJpaRepositories("com.bitsmilez.cartmicroservice.core.domain.service.interfaces")
public class CartMicroserviceApplication {


	public static void main(String[] args) {
		SpringApplication.run(CartMicroserviceApplication.class, args);
	}
	@Bean
	public CommandLineRunner demo(IProductRepository repository) {
		System.out.println("Create Products!");
		return (args) -> {
			String cartID= "1234";
			
			

			Product product= new Product(new ProductID(UUID.nameUUIDFromBytes("9d94ae9f-3b8f-47ed-89ab-8bb808e94521".getBytes()),cartID), "fork", new BigDecimal(10), new BigDecimal(3), "https://ucarecdn.com/ac45e974-a77e-4f1c-86d1-f189204d18df", 10);
			Product product2=(new Product(new ProductID( UUID.nameUUIDFromBytes("1d56d360-5495-4dd2-96c1-a87b444bba56".getBytes()),cartID), "champa", new BigDecimal(12), null, "https://ucarecdn.com/9f95c6c4-ace3-4f59-a0da-e0c15591e002/", 5));
			Product product3=( new Product(new ProductID( UUID.nameUUIDFromBytes("3372a9ba-ca98-4718-a7c2-fa7272a011fd".getBytes()),cartID), "mug", new BigDecimal("17.5"), null, "https://ucarecdn.com/8f73e3b8-bc9c-46d1-a0f7-1bad02629692/", 5));


			repository.save(product);
			repository.save(product2);
			repository.save(product3);



		};
	}


}