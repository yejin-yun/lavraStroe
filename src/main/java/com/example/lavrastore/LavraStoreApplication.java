package com.example.lavrastore;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication
@ComponentScan(basePackages="com.example.lavrastore.*")
public class LavraStoreApplication {

	public static void main(String[] args) {
		SpringApplication.run(LavraStoreApplication.class, args);
	}

}
