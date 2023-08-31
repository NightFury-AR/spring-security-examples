package com.nightfury.dto;

import lombok.*;

import java.io.Serializable;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class ApplicationUserDTO implements Serializable {
    private Long userId;
    private String name;
    private String password;
    private String email;
    private boolean enabled;
    private String location;
}
