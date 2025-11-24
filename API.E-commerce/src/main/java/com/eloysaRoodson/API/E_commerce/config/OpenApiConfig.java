package com.eloysaRoodson.API.E_commerce.config;

import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.info.Contact;
import io.swagger.v3.oas.models.info.License;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class OpenApiConfig {
    
    @Bean
    public OpenAPI customOpenAPI() {
        return new OpenAPI()
            .info(new Info()
                .title("E-commerce API")
                .version("1.0.0")
                .description("API REST para um sistema de e-commerce com gestão de produtos, categorias, usuários e pedidos.")
                .contact(new Contact()
                    .name("Suporte")
                    .email("suporte@ecommerce.com"))
                .license(new License()
                    .name("Apache 2.0")
                    .url("http://springdoc.org")));
    }
}