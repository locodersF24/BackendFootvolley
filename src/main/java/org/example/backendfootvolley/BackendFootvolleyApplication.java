package org.example.backendfootvolley;

import org.example.backendfootvolley.model.*;
import org.example.backendfootvolley.repository.UserAccountRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Profile;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.util.Set;

@SpringBootApplication
public class BackendFootvolleyApplication {

    public static void main(String[] args) {
        SpringApplication.run(BackendFootvolleyApplication.class, args);
    }

    @Profile("dev")
    @Bean
    public CommandLineRunner test(UserAccountRepository userAccountRepository, PasswordEncoder passwordEncoder) {
        return args -> {
            Contact contact = new Contact();
            contact.setEmail("admin@admin.net");
            contact.setFirstName("admin");
            contact.setLastName("admin");
            UserAccount userAccount = new UserAccount();
            userAccount.setContact(contact);
            userAccount.setPassword("{bcrypt}" + passwordEncoder.encode("admin123"));
            userAccount.setScope(Scope.CLUB);

            Club club = new Club();
            City city = new City();
            Player player = new Player();

            city.setCountry("DK");
            city.setName("Copenhagen");

            club.setName("copenhagen footvolley");
            club.setEstablished("2020");
            club.setCity(city);


            player.setContact(contact);
            player.setClubs(Set.of(club));

            userAccount.setClub(club);
            userAccountRepository.save(userAccount);

        };

    }

}
