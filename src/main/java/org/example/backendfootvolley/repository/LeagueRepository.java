package org.example.backendfootvolley.repository;

import org.example.backendfootvolley.model.Category;
import org.example.backendfootvolley.model.League;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface LeagueRepository extends JpaRepository<League, Integer> {
    Optional<League> findFirstByCategoryAndSeasonYear(Category category, String seasonYear);
}
