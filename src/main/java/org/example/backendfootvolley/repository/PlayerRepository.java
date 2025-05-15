package org.example.backendfootvolley.repository;

import org.example.backendfootvolley.model.Player;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface PlayerRepository extends JpaRepository<Player, Long> {
    @Query("SELECT p FROM Player p JOIN p.clubs c WHERE c.id = :clubId")
    List<Player> findByClubId(@Param("clubId")Long clubId);
}
