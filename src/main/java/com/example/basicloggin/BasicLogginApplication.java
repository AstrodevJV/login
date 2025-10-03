package com.example.basicloggin;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableAsync;

@SpringBootApplication
@EnableAsync
public class BasicLogginApplication {

	public static void main(String[] args) {
		SpringApplication.run(BasicLogginApplication.class, args);
	}

}
