package org.example.backendfootvolley;

import org.example.backendfootvolley.model.*;
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

    @Profile({"dev", "test"})
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
            userAccount.setScope(Scope.ADMIN);
            userAccountRepository.save(userAccount);
        };

    }

}
