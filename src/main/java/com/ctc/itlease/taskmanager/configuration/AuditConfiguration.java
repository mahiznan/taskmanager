package com.ctc.itlease.taskmanager.configuration;

import com.ctc.itlease.taskmanager.model.User;
import com.ctc.itlease.taskmanager.repository.UserRepository;
import com.ctc.itlease.taskmanager.security.UserPrincipal;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.domain.AuditorAware;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;
import org.springframework.security.authentication.AnonymousAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;

import java.util.Optional;

@Configuration
@EnableJpaAuditing
public class AuditConfiguration {
    private final UserRepository userRepository;

    public AuditConfiguration(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Bean
    public AuditorAware<User> auditProvider() {
        return new SpringSecurityAuditAwareImpl(userRepository);
    }
}

class SpringSecurityAuditAwareImpl implements AuditorAware<User> {
    private final UserRepository userRepository;

    public SpringSecurityAuditAwareImpl(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public Optional<User> getCurrentAuditor() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        if (authentication == null || !authentication.isAuthenticated() || authentication instanceof AnonymousAuthenticationToken) {
            return Optional.empty();
        }
        UserPrincipal userPrincipal = (UserPrincipal) authentication.getPrincipal();
        return userRepository.findById(userPrincipal.getId());
    }
}