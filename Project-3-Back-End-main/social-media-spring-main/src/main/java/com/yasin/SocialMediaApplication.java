package com.revature;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;


@SpringBootApplication
public class SocialMediaApplication {
	@Bean
	public WebMvcConfigurer configure() {
		return new WebMvcConfigurer() {
			@Override
			public void addCorsMappings(CorsRegistry registry) {
				registry.addMapping("/**").allowedOrigins("http://localhost:3000").allowCredentials(true).allowedMethods("OPTIONS", "PUT", "POST", "PATCH", "DELETE", "GET");
			}
		};
	}

	public static void main(String[] args) {
		SpringApplication.run(SocialMediaApplication.class, args);
	}
}
