package com.nightfury.repository;

import com.nightfury.entity.ApplicationUser;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface AuthRepository extends JpaRepository<ApplicationUser,Long> {
    Optional<ApplicationUser> findByNameAndEnabled(String name, boolean enabled);
}
