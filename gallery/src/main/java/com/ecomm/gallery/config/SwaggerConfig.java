package com.ecomm.gallery.config;

import com.google.common.base.Predicates;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.Contact;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;

import java.util.Collections;
import java.util.function.Predicate;

@Configuration
public class SwaggerConfig {

    @Bean
    public Docket apiEndpointVessel() {
        return new Docket(DocumentationType.SWAGGER_2)
                .select()
                .apis(RequestHandlerSelectors.any())
                .paths(PathSelectors.any())
                .paths(Predicates.not(PathSelectors.regex("/error/*")))
                .paths(Predicates.not(PathSelectors.regex("/actuator")))
                .build()
                .apiInfo((apiInfo()));
    }

    private ApiInfo apiInfo() {
        Contact contact = new Contact(
                "Vessel",
                "TBA",
                "rangkuti.hardin@gmail.com");

        return new ApiInfo(
                "Ecomm Gallery",
                "Swagger",
                "Version 1.0.0",
                "OpenSource",
                contact,
                "OpenSource",
                "OpenSource",
                Collections.emptyList()
        );
    }
}

