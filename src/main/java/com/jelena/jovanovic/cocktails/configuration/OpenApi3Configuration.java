package com.jelena.jovanovic.cocktails.configuration;


import org.springframework.context.annotation.Configuration;

import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.info.Info;

@Configuration
@OpenAPIDefinition(info = @Info(title = "Cocktails", version = "v1", description = "Cocktails details"))
public class OpenApi3Configuration {
//http://localhost:8080/swagger-ui/index.html
}
