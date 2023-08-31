package com.nightfury.repository;

import com.nightfury.entity.ApplicationUser;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AuthRepository extends JpaRepository<ApplicationUser,Long> {
    ApplicationUser findByNameAndEnabled(String name, boolean enabled);
}
