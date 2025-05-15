package org.example.backendfootvolley.repository;

import org.example.backendfootvolley.model.Player;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface PlayerRepository extends JpaRepository<Player, Long> {
    boolean existsByContact_Email(String email);
    Optional<Player> findByContact_Email(String email);
}
