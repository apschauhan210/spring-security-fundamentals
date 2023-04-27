package com.understanding.springSecurity1.services;

import com.understanding.springSecurity1.entities.User;
import com.understanding.springSecurity1.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

import java.util.Optional;

public class JPAUserDetailsService implements UserDetailsService {

    @Autowired
    private UserRepository userRepository;

//    @Autowired
//    public JPAUserDetailsService(UserRepository userRepository) {
//        this.userRepository = userRepository;
//    }

    @Override
    public UserDetails loadUserByUsername(String username){

//        System.out.println(username);

        Optional<User> user = userRepository.findUserByUsername(username);

//        System.out.println(user.isPresent());
//        System.out.println(user.isEmpty());
//        System.out.println(user);
//        System.out.println(user.get());

        User user1 = user.orElseThrow(() -> new UsernameNotFoundException("username does not exist"));

        return new SecurityUser(user1);
    }


}
