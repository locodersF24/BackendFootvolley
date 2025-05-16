package org.example.backendfootvolley;

import org.example.backendfootvolley.model.*;
import org.example.backendfootvolley.repository.CityRepository;
import org.example.backendfootvolley.repository.UserAccountRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Profile;
import org.springframework.security.crypto.password.PasswordEncoder;

@SpringBootApplication
public class BackendFootvolleyApplication {

    public static void main(String[] args) {
        SpringApplication.run(BackendFootvolleyApplication.class, args);
    }

    @Profile("dev")
    @Bean
    public CommandLineRunner test(UserAccountRepository userAccountRepository, PasswordEncoder passwordEncoder, CityRepository cityRepository) {
        return args -> {
            Contact contact = new Contact();
            contact.setEmail("admin@admin.net");
            contact.setFirstName("admin");
            contact.setLastName("admin");
            UserAccount userAccount = new UserAccount();
            userAccount.setContact(contact);
            userAccount.setPassword("{bcrypt}" + passwordEncoder.encode("admin123"));
            userAccount.setScope(Scope.ADMIN);
            userAccountRepository.save(userAccount);

            City madrid = new City();
            madrid.setCountry("ES");
            madrid.setName("Madrid");
            cityRepository.save(madrid);

            City barcelona = new City();
            barcelona.setCountry("ES");
            barcelona.setName("Barcelona");
            cityRepository.save(barcelona);

            City copenhagen = new City();
            copenhagen.setCountry("DK");
            copenhagen.setName("Copenhagen");
            cityRepository.save(copenhagen);
        };
    }

}
