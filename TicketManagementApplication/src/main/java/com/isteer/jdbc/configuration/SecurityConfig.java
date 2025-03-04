package com.isteer.jdbc.configuration;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;

@Configuration
@EnableWebSecurity
@EnableMethodSecurity(prePostEnabled = true) // Enables @PreAuthorize and @Secured
public class SecurityConfig {

    @Autowired
    private UserDetailsService userDetailService;

    @Autowired
    private JwtFilter jwtFilter;

    // Define Security Filter Chain to configure security rules
    @Bean
    SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        return http
                .csrf(csrf -> csrf.disable())  // Disable CSRF (as we're using stateless authentication)
                .authorizeHttpRequests(authorizeRequests ->
                        authorizeRequests
                                .requestMatchers("appSteer/registerUser").permitAll()  // Public end points
                                .anyRequest().authenticated()  // All other requests must be authenticated
                ).httpBasic(Customizer.withDefaults())
                .sessionManagement(session -> 
                        session.sessionCreationPolicy(org.springframework.security.config.http.SessionCreationPolicy.STATELESS)  // State less session for JWT
                )
                .addFilterBefore(jwtFilter, UsernamePasswordAuthenticationFilter.class)  // Add JWT filter
                .build();
    }

    // AuthenticationProvider Bean (Configures DAO Authentication with BCrypt Encoder)
    @Bean
    AuthenticationProvider authenticationProvider() {
        DaoAuthenticationProvider provider = new DaoAuthenticationProvider();
        provider.setPasswordEncoder(new BCryptPasswordEncoder(7));  
        provider.setUserDetailsService(userDetailService);  // Set UserDetailsService (your service for loading user info)
        return provider;
    }

    // AuthenticationManager Bean (Required for authentication in JWT filter)
    @Bean
    AuthenticationManager authenticationManager(AuthenticationConfiguration config) throws Exception {
    	return config.getAuthenticationManager();
    }
}

