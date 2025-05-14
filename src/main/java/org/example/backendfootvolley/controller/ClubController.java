package org.example.backendfootvolley.controller;

import lombok.RequiredArgsConstructor;
import org.example.backendfootvolley.dto.UserAccountDTO;
import org.example.backendfootvolley.model.*;
import org.example.backendfootvolley.repository.ClubRepository;
import org.example.backendfootvolley.repository.UserAccountRepository;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;

@RequiredArgsConstructor
@RestController
@RequestMapping("/api/club")
public class ClubController {

    private final ClubRepository clubRepository;
    private final UserAccountRepository userAccountRepository;
    private final PasswordEncoder passwordEncoder;

    @PreAuthorize("hasAuthority('SCOPE_CLUB')")
    @GetMapping
    public ResponseEntity<Club> getByToken(Principal principal) {
        System.out.println(principal.getName());
        return userAccountRepository
                .findByContact_Email(principal.getName())
                .map(UserAccount::getClub)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @GetMapping("/{id}")
    public ResponseEntity<Club> getById(@PathVariable Long id) {
        return clubRepository
                .findById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @PreAuthorize("hasAuthority('SCOPE_ADMIN')")
    @PostMapping
    public ResponseEntity<String> register(@RequestBody UserAccountDTO userAccountDTO) {
        System.out.println(userAccountDTO);
        if (userAccountDTO == null || userAccountDTO.containsUnexpectedInput()) {
            return new ResponseEntity<>("Unexpected input.", HttpStatus.BAD_REQUEST);
        }
        if (userAccountDTO.getPassword().isEmpty()) {
            return new ResponseEntity<>("No password.", HttpStatus.BAD_REQUEST);
        }
        if (!userAccountDTO.hasValidEmail()) {
            return new ResponseEntity<>("No valid email.", HttpStatus.BAD_REQUEST);
        }
        if (userAccountRepository.existsByContact_Email(userAccountDTO.getEmail())) {
            return new ResponseEntity<>("There is already an account with that email.", HttpStatus.CONFLICT);
        }
        City city = new City();
        city.setCountry(userAccountDTO.getCountry());
        city.setName(userAccountDTO.getCity());
        Club club = new Club();
        club.setCity(city);
        club.setName(userAccountDTO.getClubName());
        club.setEstablished(userAccountDTO.getEstablished());
        Contact contact = new Contact();
        contact.setEmail(userAccountDTO.getEmail());
        contact.setFirstName(userAccountDTO.getFirstName());
        contact.setLastName(userAccountDTO.getLastName());
        UserAccount userAccount = new UserAccount();
        userAccount.setContact(contact);
        userAccount.setClub(club);
        userAccount.setPassword("{bcrypt}" + passwordEncoder.encode(userAccountDTO.getPassword()));
        userAccount.setScope(Scope.CLUB);
        userAccountRepository.save(userAccount);
        return new ResponseEntity<>(HttpStatus.CREATED);
    }
}
