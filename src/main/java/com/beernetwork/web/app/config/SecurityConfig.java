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
//                .authorizeRequests()
//                .antMatchers("/v2/api-docs").hasRole("ADMIN")
//                .antMatchers("/swagger-ui.html").hasRole("ADMIN")
//                .antMatchers("/swagger-ui.html/**").hasRole("ADMIN")
//                .antMatchers("/swagger-resources/**").hasRole("ADMIN")
//                .antMatchers("/webjars/springfox-swagger-ui/**").hasRole("ADMIN")
//                .antMatchers("/adminPage.html").hasRole("ADMIN")
//                .antMatchers("//api/select/admin/**").hasRole("ADMIN")
//                .antMatchers("/index.html").permitAll()
//                .antMatchers("/beermap.html").permitAll()
//                .antMatchers("/news.html").permitAll()
//                .antMatchers("/register.html").permitAll()
//                .antMatchers("/about.html").permitAll()
//                .antMatchers("/api/select/user/by/CreateNew").permitAll()
//                .antMatchers("/api/select/user/by/CreateNew").permitAll()
//                .antMatchers("/css/**").permitAll()
//                .antMatchers("/images/**").permitAll()
//                .antMatchers("/images-about/**").permitAll()
//                .antMatchers("/js/**").permitAll()
//                .antMatchers("/messanges.html").hasAnyRole("USER", "ADMIN")
//                .antMatchers("/users.html").hasAnyRole("USER", "ADMIN")
//                .antMatchers("/myprofile.html").hasAnyRole("USER", "ADMIN")
//                .anyRequest().authenticated()
//                .and()
//                .formLogin()
//                .loginPage("/login.html").permitAll()
//                .loginProcessingUrl("/login").permitAll()
//                .defaultSuccessUrl("/messages.html",true);
                .authorizeRequests()
                .antMatchers("/v2/api-docs").permitAll()
                .antMatchers("/swagger-ui.html").permitAll()
                .antMatchers("/swagger-ui.html/**").permitAll()
                .antMatchers("/swagger-resources/**").permitAll()
                .antMatchers("/webjars/springfox-swagger-ui/**").permitAll();

    }
}