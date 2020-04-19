package com.beernetwork.web.app.dao;

import com.beernetwork.web.app.api.request.UserByEmailRequest;
import org.springframework.context.annotation.Bean;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class AuthorizationDAO implements UserDetailsService {
    private final UserInteractionDAO userInteractionDAO;

    public AuthorizationDAO(UserInteractionDAO UserInteractionDAO) {
        this.userInteractionDAO = UserInteractionDAO;
    }

    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        com.beernetwork.web.app.model.AuthorizationUser userFromDb = UserByEmailRequest.selectUserByEmail(email);

        UserDetails userDetails = User
                .withUsername(userFromDb.getEmail())
                .password(userFromDb.getPassword())
                .roles(userFromDb.getRole())
                .build();
        return userDetails;
    }
    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }
}
