package com.example.apinotemanager.config;

import com.example.apinotemanager.client.SecurityManagerApiClient;
import okhttp3.OkHttpClient;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.concurrent.TimeUnit;

@Configuration
public class OkHttpClientFactory {

    @Value("${security-manager-api-client-service.url}")
    private String serviceUrl;

    @Value("${security-manager-api-client.connectTimeout:10000}")
    private int clientConnectTimeout;

    @Value("${security-manager-api-client.readTimeout:10000}")
    private int clientReadTimeout;

    @Value("${security-manager-api-client.writeTimeout:10000}")
    private int clientWriteTimeout;

    @Bean
    public SecurityManagerApiClient securityManagerApiClient() {
        OkHttpClient okHttpClient = new OkHttpClient().newBuilder()
                .connectTimeout(clientConnectTimeout, TimeUnit.MILLISECONDS)
                .readTimeout(clientReadTimeout, TimeUnit.MILLISECONDS)
                .writeTimeout(clientWriteTimeout, TimeUnit.MILLISECONDS)
                .build();

        return new SecurityManagerApiClient(serviceUrl, okHttpClient);
    }

}