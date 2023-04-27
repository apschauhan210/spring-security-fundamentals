package com.springsecurity.userdetailsserviceimplementation.services;

import com.springsecurity.userdetailsserviceimplementation.entities.User;
import com.springsecurity.userdetailsserviceimplementation.repositories.UserRepository;
import com.springsecurity.userdetailsserviceimplementation.security.SecurityUserDetails;
import lombok.AllArgsConstructor;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@AllArgsConstructor
public class JPAUserDetailsService implements UserDetailsService {

    private final UserRepository userRepository;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Optional<User> u = userRepository.findUserByUserName(username);

        return u.map(SecurityUserDetails::new)
                .orElseThrow(() -> new UsernameNotFoundException("User not found with username: " + username));
    }
}
