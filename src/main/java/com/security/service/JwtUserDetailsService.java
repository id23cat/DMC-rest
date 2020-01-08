package com.security.service;

import static java.util.Collections.emptyList;

import java.util.ArrayList;
import java.util.Collections;

import com.security.controller.auth.dto.UserDTO;
import com.security.controller.auth.model.UserModel;
import com.security.controller.auth.repo.UserManager;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class JwtUserDetailsService implements UserDetailsService {

    @Autowired
    private UserManager userManager;

    @Autowired
    private PasswordEncoder bcryptEncoder;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        userManager.findByUsername(username);
        if (userManager.findByUsername(username) != null) {
            return userManager.findByUsername(username);
        } else {
            throw new UsernameNotFoundException("User not found with username: " + username);
        }
    }

    public UserModel save(UserDTO user) {
        UserModel newUser = new UserModel(user.getUsername(), bcryptEncoder.encode(user.getPassword()), emptyList());
        return userManager.save(newUser);
    }

}