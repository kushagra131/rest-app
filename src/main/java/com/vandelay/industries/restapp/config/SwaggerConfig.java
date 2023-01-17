package com.vandelay.industries.restapp.config;

import java.util.Collections;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

@Configuration
@EnableSwagger2
public class SwaggerConfig {
	
    @Bean
    public Docket productApi() {
        return new Docket(DocumentationType.SWAGGER_2)
		                .select()
		                .paths(PathSelectors.ant("/api/v1/**"))
		                .apis(RequestHandlerSelectors.basePackage("com.vandelay.industries.restapp"))
		                .build()
		                .apiInfo(metaData());

    }
    
    private ApiInfo metaData() {
        ApiInfo apiInfo = new ApiInfo(
                "Vandelay Industries API - DRAFT",
                "Sample API for Vandelay Industries",
                "1.0",
                "Terms of Service",
                new springfox.documentation.service.Contact("Kushagra Sharma", "https://www.linkedin.com/in/ksharma3", "kushagra.sharma131@gmail.com"),
                "API License",
                "https://github.com/kushagra131",
                Collections.emptyList());
        return apiInfo;
    }
}