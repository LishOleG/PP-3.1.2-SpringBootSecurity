package ru.kata.spring.boot_security.demo.springboot_security.service;


import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import ru.kata.spring.boot_security.demo.springboot_security.model.Role;
import ru.kata.spring.boot_security.demo.springboot_security.model.User;

import java.util.Collection;
import java.util.stream.Collectors;

@Service
public class UserDetailsServiceImpl implements UserDetailsService {
    private final UserService userService;

    public UserDetailsServiceImpl(UserService userService) {
        this.userService = userService;
    }

    public User findUserByName(String username) {
        return userService.getUserByNameWithRoles(username);
    }

    @Override
    public UserDetails loadUserByUsername(String name) throws UsernameNotFoundException {
        User user = findUserByName(name);
        if (user == null) {
            throw new UsernameNotFoundException(String.format("User '%s' not found", name));
        }
        return new org.springframework.security.core.userdetails.User(user.getUsername(), user.getPassword(), mapRoles(user.getRoles()));
    }

    private Collection<? extends GrantedAuthority> mapRoles(Collection<Role> roles) {
        return roles
                .stream()
                .map(u -> new SimpleGrantedAuthority(u.getRole()))
                .collect(Collectors.toList());
    }
}
