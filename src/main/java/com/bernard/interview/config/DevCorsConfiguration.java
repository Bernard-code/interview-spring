package com.bernard.interview.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class DevCorsConfiguration implements WebMvcConfigurer {

    @Value("${ALLOWED_ORIGINS:http://localhost:4200}")
    private String allowedOrigins;

    @Bean
    public DevCorsConfiguration corsConfigurer() {
        return new DevCorsConfiguration() {
            @Override
            public void addCorsMappings(CorsRegistry registry) {
                registry.addMapping("/**")
                        .allowedOrigins("http://localhost:4200", "https://bernard-code.github.io")
                        .allowedMethods("GET", "POST", "PUT", "DELETE", "OPTIONS")
                        .allowedHeaders("*")
                        .allowCredentials(true);
            }
        };
    }
}