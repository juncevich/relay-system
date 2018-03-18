package com.relay.config;

import org.springframework.context.annotation.Bean;
import org.springframework.security.config.annotation.web.reactive.EnableWebFluxSecurity;
import org.springframework.security.config.web.server.ServerHttpSecurity;
import org.springframework.security.core.userdetails.MapReactiveUserDetailsService;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.web.server.SecurityWebFilterChain;

@EnableWebFluxSecurity
public class WebSecurityConfig {

    // @Bean
    // public UserDetailsService userDetailsService() {
    //
    // InMemoryUserDetailsManager manager = new InMemoryUserDetailsManager();
    // manager.createUser(User.withUsername("user").password("password").roles("USER").build());
    // manager.createUser(
    // User.withUsername("admin").password("password").roles("USER", "ADMIN").build());
    // return manager;
    // }

    // @Autowired
    // public void configureGlobal(AuthenticationManagerBuilder auth) throws Exception {
    //
    // auth.inMemoryAuthentication().withUser("user") // #1
    // .password("password").roles("USER").and().withUser("admin") // #2
    // .password("password").roles("ADMIN", "USER");
    // }

    @Bean
    public MapReactiveUserDetailsService userDetailsRepository() {

        UserDetails user = User.withDefaultPasswordEncoder().username("user").password("password")
                .roles("USER").build();
        return new MapReactiveUserDetailsService(user);
    }

    @Bean
    protected SecurityWebFilterChain configure(ServerHttpSecurity http) {

        http.authorizeExchange().anyExchange().permitAll().and().formLogin().loginPage("/login")
                .and().csrf().disable();
        return http.build();
    }
}
