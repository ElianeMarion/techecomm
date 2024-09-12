package br.com.fiap.shoppingcart;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;

@EnableFeignClients
@SpringBootApplication
public class ShoppingcartApplication {

	public static void main(String[] args) {
		SpringApplication.run(ShoppingcartApplication.class, args);
	}

}
