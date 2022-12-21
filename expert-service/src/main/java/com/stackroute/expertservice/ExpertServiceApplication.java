package com.stackroute.expertservice;


import org.springframework.boot.SpringApplication;

import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;


@EnableDiscoveryClient
@SpringBootApplication()




public class ExpertServiceApplication {

	public static void main(String[] args) {
		SpringApplication.run(ExpertServiceApplication.class, args);
	}

}
