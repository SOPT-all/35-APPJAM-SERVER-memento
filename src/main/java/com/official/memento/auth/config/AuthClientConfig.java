package com.official.memento.auth.config;

import com.official.memento.auth.domain.AuthProvider;
import com.official.memento.auth.domain.port.AuthClientOutputPort;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.Map;

@Configuration
public class AuthClientConfig {

    @Bean
    public Map<AuthProvider, AuthClientOutputPort> authClientAdapters(
            AuthClientOutputPort googleAuthClientAdapter,
            AuthClientOutputPort appleAuthClientAdapter
    ) {
        return Map.of(
                AuthProvider.GOOGLE, googleAuthClientAdapter,
                AuthProvider.APPLE, appleAuthClientAdapter
        );
    }
}
