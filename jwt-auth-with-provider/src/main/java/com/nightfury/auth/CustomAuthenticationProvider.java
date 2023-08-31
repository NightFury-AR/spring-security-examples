package com.nightfury.auth;

import com.nightfury.entity.ApplicationUser;
import com.nightfury.repository.AuthRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class CustomAuthenticationProvider implements AuthenticationProvider {

    @Autowired private AuthRepository authRepository;

    @Autowired private PasswordEncoder passwordEncoder;

    @Override
    public Authentication authenticate(Authentication authentication) throws AuthenticationException {
            return authRepository
                    .findByNameAndEnabled(authentication.getName(), true)
                    .filter( credentials -> passwordEncoder.matches(authentication.getCredentials().toString(),credentials.getPassword()))
                    .map(userData -> new UsernamePasswordAuthenticationToken(userData.getName(),userData.getPassword(),userData.getRoles()))
                    .orElseThrow(() -> new BadCredentialsException("Username not found"));
    }

    @Override
    public boolean supports(Class<?> authentication) {
        return UsernamePasswordAuthenticationToken.class.isAssignableFrom(authentication);
    }

}
