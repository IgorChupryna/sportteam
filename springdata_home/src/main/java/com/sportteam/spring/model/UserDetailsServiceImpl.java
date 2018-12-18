package com.sportteam.spring.model;

import com.sportteam.spring.service.SecurityService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;


import java.util.HashSet;
import java.util.Set;

@Service
public class UserDetailsServiceImpl implements UserDetailsService {
    @Autowired
    private SecurityService securityService;

    @Override
    public UserDetails loadUserByUsername(String login) throws UsernameNotFoundException {
        Security customUser = securityService.getUserByLogin(login);
        if (customUser == null)
            throw new UsernameNotFoundException(login + " not found");

        Set<GrantedAuthority> roles = new HashSet<>();
        roles.add(new SimpleGrantedAuthority(customUser.getRole().toString()));

        return new User(customUser.getLogin(), customUser.getPassword(), roles);
    }
}