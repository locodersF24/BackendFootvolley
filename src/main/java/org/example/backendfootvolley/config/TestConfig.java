package org.example.backendfootvolley.config;

import org.example.backendfootvolley.model.Contact;
import org.example.backendfootvolley.model.Scope;
import org.example.backendfootvolley.model.UserAccount;
import org.example.backendfootvolley.repository.UserAccountRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;
import org.springframework.security.crypto.password.PasswordEncoder;

@Profile({"test", "dev"})
@Configuration
public class TestConfig {

    @Bean
    public CommandLineRunner commandLineRunner(PasswordEncoder passwordEncoder, UserAccountRepository userAccountRepository) {
        return args -> setupAdminAccount(passwordEncoder, userAccountRepository);
    }

    private void setupAdminAccount(PasswordEncoder passwordEncoder, UserAccountRepository userAccountRepository) {
        Contact contact = new Contact();
        contact.setEmail("admin@admin.net");
        contact.setFirstName("admin");
        contact.setLastName("admin");
        UserAccount userAccount = new UserAccount();
        userAccount.setContact(contact);
        userAccount.setPassword("{bcrypt}" + passwordEncoder.encode("admin123"));
        userAccount.setScope(Scope.ADMIN);
        userAccountRepository.save(userAccount);
    }

}
