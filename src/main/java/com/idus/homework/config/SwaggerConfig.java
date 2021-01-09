package com.idus.homework.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.Contact;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

import java.util.ArrayList;

@Configuration
@EnableSwagger2
public class SwaggerConfig {

    @Bean
    public Docket api() {
        String version = "V1";
        String title = "API Document";

        return new Docket(DocumentationType.SWAGGER_2)
                .useDefaultResponseMessages(false)
                .groupName(version)
                .select()
                .apis(RequestHandlerSelectors.basePackage("com.idus.homework.controller"))
                .paths(PathSelectors.any())
                .build()
                .apiInfo(apiInfo(title, version));

    }

    private ApiInfo apiInfo(String title, String version) {
        return new ApiInfo(
                title,
                "Swagger API Docs",
                version,
                null,
                new Contact("YoungHo Kim", "https://github.com/kyh1537/", "kyh1537@naver.com"),
                null,
                null,
                new ArrayList<>());
    }
}
