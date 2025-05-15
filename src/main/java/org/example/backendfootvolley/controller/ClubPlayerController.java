package org.example.backendfootvolley.controller;

import lombok.RequiredArgsConstructor;
import org.example.backendfootvolley.dto.PlayerDTO;
import org.example.backendfootvolley.model.Club;
import org.example.backendfootvolley.model.Player;
import org.example.backendfootvolley.model.UserAccount;
import org.example.backendfootvolley.repository.ContactRepository;
import org.example.backendfootvolley.repository.PlayerRepository;
import org.example.backendfootvolley.repository.UserAccountRepository;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;
import java.util.Optional;
import java.util.Set;

@PreAuthorize("hasAuthority('SCOPE_CLUB')")
@RequiredArgsConstructor
@RestController
@RequestMapping("/api/clubs/players")
public class ClubPlayerController {

    private final ContactRepository contactRepository;
    private final PlayerRepository playerRepository;
    private final UserAccountRepository userAccountRepository;

    @GetMapping
    public ResponseEntity<Set<Player>> getOwnPlayers(Principal principal) {
        return userAccountRepository
                .findByContact_Email(principal.getName())
                .map(UserAccount::getClub)
                .map(Club::getPlayers)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @GetMapping("/{id}")
    public ResponseEntity<Player> getOwnPlayerById(Principal principal, @PathVariable Long id) {
        Set<Player> players = userAccountRepository
                .findByContact_Email(principal.getName())
                .get()
                .getClub()
                .getPlayers();
        for (Player player : players) {
            if (player.getId().equals(id)) {
                return ResponseEntity.ok(player);
            }
        }
        return ResponseEntity.notFound().build();
    }

    @PostMapping
    public ResponseEntity<Void> createPlayer(Principal principal, @RequestBody Player player) {
        if (player.getContact() == null || player.getContact().getEmail() == null) {
            return ResponseEntity.badRequest().build();
        }
        if (playerRepository.existsByContact_Email(player.getContact().getEmail())) {
            return new ResponseEntity<>(HttpStatus.CONFLICT);
        }
        Club club = userAccountRepository.findByContact_Email(principal.getName()).get().getClub();
        player.setClubs(Set.of(club));
        contactRepository
                .findByEmail(player.getContact().getEmail())
                .ifPresent(value -> player.getContact().setId(value.getId()));
        playerRepository.save(player);
        return new ResponseEntity<>(HttpStatus.CREATED);
    }

    @PostMapping("/{id}")
    public ResponseEntity<Void> addPlayer(Principal principal, @PathVariable Long id) {
        Optional<Player> optional = playerRepository.findById(id);
        if (optional.isEmpty()) {
            return ResponseEntity.notFound().build();
        }
        Player player = optional.get();
        Club club = userAccountRepository
                .findByContact_Email(principal.getName())
                .get()
                .getClub();
        player.getClubs().add(club);
        playerRepository.save(player);
        return new ResponseEntity<>(HttpStatus.CREATED);
    }

    @PutMapping
    public ResponseEntity<Player> editPlayer(Principal principal, @RequestBody PlayerDTO updatedPlayer) {
        Optional<Player> optional = playerRepository.findById(updatedPlayer.getId());
        if (optional.isEmpty()) {
            return ResponseEntity.notFound().build();
        }

        Player existingPlayer = optional.get();
        Club club = userAccountRepository
                .findByContact_Email(principal.getName())
                .get()
                .getClub();
        if (!existingPlayer.getClubs().contains(club)) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }

        existingPlayer.getContact().setFirstName(updatedPlayer.getFirstName());
        existingPlayer.getContact().setLastName(updatedPlayer.getLastName());
        existingPlayer.getContact().setEmail(updatedPlayer.getEmail());
        existingPlayer.setNickName(updatedPlayer.getNickName());

        Player player = playerRepository.save(existingPlayer);

        return ResponseEntity.ok(player);
    }


    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletePlayer(Principal principal, @PathVariable Long id) {
        Optional<Player> optional = playerRepository.findById(id);
        if (optional.isEmpty()) {
            return ResponseEntity.notFound().build();
        }
        Player player = optional.get();
        Club club = userAccountRepository
                .findByContact_Email(principal.getName())
                .get()
                .getClub();
        if (!player.getClubs().contains(club)) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        if (player.getClubs().size() > 1) {
            player.getClubs().remove(club);
            playerRepository.save(player);
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
        playerRepository.delete(player);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

}
