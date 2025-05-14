package org.example.backendfootvolley.repository;

import org.example.backendfootvolley.model.Tournament;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TournamentRepository extends JpaRepository<Tournament, Long> {
}
