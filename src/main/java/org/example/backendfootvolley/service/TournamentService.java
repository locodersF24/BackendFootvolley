package org.example.backendfootvolley.service;

import org.example.backendfootvolley.model.Tournament;
import org.example.backendfootvolley.repository.TournamentRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class TournamentService {
    private final TournamentRepository tournamentRepository;

    public TournamentService(TournamentRepository tournamentRepository) {
        this.tournamentRepository = tournamentRepository;
    }

    public List<Tournament> getAllTournaments() {
        return tournamentRepository.findAll();
    }
    public Optional<Tournament> getTournamentById(Long id) {
        return tournamentRepository.findById(id);
    }

}
