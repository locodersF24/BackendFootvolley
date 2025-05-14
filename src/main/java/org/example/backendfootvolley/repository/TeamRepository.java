package org.example.backendfootvolley.repository;

import org.example.backendfootvolley.model.Team;
import org.springframework.data.jpa.repository.JpaRepository;



public interface TeamRepository extends JpaRepository<Team, Long> {
}

