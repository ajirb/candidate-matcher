package com.candidate.finder.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import io.swagger.v3.oas.models.Components;
import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.info.License;

@Configuration
public class OpenApiConfig {

  @Bean
  public OpenAPI customOpenAPI() {
    return new OpenAPI()
        .components(new Components())
        .info(new Info()
            .title("Candidate Finder Application")
            .description("This is a sample Spring Boot RESTful service to match candidate with jobs")
            .termsOfService("terms")
            .license(new License().name("GNU"))
            .version("1.0")
        );
  }
}
