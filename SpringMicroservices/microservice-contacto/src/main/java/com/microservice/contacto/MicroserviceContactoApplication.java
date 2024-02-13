package com.microservice.contacto;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;


@EnableDiscoveryClient
@SpringBootApplication
public class MicroserviceContactoApplication {

	public static void main(String[] args) {
		SpringApplication.run(MicroserviceContactoApplication.class, args);
	}

}
