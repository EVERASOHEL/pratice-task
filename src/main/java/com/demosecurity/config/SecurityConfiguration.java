package com.demosecurity.config;

import com.demosecurity.utils.Role;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.web.AuthenticationEntryPoint;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.access.AccessDeniedHandler;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

@Configuration
@EnableWebSecurity
@RequiredArgsConstructor
@EnableGlobalMethodSecurity(prePostEnabled = true,securedEnabled = true)
public class SecurityConfiguration {

    private final JwtAuthenticationFilter jwtAuthFilter;
    private final AuthenticationProvider authenticationProvider;

    @Autowired
    @Qualifier("customAuthenticationEntryPoint")
    AuthenticationEntryPoint authEntryPoint;

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http
                .csrf().disable()
                .authorizeHttpRequests()
//                    .requestMatchers("/api/v1/auth/register").hasAuthority(Role.ROLE_ADMIN.name())
                    .requestMatchers("/api/v1/auth/register").permitAll()
                    .requestMatchers("/api/v1/auth/users").hasAuthority(Role.ROLE_ADMIN.name())
                    .requestMatchers("/api/v1/auth/getAllUsers").hasAuthority(Role.ROLE_ADMIN.name())
                    .requestMatchers("/api/v1/rolePermission/getAllRoles").hasAuthority(Role.ROLE_ADMIN.name())
                    .requestMatchers("api/v1/auth/authenticate").permitAll() // Permit access to the registration endpoint
//                    .requestMatchers("/api/v1/product/**").hasAuthority(Role.ROLE_ADMIN.name())
                    .requestMatchers("/api/v1/product/getAllProducts").hasAnyAuthority(Role.ROLE_ADMIN.name(),Role.ROLE_MANAGER.name())
                    .requestMatchers("/api/v1/sales/**").hasAnyAuthority(Role.ROLE_ADMIN.name(), Role.ROLE_ACCOUNTANT.name())
                    .requestMatchers("/api/v1/purchase/**").hasAnyAuthority(Role.ROLE_ADMIN.name(), Role.ROLE_ACCOUNTANT.name())
                    .requestMatchers("/api/v1/rolePermission/getAllPermissionList").hasAnyAuthority(Role.ROLE_ADMIN.name(),Role.ROLE_MANAGER.name())
                    .requestMatchers("/api/v1/rolePermission/getAllAccessControl").hasAuthority(Role.ROLE_ADMIN.name())
                    .requestMatchers("/api/v1/rolePermission/createNewPermission").hasAuthority(Role.ROLE_ADMIN.name())
                    .anyRequest().authenticated()
                .and()
                .sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS)
                .and()
                .authenticationProvider(authenticationProvider)
                .addFilterBefore(jwtAuthFilter, UsernamePasswordAuthenticationFilter.class)
                .httpBasic(basic -> basic.authenticationEntryPoint(authEntryPoint)) //handle authentication exception
                .exceptionHandling(Customizer.withDefaults());

        return http.build();
    }
}
