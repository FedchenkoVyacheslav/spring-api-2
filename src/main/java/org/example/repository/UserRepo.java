package org.example.repository;

import org.example.domain.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface UserRepo extends JpaRepository<User, Long> {
    User findByEmail(String email);

    List<User> findByEmailContainingIgnoreCase(String email);
}
