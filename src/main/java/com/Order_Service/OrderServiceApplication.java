package com.Order_Service;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.openfeign.EnableFeignClients;


@EnableFeignClients
@EnableDiscoveryClient

@SpringBootApplication
public class OrderServiceApplication {
	int x= 10;
	public static void main(String[] args) {
		SpringApplication.run(OrderServiceApplication.class, args);
	}

}
