package com.nightfury.controller;

import com.nightfury.service.AuthService;
import com.nightfury.dto.ApplicationUserDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/basic-auth-db")
public class AppController {

    @Autowired private AuthService authService;

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
}

