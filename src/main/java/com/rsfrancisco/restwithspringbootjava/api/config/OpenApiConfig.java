package com.rsfrancisco.restwithspringbootjava.api.config;

import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.info.License;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class OpenApiConfig {
    @Bean
    public OpenAPI customOpenApi() {
        return new OpenAPI()
                .info(new Info()
                        .title("RESTful API with Java 20 and Spring Boot 3")
                        .version("v1")
                        .description("Api de praticas e estudos do Java e Spring Boot")
                        .termsOfService("https://github.com/broncasrafa")
                        .license(new License()
                                .name("Apache 2.0")
                                .url("https://github.com/broncasrafa"))
                );
    }
}
