package com.project.professor.allocation.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Info;

@Configuration
public class SwaggerConfig {

	@Bean
	public OpenAPI springShopOpenAPI() {
		return new OpenAPI().info(getOpenApiInfo());
	}

	private Info getOpenApiInfo() {
		return new Info()
			.title("Professor Allocation")
			.description("Projeto backend web do curso de Pós-Graduação em Engenharia de Software na disciplina do professor Tiago Santos.")
			.version("0.0.1-SNAPSHOT");
	}
}