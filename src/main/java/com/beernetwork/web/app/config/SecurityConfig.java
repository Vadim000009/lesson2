package com.beernetwork.web.app.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;

@Configuration
@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter {
    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http
                .csrf().disable();
//                .authorizeRequests().anyRequest().authenticated()
//                .and().httpBasic()
//                .and().sessionManagement().disable();
        http
                .authorizeRequests().antMatchers("/**").permitAll()
                .antMatchers("/").permitAll()
                .antMatchers("/csrf").permitAll()
                .antMatchers("/v2/api-docs").permitAll()
                .antMatchers("/swagger-ui.html").permitAll()
                .antMatchers("/swagger-ui.html/**").permitAll()
                .antMatchers("/swagger-resources/**").permitAll()
                .antMatchers("/webjars/springfox-swagger-ui/**").permitAll()
                .antMatchers("/messanges.html").hasRole("USER")
                .antMatchers("/users.html").hasRole("USER")
                .antMatchers("/adminPage.htnl").hasRole("ADMIN")
                .antMatchers().authenticated()
                .and()
                .formLogin()
                .defaultSuccessUrl("/messages.html");

    }

    @Bean
    @Override
    public UserDetailsService userDetailsService() {
        UserDetails userDetails = User.withDefaultPasswordEncoder()
                .username("email")
                .password("password")
                .roles("USER")
                .build();
        return  new InMemoryUserDetailsManager();
    }
}