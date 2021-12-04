package com.example.apinotemanager.config;

import com.example.apinotemanager.client.SecurityManagerApiClient;
import com.example.apinotemanager.controller.AuthorizationInterceptor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class WebMvcConfig implements WebMvcConfigurer {

    @Autowired
    private SecurityManagerApiClient securityManagerApiClient;

    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(new AuthorizationInterceptor(securityManagerApiClient))
                .excludePathPatterns(AUTH_WHITELIST);
    }

    private static final String[] AUTH_WHITELIST = {
            "/swagger-ui.html/**", "/configuration/**", "/swagger-resources/**", "/v2/api-docs","/webjars/**"
    };
}