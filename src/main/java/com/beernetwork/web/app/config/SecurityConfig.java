package com.beernetwork.web.app.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;

@Configuration
@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter {
    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http
                .csrf().disable();
        http
                .authorizeRequests()
                .antMatchers("/v2/api-docs").hasRole("ADMIN")
                .antMatchers("/swagger-ui.html").hasRole("ADMIN")
                .antMatchers("/swagger-ui.html/**").hasRole("ADMIN")
                .antMatchers("/swagger-resources/**").hasRole("ADMIN")
                .antMatchers("/webjars/springfox-swagger-ui/**").hasRole("ADMIN")
                .antMatchers("/api/select/admin/**").hasRole("ADMIN")
                .antMatchers("/api/select/user/by/SelectUser").hasAnyRole("USER", "ADMIN")
                .antMatchers("/api/select/user/change/info").hasAnyRole("USER", "ADMIN")
                .antMatchers("/api/select/user/change/password").hasAnyRole("USER", "ADMIN")
                .antMatchers("/api/select/user/check/email").permitAll()
                .antMatchers("/api/select/user/by/CreateNew").permitAll()
                .antMatchers("/api/select/user/get/news").permitAll()
                .antMatchers("/css/**").permitAll()
                .antMatchers("/ws/**").permitAll()
                .antMatchers("/images/**").permitAll()
                .antMatchers("/photo-users/**").hasAnyRole("USER", "ADMIN")
                .antMatchers("/js/**").permitAll()
                .antMatchers("/about.html").permitAll()
                .antMatchers("/adminPage.html").hasRole("ADMIN")
                .antMatchers("/beermap.html").permitAll()
                .antMatchers("/chat.html").hasAnyRole("USER", "ADMIN")
                .antMatchers("/editMyPage.html").hasAnyRole("USER", "ADMIN")
                .antMatchers("/index.html").permitAll()
                .antMatchers("/news.html").permitAll()
                .antMatchers("/register.html").permitAll()
                .antMatchers("/favicon.ai").permitAll()
                .antMatchers("/favicon.ico").permitAll()
                .antMatchers("/users.html").hasAnyRole("USER", "ADMIN")
                .anyRequest().authenticated()
                .and()
                .formLogin()
                .loginPage("/login.html").permitAll()
                .loginProcessingUrl("/login").permitAll()
                .defaultSuccessUrl("/index.html",true);

    }
}