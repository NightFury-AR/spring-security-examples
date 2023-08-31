package com.nightfury.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.nightfury.constants.Privilege;
import jakarta.persistence.*;
import lombok.*;

import java.util.Collection;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "application_privilege")
@ToString
public class UserPrivilege {
    @Id
    @GeneratedValue(generator = "privilege_generator",strategy = GenerationType.SEQUENCE)
    @SequenceGenerator(sequenceName = "privilege_generator",name = "privilege_generator")
    private Long privilegeId;
    @Enumerated(EnumType.STRING)
    private Privilege privilege;
    @ManyToMany(mappedBy = "userPrivileges")
    @JsonIgnore
    private Collection<UserRole> roles;
}
