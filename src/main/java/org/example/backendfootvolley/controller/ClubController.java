package org.example.backendfootvolley.controller;

import lombok.RequiredArgsConstructor;
import org.example.backendfootvolley.dto.ClubDTO;
import org.example.backendfootvolley.dto.NewClubUserAccount;
import org.example.backendfootvolley.model.*;
import org.example.backendfootvolley.repository.ClubRepository;
import org.example.backendfootvolley.repository.ContactRepository;
import org.example.backendfootvolley.repository.NationalFederationRepository;
import org.example.backendfootvolley.repository.UserAccountRepository;
import org.example.backendfootvolley.service.ClubService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;
import java.util.List;
import java.util.Optional;
import java.util.Set;

@RequiredArgsConstructor
@RestController
@RequestMapping("/api/clubs")
public class ClubController {

    private final ClubService clubService;
    private final ClubRepository clubRepository;
    private final ContactRepository contactRepository;
    private final UserAccountRepository userAccountRepository;
    private final PasswordEncoder passwordEncoder;
    private final NationalFederationRepository nationalFederationRepository;

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
    public ResponseEntity<Club> editClubInfo(Principal principal, @RequestBody ClubDTO updatedClub) {
        Optional<Club> optional = clubRepository.findById(updatedClub.getId());
        if (optional.isEmpty()) {
            return ResponseEntity.notFound().build();
        }

        Club existingClub = optional.get();
        Club clubCheck = userAccountRepository
                .findByContact_Email(principal.getName())
                .get()
                .getClub();
        if (!existingClub.equals(clubCheck)) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }

        existingClub.getCity().setName(updatedClub.getCity());
        existingClub.setName(updatedClub.getName());
        existingClub.setEstablished(updatedClub.getEstablished());
        existingClub.setLogoBlobUrl(updatedClub.getLogoBlobUrl());

        nationalFederationRepository.findById(updatedClub.getNationalFederationId());


        Club club = clubRepository.save(existingClub);

        return ResponseEntity.ok(club);
    }


    @PreAuthorize("hasAuthority('SCOPE_ADMIN')")
    @PostMapping
    public ResponseEntity<String> register(@RequestBody NewClubUserAccount newClubUserAccount) {
        if (newClubUserAccount == null || newClubUserAccount.containsUnexpectedInput()) {
            return new ResponseEntity<>("Unexpected input.", HttpStatus.BAD_REQUEST);
        }
        if (newClubUserAccount.getPassword().isEmpty()) {
            return new ResponseEntity<>("No password.", HttpStatus.BAD_REQUEST);
        }
        if (!newClubUserAccount.hasValidEmail()) {
            return new ResponseEntity<>("No valid email.", HttpStatus.BAD_REQUEST);
        }
        if (userAccountRepository.existsByContact_Email(newClubUserAccount.getEmail())) {
            return new ResponseEntity<>("There is already an account with that email.", HttpStatus.CONFLICT);
        }
        City city = new City();
        city.setCountry(newClubUserAccount.getCountry());
        city.setName(newClubUserAccount.getCity());
        Club club = new Club();
        club.setCity(city);
        club.setName(newClubUserAccount.getClubName());
        club.setEstablished(newClubUserAccount.getEstablished());
        Contact contact = new Contact();
        contact.setEmail(newClubUserAccount.getEmail());
        contact.setFirstName(newClubUserAccount.getFirstName());
        contact.setLastName(newClubUserAccount.getLastName());
        contactRepository
                .findByEmail(newClubUserAccount.getEmail())
                .ifPresent(value -> contact.setId(value.getId()));
        UserAccount userAccount = new UserAccount();
        userAccount.setContact(contact);
        userAccount.setClub(club);
        userAccount.setPassword("{bcrypt}" + passwordEncoder.encode(newClubUserAccount.getPassword()));
        userAccount.setScope(Scope.CLUB);
        userAccountRepository.save(userAccount);
        return new ResponseEntity<>(HttpStatus.CREATED);
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
