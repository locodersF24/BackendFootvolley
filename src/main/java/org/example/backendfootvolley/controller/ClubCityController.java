package org.example.backendfootvolley.controller;

import lombok.RequiredArgsConstructor;
import org.example.backendfootvolley.model.City;
import org.example.backendfootvolley.model.Club;
import org.example.backendfootvolley.model.UserAccount;
import org.example.backendfootvolley.repository.CityRepository;
import org.example.backendfootvolley.repository.ClubRepository;
import org.example.backendfootvolley.repository.UserAccountRepository;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;
import java.util.Optional;

@PreAuthorize("hasAuthority('SCOPE_CLUB')")
@RequiredArgsConstructor
@RestController
@RequestMapping("/api/clubs/city")
public class ClubCityController {

    private final CityRepository cityRepository;
    private final ClubRepository clubRepository;
    private final UserAccountRepository userAccountRepository;

    @GetMapping
    public ResponseEntity<City> getOwnCity(Principal principal) {
        return userAccountRepository
                .findByContact_Email(principal.getName())
                .map(UserAccount::getClub)
                .map(Club::getCity)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @PostMapping
    public ResponseEntity<Void> createNewCity(Principal principal, @RequestBody City city) {
        if (city.getId() != null) {
            return ResponseEntity.badRequest().build();
        }
        City saved = cityRepository.save(city);
        Club club = userAccountRepository.findByContact_Email(principal.getName()).get().getClub();
        club.setCity(saved);
        clubRepository.save(club);
        return ResponseEntity.noContent().build();
    }

    @PutMapping("/{id}")
    public ResponseEntity<Void> replaceCityWithAnotherExistingCity(Principal principal, @PathVariable Long id) {
        Optional<City> city = cityRepository.findById(id);
        if (city.isEmpty()) {
            return ResponseEntity.notFound().build();
        }
        Club club = userAccountRepository.findByContact_Email(principal.getName()).get().getClub();
        clubRepository.save(club);
        return ResponseEntity.noContent().build();
    }

}
