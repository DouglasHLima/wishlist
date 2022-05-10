package com.doughlima.wishlist.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import springfox.documentation.builders.*;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.Contact;
import springfox.documentation.service.Response;
import springfox.documentation.service.ResponseMessage;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;

import java.util.ArrayList;
import java.util.List;

@Configuration
public class SwaggerConfiguration {
    @Bean
    public Docket api(){
        return new Docket(DocumentationType.SWAGGER_2)
                .select()
                .apis(RequestHandlerSelectors.basePackage("com.doughlima.wishlist.gateways.controllers"))
                .paths(PathSelectors.any())
                .build()
                .apiInfo(buildApiInfo())
                .useDefaultResponseMessages(false);
    }

    private ApiInfo buildApiInfo() {
        return new ApiInfoBuilder()
                .title("WishList API")
                .description("A wishlist api service for e-commerce platform")
                .contact(new Contact(
                        "Douglas Lima",
                        "https://github.com/DouglasHLima",
                        "douglashdelima@gmail.com"))
                .build();
    }

}
