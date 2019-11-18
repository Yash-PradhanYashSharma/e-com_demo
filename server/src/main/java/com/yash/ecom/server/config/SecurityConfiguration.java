package com.yash.ecom.server.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.web.cors.CorsConfiguration;

import java.util.Collections;

@Configuration
@EnableWebSecurity
public class SecurityConfiguration extends WebSecurityConfigurerAdapter {

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.inMemoryAuthentication()
                .withUser("user").password("{noop}user").roles("USER")
                .and()
                .withUser("admin").password("{noop}admin").roles("USER", "ADMIN");
    }


    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.
                csrf().disable()
                .authorizeRequests().antMatchers("/**").permitAll().anyRequest().authenticated()
                .and().httpBasic()
                .and().cors()
                .configurationSource(request -> {
                            CorsConfiguration config = new CorsConfiguration();
                            config.setAllowedHeaders(Collections.singletonList("*"));
                            config.setAllowedMethods(Collections.singletonList("*"));
                            config.addAllowedOrigin("*");
                            config.setAllowCredentials(true);
                            return config;
                        }
                );
    }

}
