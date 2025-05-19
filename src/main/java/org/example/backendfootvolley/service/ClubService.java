package org.example.backendfootvolley.service;

import lombok.RequiredArgsConstructor;
import org.example.backendfootvolley.dto.NewClubUserAccount;
import org.example.backendfootvolley.model.*;
import org.example.backendfootvolley.repository.CityRepository;
import org.example.backendfootvolley.repository.ClubRepository;
import org.example.backendfootvolley.repository.ContactRepository;
import org.example.backendfootvolley.repository.UserAccountRepository;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.*;

@RequiredArgsConstructor
@Service
public class ClubService {

    private final CityRepository cityRepository;
    private final ContactRepository contactRepository;
    private final ClubRepository clubRepository;
    private final PasswordEncoder passwordEncoder;
    private final UserAccountRepository userAccountRepository;

    public SortedSet<Club> getAllClubs() {
        List<Club> list = clubRepository.findAll();
        return new TreeSet<>(list);
    }

    public Optional<Club>getClubById(Long clubId) {
        return clubRepository.findById(clubId);

    }
    public Set<Player> getPlayersByClubId(Long clubId) {
        return clubRepository.findById(clubId).get().getPlayers();
    }

    public String register(NewClubUserAccount newClubUserAccount) {
        if (newClubUserAccount == null || newClubUserAccount.containsUnexpectedInput()) {
            return "Unexpected input.";
        }
        if (newClubUserAccount.getPassword().isEmpty()) {
            return "No password.";
        }
        if (!newClubUserAccount.hasValidEmail()) {
            return "No valid email.";
        }
        if (userAccountRepository.existsByContact_Email(newClubUserAccount.getEmail())) {
            return "Conflict: There is already an account with that email.";
        }
        City city;
        if (newClubUserAccount.getCityId() == null) {
            City newCity = new City();
            newCity.setCountry(newClubUserAccount.getCountry());
            newCity.setName(newClubUserAccount.getCityName());
            city = cityRepository.save(newCity);
        } else {
            Optional<City> cityOptional = cityRepository.findById(newClubUserAccount.getCityId());
            if (cityOptional.isEmpty()) {
                return "No city by that id.";
            }
            city = cityOptional.get();
        }
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
        return "Created";
    }

    public Club updateClub(String clubUserAccountContactEmail, Club input) {
        Optional<UserAccount> userAccount = userAccountRepository.findByContact_Email(clubUserAccountContactEmail);
        if (userAccount.isEmpty()) {
            return null;
        }
        Club club = userAccount.get().getClub();
        club.setName(input.getName());
        club.setEstablished(input.getEstablished());
        return clubRepository.save(club);
    }

    public boolean deleteClub(Long id) {
        boolean exists = clubRepository.existsById(id);
        clubRepository.deleteById(id);
        return exists;
    }
}
