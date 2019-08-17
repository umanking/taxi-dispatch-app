package com.example.taxi.config;

import com.example.taxi.domain.user.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class TaxiAuthenticationProvider extends DaoAuthenticationProvider {

    @Autowired
    private UserService userService;

    @Autowired
    private PasswordEncoder bCryptPasswordEncoder;

    @Autowired
    private UserDetailsService userDetailsService;

    public TaxiAuthenticationProvider(UserService userService, UserDetailsService userDetailsService, BCryptPasswordEncoder bCryptPasswordEncoder) {
        this.userService = userService;
        this.bCryptPasswordEncoder = bCryptPasswordEncoder;
        this.userDetailsService = userDetailsService;

        this.setUserDetailsService(userDetailsService);
        this.setPasswordEncoder(bCryptPasswordEncoder);
        this.setHideUserNotFoundExceptions(false);
    }

    protected void additionalAuthenticationChecks(UserDetails userDetails, UsernamePasswordAuthenticationToken authentication) throws AuthenticationException {
        SessionUser sessionUser = (SessionUser) userDetails;
        String presentPassword = (String) authentication.getCredentials();

        if (!bCryptPasswordEncoder.matches(presentPassword, userDetails.getPassword())) {
            throw new RuntimeException("bad credential");
        }
    }
}
