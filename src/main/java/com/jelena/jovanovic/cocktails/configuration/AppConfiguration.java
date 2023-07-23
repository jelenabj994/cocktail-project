package com.jelena.jovanovic.cocktails.configuration;

import java.util.List;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.client.RestTemplate;

@Configuration
public class AppConfiguration {
	@Bean
	public RestTemplate restTemplate() {
		return new RestTemplate();
	}

	@Bean
	public List<String> cocktailNameList() {
		return List.of("Margarita", "Aperol Spritz", "Amaretto Sour", "Gin Tonic", "Tequila Sunrise", "Bloody Mary",
				"Sex on the Beach", "White Russian", "Cosmopolitan", "Blue Lagoon", "Mojito", "Long Island Tea",
				"Espresso Martini", "Chocolate Drink", "Frappé", "Iced Coffee", "Thai Iced Tea");
	}
}
