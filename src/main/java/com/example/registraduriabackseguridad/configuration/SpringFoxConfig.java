package com.example.registraduriabackseguridad.configuration;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.Contact;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

import java.util.Collections;

@Configuration //
@EnableSwagger2 // Etiqueta de Swagger
public class SpringFoxConfig {
    //Propiedades del properties del Swagger
    @Value("${api.swagger.base_package}")
    private String basePackage;

    @Value("${api.swagger.title}")
    private String title;

    @Value("${api.swagger.description}")
    private String description;

    @Value("${api.swagger.version}")
    private String version;

    @Value("${api.swagger.terms}")
    private String terms;

    @Value("${api.swagger.contact.name}")
    private String contactName;

    @Value("${api.swagger.contact.url}")
    private String contactUrl;

    @Value("${api.swagger.contact.email}")
    private String contactEmail;

    @Value("${api.swagger.license.name}")
    private String licenseName;

    @Value("${api.swagger.license.url}")
    private String licenseUrl;

    //Información que recibe
    @Bean
    public Docket api() {
        return new Docket(DocumentationType.SWAGGER_2).apiInfo(getApiInfo()).securityContexts(Collections.emptyList())
                .securitySchemes(Collections.emptyList()).select().apis(RequestHandlerSelectors.basePackage(basePackage)).paths(PathSelectors.any())
                .build();

    }
    //Información del Api
    private ApiInfo getApiInfo() {
        return new ApiInfo(title,description,version,terms, new Contact(contactName,contactUrl,contactEmail), licenseName, licenseUrl,
                Collections.emptyList());
    }
}
