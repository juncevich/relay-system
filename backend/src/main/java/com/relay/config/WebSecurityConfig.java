package com.relay.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;

// @EnableWebFluxSecurity
@EnableWebSecurity
public class WebSecurityConfig {

    @Bean
    public UserDetailsService userDetailsService() {

        InMemoryUserDetailsManager manager = new InMemoryUserDetailsManager();
        manager.createUser(
                User.withUsername("user").password("{noop}password").roles("USER").build());
        manager.createUser(User.withUsername("admin").password("{noop}password")
                .roles("USER", "ADMIN").build());
        return manager;
    }

    @Autowired
    public void configureGlobal(AuthenticationManagerBuilder auth) throws Exception {

        auth.inMemoryAuthentication().withUser("user") // #1
                .password("{noop}password").roles("USER").and().withUser("admin") // #2
                .password("{noop}password").roles("ADMIN", "USER");
    }

    // @Bean
    // public ReactiveUserDetailsService userDetailsRepository() {
    //
    // UserDetails user =
    // User.withUsername("user").password("{noop}password").roles("USER").build();
    // return new MapReactiveUserDetailsService(user);
    // }
    //
    // @Bean
    // protected SecurityWebFilterChain configure(ServerHttpSecurity http) {
    //
    // http.httpBasic().disable();
    // http.formLogin().disable();
    // http.csrf().disable();
    // http.logout().disable();
    // // http.authorizeExchange().anyExchange().permitAll()
    // // // .and().formLogin().loginPage("/login")
    // // .and().csrf().disable();
    // // return http.build();
    // return http.authorizeExchange().anyExchange().permitAll().and().build();
    // }

}
