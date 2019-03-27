package com.relay.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.*;
import org.springframework.security.config.annotation.web.configuration.*;

@EnableWebSecurity
public class WebSecurityConfig extends WebSecurityConfigurerAdapter{

    /**
     * User role name
     */
    private static final String USER_ROLE = "USER";

    /**
     * Admin role name
     */
    private static final String ADMIN_ROLE = "ADMIN";

    /**
     * User user name
     */
    private static final String USER_USERNAME = "user";

    /**
     * Admin user name
     */
    private static final String ADMIN_USERNAME = "admin";

    /**
     * Config authentication
     * 
     * @param auth
     *            {@link AuthenticationManagerBuilder}
     * @throws Exception
     *             if an error occurs when adding the in memory authentication
     */
    @Autowired
    public void configureGlobal(AuthenticationManagerBuilder auth) throws Exception {

        auth.inMemoryAuthentication().withUser(USER_USERNAME) // #1
                .password("{noop}123").roles(USER_ROLE).and().withUser(ADMIN_USERNAME) // #2
                .password("{noop}321").roles(ADMIN_ROLE, USER_ROLE);


    }
    

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.anonymous();
    }


}
