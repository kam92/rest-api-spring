package com.camera.schoolsystem.configuration;


import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Contact;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.info.License;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class OpenApiConfig {


    @Bean
    public OpenAPI myOpenAPI() {


        Contact contact = new Contact();
        contact.setName("Kalil El Ammar");
        contact.setUrl("https://www.linkedin.com/in/kalil-el-ammar-a083b51ba/");

        License mitLicense = new License().name("License").url("https://www.linkedin.com/in/kalil-el-ammar-a083b51ba/");

        Info info = new Info()
                .title("School System API")
                .version(" ")
                .contact(contact)
                .description("Endpoints da API do School System.").termsOfService("https://www.linkedin.com/in/kalil-el-ammar-a083b51ba/")
                .license(mitLicense);

        return new OpenAPI().info(info);
    }
}