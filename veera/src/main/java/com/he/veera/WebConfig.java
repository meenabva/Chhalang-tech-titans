package com.he.veera;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class WebConfig implements WebMvcConfigurer {

    @Override
    public void addCorsMappings(CorsRegistry registry) {
        registry.addMapping("/*").allowedOriginPatterns("").allowCredentials(true)
                .allowedMethods( "HEAD","GET", "POST", "PUT", "DELETE", "PATCH","OPTIONS")
                .allowedHeaders("Content-Type,X-Requested-With,Accept,Authorization,Origin,Access-Control-Request-Method,Access-Control-Request-Headers");

    }
}