package com.beernetwork.web.app.dao;

import org.springframework.context.annotation.Bean;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

public class AuthorizationDAO implements UserDetailsService {
    private final UserInteractionDAO userInteractionDAO;

    public AuthorizationDAO(UserInteractionDAO UserInteractionDAO) {
        this.userInteractionDAO = UserInteractionDAO;
    }

    @Override
    public UserDetails loadUserByEmail(String email) throws UsernameNotFoundException {
        web.app.model.User credential = UserInteractionDAO.selectEmail(email);

        UserDetails user = User
                .withUsername(credential.getEmail())
                .password(credential.getPassword())
                .roles(credential.getRole())
                .build();
        return user;
    }
    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }
}
