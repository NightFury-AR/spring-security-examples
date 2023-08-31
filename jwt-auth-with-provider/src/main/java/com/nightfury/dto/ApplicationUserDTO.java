package com.nightfury.dto;

import lombok.*;

import java.io.Serializable;
import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class ApplicationUserDTO implements Serializable {
    private String name;
    private String email;
    private String password;
    private String location;
    private boolean enabled;
    private List<RoleDTO> roles;
}
