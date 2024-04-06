package com.he.veera;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.web.client.RestTemplate;

@SpringBootApplication
public class VeeraApplication {

	public static void main(String[] args) {
		SpringApplication.run(VeeraApplication.class, args);
	}

	@Bean
	public RestTemplate getRestTemplate()
	{
		return new RestTemplate();
	}
}
