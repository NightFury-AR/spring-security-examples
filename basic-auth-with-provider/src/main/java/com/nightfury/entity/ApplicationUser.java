package com.nightfury.entity;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.JoinTable;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.SequenceGenerator;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.util.List;

@Entity
@Getter
@Setter
@Table(name = "application_user")
@ToString
public class ApplicationUser {

    @Id
    @GeneratedValue(generator = "application_user_sequence",strategy = GenerationType.SEQUENCE)
    @SequenceGenerator(sequenceName = "application_user_sequence",name = "application_user_sequence")
    private Long userId;
    private String name;
    private String email;
    private String password;
    private String location;
    private boolean enabled;
    @ManyToMany(cascade = CascadeType.ALL)
    @JoinTable(
            name = "user_role",
            joinColumns = @JoinColumn(
                    name = "user_id",
                    referencedColumnName = "userId"
            ),
            inverseJoinColumns = @JoinColumn(
                    name = "role_id",
                    referencedColumnName = "roleId"
            )
    )
    private List<UserRole> roles;
}
