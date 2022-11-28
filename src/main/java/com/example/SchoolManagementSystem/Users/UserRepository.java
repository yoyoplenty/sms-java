package com.example.SchoolManagementSystem.Users;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;
import java.util.UUID;

@Repository
@Transactional(readOnly = true)
public interface UserRepository extends JpaRepository<User, UUID> {
    User findByEmail(String email);

    User findUserById(UUID id);

    Optional<User> findUserByEmail(String email);

    Optional<User> findUserByConfirmToken(String token);

    Optional<User> findUserByResetToken(String accessToken);
}
