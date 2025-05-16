package org.example.backendfootvolley.service;

import lombok.RequiredArgsConstructor;
import org.example.backendfootvolley.dto.CreateTournamentDTO;
import org.example.backendfootvolley.model.Club;
import org.example.backendfootvolley.model.League;
import org.example.backendfootvolley.model.Tournament;
import org.example.backendfootvolley.repository.ClubRepository;
import org.example.backendfootvolley.repository.LeagueRepository;
import org.example.backendfootvolley.repository.TournamentRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@RequiredArgsConstructor
@Service
public class TournamentService {

    private final TournamentRepository tournamentRepository;
    private final ClubRepository clubRepository;
    private final LeagueRepository leagueRepository;



    public List<Tournament> getAllTournaments() {
        return tournamentRepository.findAll();
    }

    public Optional<Tournament> getTournamentById(Long id) {
        return tournamentRepository.findById(id);
    }



    public Tournament createTournament(CreateTournamentDTO dto) {
        Tournament tournament = new Tournament();

        tournament.setPointsAtStake(dto.pointsAtStake);
        tournament.setQualificationStartDate(dto.qualificationStartDate);
        tournament.setQualificationEndDate(dto.qualificationEndDate);
        tournament.setFinalsStartDate(dto.finalsStartDate);
        tournament.setFinalsEndDate(dto.finalsEndDate);
        tournament.setPrizeMoney(dto.prizeMoney);

        // we will show a list of all clubs in frontend(drop down, selection ), then admin can choose
        // the club where club id is attached as value which will be passed to the backend
        Club hostClub = clubRepository.findById(dto.hostClubId)
                .orElseThrow(() -> new IllegalArgumentException("Invalid host club ID"));
        League league = leagueRepository.findById(dto.leagueId)
                .orElseThrow(() -> new IllegalArgumentException("Invalid league ID"));

        tournament.setHost(hostClub);
        tournament.setLeague(league);


        tournament.setCity(hostClub.getCity());


        tournament.setCurrency("EUR");

        return tournamentRepository.save(tournament);
    }
}
