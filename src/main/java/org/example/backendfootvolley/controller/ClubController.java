package org.example.backendfootvolley.controller;

import lombok.RequiredArgsConstructor;
import org.example.backendfootvolley.dto.NewClubUserAccount;
import org.example.backendfootvolley.model.*;
import org.example.backendfootvolley.service.ClubService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;
import java.util.List;
import java.util.Set;

@RequiredArgsConstructor
@RestController
@RequestMapping("/api/clubs")
public class ClubController {

    private final ClubService clubService;

    @GetMapping
    public ResponseEntity<List<Club>> getAllClubs() {
        return ResponseEntity.ok(clubService.getAllClubs());
    }

    @PreAuthorize("hasAuthority('SCOPE_ADMIN')")
    @GetMapping("/{id}/players")
    public ResponseEntity<Set<Player>> getPlayersByClub(@PathVariable Long id) {
        Set<Player> players = clubService.getPlayersByClubId(id);
        return ResponseEntity.ok(players);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Club> getById(@PathVariable Long id) {
        return clubService
                .getClubById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @PutMapping
    public ResponseEntity<Club> editClubInfo(Principal principal, @RequestBody Club club) {
        Club savedClub = clubService.updateClub(principal.getName(), club);
        if (savedClub == null) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(savedClub);
    }


    @PreAuthorize("hasAuthority('SCOPE_ADMIN')")
    @PostMapping
    public ResponseEntity<String> register(@RequestBody NewClubUserAccount newClubUserAccount) {
        String response = clubService.register(newClubUserAccount);
        if (response.equals("Created")) {
            return new ResponseEntity<>(HttpStatus.CREATED);
        }
        if (response.startsWith("Conflict: ")) {
            return new ResponseEntity<>(response.substring(11), HttpStatus.CONFLICT);
        }
        return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);
    }

    @PreAuthorize("hasAuthority('SCOPE_ADMIN')")
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteClub(@PathVariable Long id) {
        if (!clubService.deleteClub(id)) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.noContent().build();
    }

}
