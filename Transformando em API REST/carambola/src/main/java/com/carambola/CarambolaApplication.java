package com.carambola;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;

@SpringBootApplication
@EnableFeignClients
public class CarambolaApplication {

	public static void main(String[] args) {
		SpringApplication.run(CarambolaApplication.class, args);
	}







}
