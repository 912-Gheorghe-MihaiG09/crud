package com.discount.crud.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class WebConfig implements WebMvcConfigurer {

    @Override
    public void addCorsMappings(CorsRegistry registry) {
        // Disabling CORS globally
        registry.addMapping("/**")
                .allowedMethods("GET", "POST", "PUT", "DELETE") // specify allowed HTTP methods
                .allowedHeaders("*") // specify allowed headers
                .allowCredentials(false); // do not allow credentials
    }
}
