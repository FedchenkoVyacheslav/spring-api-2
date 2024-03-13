package org.example.repository;

import org.example.domain.Identity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface IdentityRepo extends JpaRepository<Identity, Long> {
    Identity findByUsername(String username);
}
