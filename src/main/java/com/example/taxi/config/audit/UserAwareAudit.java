package com.example.taxi.config.audit;

import org.springframework.context.annotation.Configuration;
import org.springframework.data.domain.AuditorAware;

import java.util.Optional;

@Configuration
public class UserAwareAudit implements AuditorAware {
    @Override
    public Optional getCurrentAuditor() {

        // TODO Spring Security를 통한 구현

        /*
           Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
            if (authentication == null || !authentication.isAuthenticated()) {
                return null;
            }
            return ((MyUserDetails) authentication.getPrincipal()).getUser();

         */

        return Optional.of("andrew");
    }
}
