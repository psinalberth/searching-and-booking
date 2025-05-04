package com.github.psinalberth.catalog.event.providers.springdoc;

import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Contact;
import io.swagger.v3.oas.models.info.Info;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class SwaggerConfig {

    @Bean
    public OpenAPI openAPI() {
        return new OpenAPI()
                .info(new Info()
                        .title("Catalog Service")
                        .description("Service responsible for catalog management and search operations")
                        .contact(new Contact()
                                .name("Inalberth Pinheiro")
                                .url("https://github.com/psinalberth"))
                        .version("1.0.0")
                );
    }
}
