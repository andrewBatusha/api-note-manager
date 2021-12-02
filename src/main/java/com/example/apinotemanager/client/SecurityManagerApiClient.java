package com.example.apinotemanager.client;

import com.example.apinotemanager.dto.JwtResponse;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import com.google.common.net.HttpHeaders;
import lombok.extern.slf4j.Slf4j;
import okhttp3.HttpUrl;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import okhttp3.ResponseBody;

import java.io.IOException;

@Slf4j
public class SecurityManagerApiClient {

    private static final String JSON_MEDIA_TYPE = "application/json; charset=utf-8";

    private final OkHttpClient okHttpClient;

    private final HttpUrl authorizationEndpoint;

    private final ObjectMapper objectMapper;

    public SecurityManagerApiClient(String baseUrl, OkHttpClient okHttpClient) {
        this.okHttpClient = okHttpClient;
        this.objectMapper = new ObjectMapper();
        this.objectMapper.registerModule(new JavaTimeModule());
        this.objectMapper.configure(SerializationFeature.WRITE_DATES_AS_TIMESTAMPS, false);

        this.authorizationEndpoint = HttpUrl.parse(baseUrl);

        if (authorizationEndpoint == null) {
            throw new IllegalArgumentException("Provided an empty or invalid base url for security manager api");
        }
    }

    public JwtResponse pass(String token) throws IOException {
        if (token != null) {
            HttpUrl httpUrl = authorizationEndpoint.newBuilder()
                    .addPathSegment("api")
                    .addPathSegment("auth")
                    .addPathSegment("pass")
                    .build();

            Request request = new Request.Builder()
                    .url(httpUrl)
                    .addHeader(HttpHeaders.CONTENT_TYPE, JSON_MEDIA_TYPE)
                    .addHeader("Authorization", token)
                    .get()
                    .build();

            try (Response response = okHttpClient.newCall(request).execute()) {
                ResponseBody responseBody = response.body();
                if (response.isSuccessful() && responseBody != null) {
                    return objectMapper.readValue(responseBody.string(), JwtResponse.class);
                } else {
                    log.error("log-tracking=5f2f548e-2752-45d2-8040-5fd477a8293a, message=\"Failed to check authorization\", "
                              + "token={}, responseCode={}, responseMessage={}", token, response.code(), response.message());
                }
            }
        }
        return new JwtResponse("Server error");
    }
}