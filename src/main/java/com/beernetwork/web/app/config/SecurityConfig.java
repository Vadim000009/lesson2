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

                .antMatchers("/about.html").permitAll()
                .antMatchers("/home.html").permitAll()
                .antMatchers("/login.html").permitAll()

                .antMatchers("/superpage.html").hasRole("ADMIN")
                .antMatchers("/orderandcontracts.html").hasAnyRole("USER_Fin")
                .antMatchers("/artcouncils.html").hasAnyRole("USER_Art")
                .antMatchers("/inventory.html").hasAnyRole("USER_Inv")
                .antMatchers("/employee.html").hasAnyRole("USER_Emp")
                .antMatchers("/improvingquality.html").hasAnyRole("USER_Qua")
                .antMatchers("/favicon.ico").permitAll()
                .anyRequest().authenticated()
                .and()
                .formLogin()
                .loginPage("/login.html").permitAll()
                .loginProcessingUrl("/login").permitAll()
                .defaultSuccessUrl("/news.html",true);  // Исправить

    }
}