package org.example.backendfootvolley;

import org.example.backendfootvolley.model.*;
import org.example.backendfootvolley.repository.*;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Profile;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.time.LocalDate;

@SpringBootApplication
public class BackendFootvolleyApplication {

    public static void main(String[] args) {
        SpringApplication.run(BackendFootvolleyApplication.class, args);
    }

    @Profile("dev")
    @Bean
    public CommandLineRunner test(
            UserAccountRepository userAccountRepository,
            PasswordEncoder passwordEncoder,
            CityRepository cityRepository,
            ClubRepository clubRepository,
            LeagueRepository leagueRepository,
            TournamentRepository tournamentRepository) {
        return args -> {

            // Create admin user
            Contact contact = new Contact();
            contact.setEmail("admin@admin.net");
            contact.setFirstName("admin");
            contact.setLastName("admin");
            UserAccount userAccount = new UserAccount();
            userAccount.setContact(contact);
            userAccount.setPassword("{bcrypt}" + passwordEncoder.encode("admin123"));
            userAccount.setScope(Scope.ADMIN);
            userAccountRepository.save(userAccount);

            // Create club user
            City city = new City();
            city.setCountry("DK");
            city.setName("Copenhagen");

            Club club = new Club();
            club.setCity(city);
            club.setName("Copenhagen Footvolley");
            club.setEstablished("2025");

            Contact contactClub = new Contact();
            contactClub.setEmail("club@club.net");
            contactClub.setFirstName("Jens");
            contactClub.setLastName("Hansen");
            UserAccount clubUserAccount = new UserAccount();
            clubUserAccount.setContact(contactClub);
            clubUserAccount.setPassword("{bcrypt}" + passwordEncoder.encode("club123"));
            clubUserAccount.setScope(Scope.CLUB);
            clubUserAccount.setClub(club);
            userAccountRepository.save(clubUserAccount);

            // Create tournament
            League league = new League();
            league.setSeasonYear("2025");
            league.setCategory(Category.OPEN);
            League savedLeague = leagueRepository.save(league);

            Tournament tournament = new Tournament();
            tournament.setLeague(savedLeague);
            tournament.setCity(cityRepository.findById(1L).get());
            tournament.setFinalsStartDate(LocalDate.now());
            tournament.setFinalsEndDate(LocalDate.now());
            tournament.setCurrency("EUR");
            tournament.setHost(clubRepository.findById(1L).get());
            tournamentRepository.save(tournament);
        };

    }

}


