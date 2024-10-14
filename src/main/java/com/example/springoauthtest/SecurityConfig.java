package com.example.springoauthtest;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.oauth2.jwt.JwtDecoder;
import org.springframework.security.oauth2.jwt.NimbusJwtDecoder;
import org.springframework.security.oauth2.server.authorization.config.annotation.web.configuration.OAuth2AuthorizationServerConfiguration;
import org.springframework.security.web.SecurityFilterChain;

import java.security.KeyPair;
import java.security.KeyPairGenerator;
import java.security.NoSuchAlgorithmException;
import java.security.interfaces.RSAPublicKey;

@Configuration
public class SecurityConfig {

    public SecurityConfig() throws NoSuchAlgorithmException {
    }

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        OAuth2AuthorizationServerConfiguration.applyDefaultSecurity(http);
        return http.build();
    }

    KeyPairGenerator kpg = KeyPairGenerator.getInstance("RSA");
    KeyPair kp = kpg.generateKeyPair();

    @Bean
    public JwtDecoder jwtDecoder() {
        // JWT 공개 키로 토큰을 검증하는 Decoder
        return NimbusJwtDecoder.withPublicKey((RSAPublicKey) kp.getPublic()).build();
    }
}