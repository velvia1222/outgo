package com.outgo.service;

import java.util.Optional;

import com.outgo.repository.UserRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class UserService implements UserDetailsService {
    @Autowired
    private UserRepository userRepository;

    @Override
    public UserDetails loadUserByUsername(final String username) {
        return Optional.ofNullable(userRepository.findByUsername(username))
                .orElseThrow(
                        () -> new UsernameNotFoundException("username: " + username));
    }
}
