package com.projetowebfatec.projetowebfatec2025.config;
 
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Info;
 
@Configuration
public class SwaggerConfig {
 
    //http://localhost:8080/swagger-ui/index.html
    @Bean
    public OpenAPI customOpenAPI() {
        return new OpenAPI()
            .info(new Info()
                    .title("API de Projeto 2025 - Semestre 1")
                    .version("1.0")
                    .description("Documentação da API do Projeto 2025 - Semestre 1"));
    }
}