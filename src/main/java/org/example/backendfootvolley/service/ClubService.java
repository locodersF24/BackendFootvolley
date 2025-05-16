package org.example.backendfootvolley.service;

import lombok.RequiredArgsConstructor;
import org.example.backendfootvolley.model.Club;
import org.example.backendfootvolley.model.Player;
import org.example.backendfootvolley.repository.ClubRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.Set;

@RequiredArgsConstructor
@Service
public class ClubService {

    private final ClubRepository clubRepository;

    public List<Club> getAllClubs() {
        return clubRepository.findAll();
    }

    public Optional<Club>getClubById(Long clubId) {
        return clubRepository.findById(clubId);

    }
    public Set<Player> getPlayersByClubId(Long clubId) {
        return clubRepository.findById(clubId).get().getPlayers();
    }

    public boolean deleteClub(Long id) {
        boolean exists = clubRepository.existsById(id);
        clubRepository.deleteById(id);
        return exists;
    }
}
