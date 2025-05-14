package org.example.backendfootvolley.repository;

import org.example.backendfootvolley.model.Match;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MatchRepository extends JpaRepository<Match, Long> {
}
