package com.nightfury.controller;

import com.nightfury.dto.ApplicationUserDTO;
import com.nightfury.dto.AuthRequestDTO;
import com.nightfury.service.AuthService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/basic-auth-db-provider")
public class AppController {

    @Autowired private AuthService authService;
    @Autowired private AuthenticationManager authenticationManager;


    @GetMapping("/public")
    public String publicRoute() {
        return "Hello All, Welcome ";
    }

    @GetMapping("/private")
    public String privateRoute() {
        String user = "privateUser";
        return " Hello " + user;
    }

    @PostMapping("/register")
    public boolean userRegistration(@RequestBody ApplicationUserDTO dto) {
        boolean isRegistered = authService.registerNewUser(dto);
        /*ResponseEntity<Void> success = ResponseEntity.accepted().location(URI.create("/login")).build();
        ResponseEntity<Void> failed = ResponseEntity.badRequest().location(URI.create("/register")).build();*/
        return isRegistered ;
    }

    @PostMapping("/login")
    public String login(@RequestBody AuthRequestDTO requestDTO) {
        Authentication authenticate = authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(requestDTO.getUsername(), requestDTO.getPassword()));
        SecurityContextHolder.getContext().setAuthentication(authenticate);
        return "";
    }
}

