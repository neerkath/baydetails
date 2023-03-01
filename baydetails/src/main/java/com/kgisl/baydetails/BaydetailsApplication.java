package com.kgisl.baydetails;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import io.swagger.v3.oas.annotations.OpenAPIDefinition;

@SpringBootApplication
@OpenAPIDefinition
public class BaydetailsApplication {

	public static void main(String[] args) {
		SpringApplication.run(BaydetailsApplication.class, args);
	}

}
