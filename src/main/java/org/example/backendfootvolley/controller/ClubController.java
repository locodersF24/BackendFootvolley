package org.example.backendfootvolley.controller;

import lombok.RequiredArgsConstructor;
import org.example.backendfootvolley.dto.NewUserAccount;
import org.example.backendfootvolley.model.*;
import org.example.backendfootvolley.repository.UserAccountRepository;
import org.example.backendfootvolley.service.ClubService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequiredArgsConstructor
@RestController
@RequestMapping("/api/clubs")
public class ClubController {

    private final ClubService clubService;
    private final UserAccountRepository userAccountRepository;
    private final PasswordEncoder passwordEncoder;

    @GetMapping
    public ResponseEntity<List<Club>> getAllClubs() {
        return ResponseEntity.ok(clubService.getAllClubs());
    }

    @GetMapping("/{id}")
    public ResponseEntity<Club> getById(@PathVariable Long id) {
        return clubService
                .getClubById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @PreAuthorize("hasAuthority('SCOPE_ADMIN')")
    @PostMapping
    public ResponseEntity<String> register(@RequestBody NewUserAccount newUserAccount) {
        System.out.println(newUserAccount);
        if (newUserAccount == null || newUserAccount.containsUnexpectedInput()) {
            return new ResponseEntity<>("Unexpected input.", HttpStatus.BAD_REQUEST);
        }
        if (newUserAccount.getPassword().isEmpty()) {
            return new ResponseEntity<>("No password.", HttpStatus.BAD_REQUEST);
        }
        if (!newUserAccount.hasValidEmail()) {
            return new ResponseEntity<>("No valid email.", HttpStatus.BAD_REQUEST);
        }
        if (userAccountRepository.existsByContact_Email(newUserAccount.getEmail())) {
            return new ResponseEntity<>("There is already an account with that email.", HttpStatus.CONFLICT);
        }
        City city = new City();
        city.setCountry(newUserAccount.getCountry());
        city.setName(newUserAccount.getCity());
        Club club = new Club();
        club.setCity(city);
        club.setName(newUserAccount.getClubName());
        club.setEstablished(newUserAccount.getEstablished());
        Contact contact = new Contact();
        contact.setEmail(newUserAccount.getEmail());
        contact.setFirstName(newUserAccount.getFirstName());
        contact.setLastName(newUserAccount.getLastName());
        UserAccount userAccount = new UserAccount();
        userAccount.setContact(contact);
        userAccount.setClub(club);
        userAccount.setPassword("{bcrypt}" + passwordEncoder.encode(newUserAccount.getPassword()));
        userAccount.setScope(Scope.CLUB);
        userAccountRepository.save(userAccount);
        return new ResponseEntity<>(HttpStatus.CREATED);
    }

}
