package com.nightfury.auth;

import com.nightfury.entity.ApplicationUser;
import com.nightfury.repository.AuthRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

import java.util.Collections;
import java.util.List;

public class CustomUserDetailsService implements UserDetailsService {

    @Autowired private AuthRepository authRepository;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        List<GrantedAuthority> authorities = Collections.unmodifiableList(List.of(new SimpleGrantedAuthority("USER")));
        User user = null;
        try {
            ApplicationUser existingUser = authRepository.findByNameAndEnabled(username, true);
            if(existingUser == null) {
                throw new UsernameNotFoundException(username+" not found !");
            } else {
                user = new User(username,existingUser.getPassword(),authorities);
            }
        } catch (UsernameNotFoundException e) {
            throw new UsernameNotFoundException(e.getMessage());
        }
        return user;
    }
}
