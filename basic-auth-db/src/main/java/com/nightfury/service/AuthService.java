package com.nightfury.service;

import com.nightfury.dto.ApplicationUserDTO;
import com.nightfury.entity.ApplicationUser;
import com.nightfury.mapper.ApplicationUserMapper;
import com.nightfury.repository.AuthRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@Slf4j
public class AuthService {
    @Autowired private AuthRepository authRepository;
    @Autowired private ApplicationUserMapper userMapper;

    @Autowired private PasswordEncoder passwordEncoder;
    public boolean registerNewUser(ApplicationUserDTO applicationUserDTO) {
        log.info("USER"+applicationUserDTO.toString());
        ApplicationUser entity = userMapper.toEntity(applicationUserDTO);
        entity.setPassword(passwordEncoder.encode(entity.getPassword()));
        ApplicationUser saved = authRepository.save(entity);
        return saved != null;
    }


}
