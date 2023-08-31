package com.nightfury.dto;

import lombok.*;

import java.io.Serializable;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class AuthRequestDTO implements Serializable {
    private String username;
    private String password;
}
