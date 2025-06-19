package com.filizzola.lawyermanager.repositories;

import com.filizzola.lawyermanager.models.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<User, String> {
    Optional<User> findByUsernameOrEmail(String username, String email);

    Optional<User> findFirstByUsername(String username);
    Optional<User> findFirstByEmail(String email);
}
