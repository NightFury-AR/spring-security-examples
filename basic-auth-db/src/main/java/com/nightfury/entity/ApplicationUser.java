package com.nightfury.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Entity
@Getter
@Setter
public class ApplicationUser {

    @Id
    @GeneratedValue(generator = "applicationUserId_generator",strategy = GenerationType.SEQUENCE)
    @SequenceGenerator(sequenceName = "applicationUserId_generator",name = "applicationUserId_generator")
    private Long userId;
    private String name;
    private String password;
    private String email;
    private boolean enabled;
    private String location;
    private List<String> roles;
}
