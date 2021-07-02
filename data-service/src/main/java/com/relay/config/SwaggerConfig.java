package com.relay.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.Contact;
import springfox.documentation.spring.web.plugins.Docket;

import java.util.ArrayList;

import static springfox.documentation.spi.DocumentationType.SWAGGER_2;

/**
 * Swagger configuration file
 */
//@EnableSwagger2
@Configuration
public class SwaggerConfig {

    /**
     * Default contact information
     */
    private static final Contact DEFAULT_CONTACT =
            new Contact("Alexandr Juncevich", "", "a.juncevich@gmail.com");

    /**
     * Default api info
     */
    private static final ApiInfo DEFAULT_API_INFO = new ApiInfo("Api Documentation",
            "Api Documentation", "1.0", "urn:tos", DEFAULT_CONTACT, "Apache 2.0",
            "http://www.apache.org/licenses/LICENSE-2.0", new ArrayList<>());

    /**
     * Setting Docket bean
     * 
     * @return Docket bean
     */
    @Bean
    public Docket api() {
        return new Docket(SWAGGER_2).apiInfo(DEFAULT_API_INFO).select()
                .apis(RequestHandlerSelectors.basePackage("com.relay.integrations.controllers"))
                .paths(PathSelectors.any()).build();
    }
}
