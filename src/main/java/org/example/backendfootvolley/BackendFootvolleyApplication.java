package org.example.backendfootvolley;

import org.example.backendfootvolley.model.Scope;
import org.example.backendfootvolley.model.UserProfile;
import org.example.backendfootvolley.repository.UserProfileRepository;
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
    public CommandLineRunner test(UserProfileRepository userProfileRepository, PasswordEncoder passwordEncoder) {
        return args -> {
            UserProfile admin = new UserProfile();
            admin.setUsername("admin@admin.net");
            admin.setPassword("{bcrypt}" + passwordEncoder.encode("admin123"));
            admin.setScope(Scope.ADMIN);
            userProfileRepository.save(admin);
        };

    }

}
