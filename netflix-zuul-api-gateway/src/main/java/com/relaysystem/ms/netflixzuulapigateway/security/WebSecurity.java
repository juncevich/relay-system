package com.relaysystem.ms.netflixzuulapigateway.security;

import org.springframework.context.annotation.*;
import org.springframework.core.env.*;
import org.springframework.http.*;
import org.springframework.security.config.annotation.web.builders.*;
import org.springframework.security.config.annotation.web.configuration.*;
import org.springframework.security.config.http.*;

@Configuration
@EnableWebSecurity
public class WebSecurity extends WebSecurityConfigurerAdapter {
    private final Environment environment;

    public WebSecurity(Environment environment) {
        this.environment = environment;
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.csrf().disable();
        http.headers().frameOptions().disable();
        http.authorizeRequests()
                .antMatchers(environment.getProperty("api.zuul.actuator.url.path")).permitAll()
                .antMatchers(environment.getProperty("api.h2console.url.path")).permitAll()
                .antMatchers(HttpMethod.POST, environment.getProperty("api.registration.url.path")).permitAll()
                .antMatchers(HttpMethod.POST, environment.getProperty("api.login.url.path")).permitAll()
                .anyRequest().authenticated()
                .and()
                .addFilter(new AuthorizationFilter(authenticationManager(), environment));
        http.sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS);
    }
}
