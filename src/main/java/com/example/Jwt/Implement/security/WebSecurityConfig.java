package com.example.Jwt.Implement.security;

import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpStatus;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.ProviderManager;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

@Configuration
@RequiredArgsConstructor
public class WebSecurityConfig {

    // Inject the beans that will be used to create the AuthenticationManager
    private final UserDetailsService userDetailsService;
    private final PasswordEncoder passwordEncoder;
    private  final JwtAuthFilter jwtAuthFilter;
//    @Bean
//    public AuthenticationManager authenticationManager() {
//        // Instantiate DaoAuthenticationProvider and pass dependencies to the constructor
//        DaoAuthenticationProvider authenticationProvider =
//                new DaoAuthenticationProvider( userDetailsService);
//
//        // OR (If you prefer not to use the constructor that reverses the arguments)
//        // DaoAuthenticationProvider authenticationProvider = new DaoAuthenticationProvider();
//        // authenticationProvider.setUserDetailsService(userDetailsService); // This setter is now removed
//        // authenticationProvider.setPasswordEncoder(passwordEncoder);       // This setter is now removed
//
//        return new ProviderManager(authenticationProvider);
//    }

    @Bean
    public AuthenticationManager authenticationManager(AuthenticationConfiguration configuration){
        return configuration.getAuthenticationManager();

    }
    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity httpSecurity) throws Exception{
        httpSecurity
                .csrf(csrfConfig -> csrfConfig.disable())
                .sessionManagement(sessionConfig ->
                        sessionConfig.sessionCreationPolicy(SessionCreationPolicy.STATELESS))
                .authorizeHttpRequests(auth->auth
                    .requestMatchers("/auth/**").permitAll()
                                .anyRequest().authenticated()
//                    .requestMatchers("/admin/**").hasRole("ADMIN")
//                    .requestMatchers("/doctor/**").hasAnyRole("DOCTOR","ADMIN")
        ).addFilterBefore(jwtAuthFilter, UsernamePasswordAuthenticationFilter.class);
        return httpSecurity.build();
    }
}
