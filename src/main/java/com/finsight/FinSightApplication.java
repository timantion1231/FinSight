package com.finsight;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication

@ComponentScan("com.finsight.controller")
@ComponentScan("com.finsight.service.impl")
@ComponentScan("com.finsight.repository")
public class FinSightApplication {
	public static void main(String[] args) {
		SpringApplication.run(FinSightApplication.class, args);
	}
}
