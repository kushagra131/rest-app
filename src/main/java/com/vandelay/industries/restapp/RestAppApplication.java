package com.vandelay.industries.restapp;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;



@SpringBootApplication
@EnableJpaRepositories("com.vandelay.industries.restapp.repository")
public class RestAppApplication {

	public static void main(String[] args) {
		SpringApplication.run(RestAppApplication.class, args);
	}

}
