package com.relay.config;

// @EnableWebSecurity
public class WebSecurityConfig
// extends WebSecurityConfigurerAdapter
{

    // @Bean
    // public UserDetailsService userDetailsService() {
    //
    // InMemoryUserDetailsManager manager = new InMemoryUserDetailsManager();
    // manager.createUser(User.withUsername("user").password("password").roles("USER").build());
    // manager.createUser(
    // User.withUsername("admin").password("password").roles("USER", "ADMIN").build());
    // return manager;
    // }
    //
    // @Autowired
    // public void configureGlobal(AuthenticationManagerBuilder auth) throws Exception {
    //
    // auth.inMemoryAuthentication().withUser("user") // #1
    // .password("password").roles("USER").and().withUser("admin") // #2
    // .password("password").roles("ADMIN", "USER");
    // }
    //
    // @Override
    // protected void configure(HttpSecurity http) throws Exception {
    //
    // http.authorizeRequests().anyRequest().permitAll().and().formLogin().loginPage("/login")
    // .failureUrl("/login-error").and().csrf().disable();
    // }
}
