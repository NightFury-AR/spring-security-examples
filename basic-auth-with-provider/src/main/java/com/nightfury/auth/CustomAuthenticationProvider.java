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

@Service
public class CustomAuthenticationProvider implements AuthenticationProvider {

    @Autowired private AuthRepository authRepository;

    @Autowired private PasswordEncoder passwordEncoder;

    @Override
    public Authentication authenticate(Authentication authentication) throws AuthenticationException {
            ApplicationUser user = authRepository.findByNameAndEnabled(authentication.getName(), true);
            if (user == null) {
                throw new BadCredentialsException("No User found for this name !");
            }
            if (passwordEncoder.matches(authentication.getCredentials().toString(),user.getPassword())) {
                List<GrantedAuthority> authorities = new ArrayList<>();
                authorities.add(new SimpleGrantedAuthority("USER"));
                return new UsernamePasswordAuthenticationToken(user.getName(),user.getPassword(),authorities);
            } else {
                throw new BadCredentialsException("Password is Invalid !!!");
            }
    }

    @Override
    public boolean supports(Class<?> authentication) {
        return UsernamePasswordAuthenticationToken.class.isAssignableFrom(authentication);
    }
}
