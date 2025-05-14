package org.example.backendfootvolley.repository;

import org.example.backendfootvolley.model.League;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

    @Repository
    public interface LeagueRepository extends JpaRepository<League, Long> {
    }


