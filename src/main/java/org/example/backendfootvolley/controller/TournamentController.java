package org.example.backendfootvolley.controller;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.example.backendfootvolley.dto.CreateTournamentDTO;
import org.example.backendfootvolley.model.Tournament;
import org.example.backendfootvolley.service.TournamentService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequiredArgsConstructor
@RestController
@RequestMapping("/api/tournaments")
public class TournamentController {

    private final TournamentService tournamentService;

    @GetMapping
    public ResponseEntity<List<Tournament>> viewAllTournaments() {
        return ResponseEntity.ok(tournamentService.getAllTournaments());
    }

    @GetMapping("/{id}")
    public ResponseEntity<Tournament> getTournamentById(@PathVariable Long id) {
        return tournamentService.getTournamentById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }


    @PostMapping
    @PreAuthorize("hasAuthority('ADMIN')")
    public ResponseEntity<?> createTournament(@Valid @RequestBody CreateTournamentDTO dto) {
        Tournament created = tournamentService.createTournament(dto);

        if (created != null && created.getId() != null) {
            return ResponseEntity.ok("Tournament created successfully");
        } else {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body("Failed to create tournament");
        }

    }


}
