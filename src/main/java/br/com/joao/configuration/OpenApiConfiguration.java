package br.com.joao.configuration;

import org.springframework.context.annotation.Bean;

import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.info.Info;
import io.swagger.v3.oas.models.Components;
import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.License;

@OpenAPIDefinition(info = 
@Info(title = "Book Service API",
		version = "v1.0",
		description = "Book-service API documentation"))
public class OpenApiConfiguration {
	
	@Bean
	public OpenAPI customOpenAPI() {
		return new OpenAPI()
				.components(new Components())
				.info(new io.swagger.v3.oas.models.info.Info()
						.title("Book-service API documentation")
						.version("v1.0")
						.license(new License()
								.name("Apache")
								.url("http://springdoc.org")));
	}

}
