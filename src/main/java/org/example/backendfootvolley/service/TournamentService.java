package org.example.backendfootvolley.service;

import lombok.RequiredArgsConstructor;
import org.example.backendfootvolley.model.City;
import org.example.backendfootvolley.model.Club;
import org.example.backendfootvolley.model.League;
import org.example.backendfootvolley.model.Tournament;
import org.example.backendfootvolley.repository.CityRepository;
import org.example.backendfootvolley.repository.ClubRepository;
import org.example.backendfootvolley.repository.LeagueRepository;
import org.example.backendfootvolley.repository.TournamentRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@RequiredArgsConstructor
@Service
public class TournamentService {

    private final CityRepository cityRepository;
    private final TournamentRepository tournamentRepository;
    private final LeagueRepository leagueRepository;

    public List<Tournament> getAllTournaments() {
        return tournamentRepository.findAll();
    }

    public Optional<Tournament> getTournamentById(Long id) {
        return tournamentRepository.findById(id);
    }

    public void createTournament(Tournament tournament) {
        Optional<League> league = leagueRepository.findFirstByCategoryAndSeasonYear(tournament.getLeague().getCategory(), tournament.getLeague().getSeasonYear());
        if (league.isPresent()) {
            tournament.setLeague(league.get());
        } else {
            League savedLeague = leagueRepository.save(tournament.getLeague());
            tournament.setLeague(savedLeague);
        }
        if (tournament.getCity().getId() == null) {
            City city = cityRepository.save(tournament.getCity());
            tournament.setCity(city);
        }
        tournamentRepository.save(tournament);
    }

}
