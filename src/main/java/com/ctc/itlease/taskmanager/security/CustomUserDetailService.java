package com.ctc.itlease.taskmanager.security;

import com.ctc.itlease.taskmanager.model.User;
import com.ctc.itlease.taskmanager.repository.UserRepository;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class CustomUserDetailService implements UserDetailsService {
    private final UserRepository userRepository;

    public CustomUserDetailService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public UserDetails loadUserByUsername(String usernameOrEmail) throws UsernameNotFoundException {
        User user = userRepository.findByUsernameOrEmail(usernameOrEmail, usernameOrEmail).orElseThrow(() -> new UsernameNotFoundException("User not found with username or email"));
        return UserPrincipal.create(user);
    }

    UserDetails loadUserById(Long id) {
        User user = userRepository.getUserWithRoles(id).orElseThrow(() -> new UsernameNotFoundException("User not found with id :" + id));
        return UserPrincipal.create(user);
    }


}
