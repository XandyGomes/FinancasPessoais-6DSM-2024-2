package com.xandy.financaspessoais;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@SpringBootApplication
public class FinancaspessoaisApplication implements WebMvcConfigurer{

	public static void main(String[] args) {
		SpringApplication.run(FinancaspessoaisApplication.class, args);
	}

}
