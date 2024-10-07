package com.xandy.financaspessoais;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@SpringBootApplication
public class FinancaspessoaisApplication implements WebMvcConfigurer{
	
//	@Override
//	public void addCorsMappings(org.springframework.web.servlet.config.annotation.CorsRegistry registry) {
//		registry.addMapping("/**").allowedMethods("GET", "POST", "PUT", "DELETE", "OPTIONS", "HEAD", "TRACE", "CONNECT");
//	}

	public static void main(String[] args) {
		SpringApplication.run(FinancaspessoaisApplication.class, args);
	}

}
