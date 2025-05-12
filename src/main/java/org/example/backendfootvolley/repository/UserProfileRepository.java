package org.example.backendfootvolley.repository;

import org.example.backendfootvolley.model.UserProfile;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UserProfileRepository extends JpaRepository<UserProfile, Long> {
    boolean existsByUsername(String username);
    Optional<UserProfile> findByUsername(String username);
}
