package com.nhoclahola.socialnetworkv1.configuration;

import com.nhoclahola.socialnetworkv1.exception.FilterExceptionHandler;
import com.nhoclahola.socialnetworkv1.exception.JwtAuthenticationEntryPoint;
import com.nhoclahola.socialnetworkv1.security.JwtValidator;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.www.BasicAuthenticationFilter;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.CorsConfigurationSource;

import java.util.List;

@Configuration
@EnableWebSecurity
@EnableMethodSecurity
@RequiredArgsConstructor
public class SecurityConfig
{
    @Bean
    SecurityFilterChain securityFilterChain(HttpSecurity httpSecurity) throws Exception
    {
        httpSecurity.authorizeHttpRequests(request ->
                request.requestMatchers("/api/**").authenticated()
                        .anyRequest().permitAll());

        httpSecurity.sessionManagement(management ->
                management.sessionCreationPolicy(SessionCreationPolicy.STATELESS));

        httpSecurity.addFilterBefore(new JwtValidator(), BasicAuthenticationFilter.class);
        httpSecurity.addFilterBefore(new FilterExceptionHandler(), JwtValidator.class);
        httpSecurity.exceptionHandling(exceptionHandling ->
                exceptionHandling.authenticationEntryPoint(new JwtAuthenticationEntryPoint()));
        httpSecurity.csrf(httpSecurityCsrfConfigurer -> httpSecurityCsrfConfigurer.disable());
        httpSecurity.cors(cors -> cors.configurationSource(corsConfigurationSource()));
        return httpSecurity.build();

    }

    private CorsConfigurationSource corsConfigurationSource()
    {
        // Interface with 1 method
        return request ->
        {
            CorsConfiguration configuration = new CorsConfiguration();
            configuration.setAllowedOrigins(List.of("http://localhost:3000"));
            configuration.setAllowedMethods(List.of("*"));
            configuration.setAllowCredentials(true);
            configuration.setAllowedHeaders(List.of("*"));
            configuration.setExposedHeaders(List.of("Authorization"));
            configuration.setMaxAge(3600L);
            return configuration;
        };
    }

    @Bean
    public PasswordEncoder passwordEncoder()
    {
        return new BCryptPasswordEncoder();
    }
}
