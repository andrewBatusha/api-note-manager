package com.example.apinotemanager.controller;

import com.example.apinotemanager.client.SecurityManagerApiClient;
import com.example.apinotemanager.dto.JwtResponse;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.AllArgsConstructor;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.AsyncHandlerInterceptor;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@Slf4j
@RequiredArgsConstructor
public class AuthorizationInterceptor implements AsyncHandlerInterceptor {

    private SecurityManagerApiClient securityManagerApiClient;

    public AuthorizationInterceptor(SecurityManagerApiClient securityManagerApiClient) {
        this.securityManagerApiClient = securityManagerApiClient;
    }

    @Override
    public boolean preHandle(
            HttpServletRequest request,
            HttpServletResponse response,
            Object handler) throws IOException {
        String jwtToken = request.getHeader("Authorization");
        JwtResponse jwtResponse = securityManagerApiClient.pass(jwtToken);
        log.info("authorization results: access={}, message={}", jwtResponse.getAccess(), jwtResponse.getMessage());

        if (!jwtResponse.getAccess()) {
            response.setContentType("application/json");
            response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
            ObjectMapper mapper = new ObjectMapper();
            mapper.writeValue(response.getOutputStream(), new JwtResponse("Full authentication is required to access this resource"));
        }
        return jwtResponse.getAccess();
    }
}
