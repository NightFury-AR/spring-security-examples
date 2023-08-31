package com.nightfury.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.nightfury.constants.ROLE;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import org.springframework.security.core.GrantedAuthority;

import java.util.Collection;
import java.util.List;

@Entity
@Table(name = "application_role")
@Getter
@Setter
@ToString
public class UserRole implements GrantedAuthority {

    @Id
    @GeneratedValue(generator = "user_role_sequence" , strategy = GenerationType.SEQUENCE)
    @SequenceGenerator(name = "user_role_sequence")
    //@Column(name = "role_id")
    private long roleId;
    @Enumerated(EnumType.STRING)
    private ROLE role;
    @ManyToMany(cascade = CascadeType.ALL)
    @JoinTable(
            name = "role_privilege",
            joinColumns = @JoinColumn(
                    name = "role_id"
            ),
            inverseJoinColumns = @JoinColumn(
                    name = "prev_id",
                    referencedColumnName = "privilegeId"
            )
    )
    private List<UserPrivilege> userPrivileges;
    @ManyToMany(mappedBy = "roles")
    @JsonIgnore
    private Collection<ApplicationUser> users;

    @Override
    public String getAuthority() {
        return this.role.name();
    }
}
