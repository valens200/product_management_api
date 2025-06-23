package rw.productant.v1.config;

import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.models.Components;
import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.security.SecurityRequirement;
import io.swagger.v3.oas.models.security.SecurityScheme;
import io.swagger.v3.oas.models.servers.Server;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.Arrays;

@OpenAPIDefinition
@Configuration
public class OpenApiConfig {
        @Bean
        public OpenAPI customOpenAPI(
                        @Value("${openapi.service.title}") String serviceTitle,
                        @Value("${openapi.service.version}") String serviceVersion,
                        @Value("${openapi.service.url}") String url) {
                final String securitySchemeName = "bearerAuth";
                return new OpenAPI()
                                .servers(Arrays.asList(
                                                new Server().url(url),
                                                new Server().url("http://localhost:8080/")))
                                .components(
                                                new Components()
                                                                .addSecuritySchemes(
                                                                                securitySchemeName,
                                                                                new SecurityScheme()
                                                                                                .type(SecurityScheme.Type.HTTP)
                                                                                                .scheme("bearer")
                                                                                                .bearerFormat("JWT")))
                                .security(Arrays.asList(new SecurityRequirement().addList(securitySchemeName)))
                                .info(new Info().title(serviceTitle).version(serviceVersion));
        }
}