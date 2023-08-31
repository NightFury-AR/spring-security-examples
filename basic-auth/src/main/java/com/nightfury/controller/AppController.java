package com.nightfury.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/basic-auth")
public class AppController {
    @RequestMapping("/public")
    public String publicPath() {
        return "Hello World !!!";
    }

    @RequestMapping("/private")
    public String protectedPath() {
        return "You Are Logged Inn !!!";
    }
}
